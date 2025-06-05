package net.undertaker.eldritch_arcanum.blocks.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.network.PacketDistributor;
import net.undertaker.eldritch_arcanum.networking.packets.ParticlePacket;
import net.undertaker.eldritch_arcanum.particles.CustomItemParticle;
import net.undertaker.eldritch_arcanum.particles.ModParticles;
import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.undertaker.eldritch_arcanum.essence.Essence;
import net.undertaker.eldritch_arcanum.infusion_recipes.InfusionRecipe;
import net.undertaker.eldritch_arcanum.util.ModRegistries;
import org.joml.Vector3f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class CopperAltarEntity extends BlockEntity {
    public CopperAltarEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.COPPER_ALTAR_ENTITY.get(), pos, blockState);
    }
    public ItemStackHandler inventory = new ItemStackHandler(1){
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            markUpdated();
            if(!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final Logger log = LoggerFactory.getLogger(CopperAltarEntity.class);
    private static final int TICKS_PER_STEP = 40;

    private InfusionRecipe currentRecipe;
    private Map<Essence, Integer> remainingEssences;
    private Queue<Pair<ItemStack, BlockPos>> pendingItems;
    private int essenceTickCounter;
    private int itemTickCounter;

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries,tag.getCompound("Inventory"));
        if (tag.contains("RemainingEssences")) {
            CompoundTag essTag = tag.getCompound("RemainingEssences");
            remainingEssences = new HashMap<>();
            for (String key : essTag.getAllKeys()) {
                CompoundTag one = new CompoundTag();
                one.putString("Id", key);
                Essence e = Essence.deserializeNBT(one);
                remainingEssences.put(e, essTag.getInt(key));
            }
        }
        if (tag.contains("PendingItems")) {
            int count = tag.getInt("PendingCount");
            pendingItems = new LinkedList<>();
            for (int i = 0; i < count; i++) {
                CompoundTag itemTag = tag.getCompound("Item" + i);
                ItemStack stack = ItemStack.parseOptional(registries, itemTag);
                BlockPos pos = BlockPos.of(itemTag.getLong("SourcePos"));
                pendingItems.add(Pair.of(stack, pos));
            }
        }
        if (tag.contains("RecipeID")) {
            String recipeIdStr = tag.getString("RecipeID");
            ResourceLocation recipeRL = ResourceLocation.tryParse(recipeIdStr);
            if (recipeRL != null && level != null) {
                var registry = level.registryAccess()
                        .registryOrThrow(ModRegistries.INFUSION_RECIPES_REGISTRY_KEY);
                registry.getOptional(recipeRL).ifPresent(r -> this.currentRecipe = r);
            } else {
                log.warn("Invalid RecipeID in NBT: {}", recipeIdStr);
            }
        }
    }


    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("Inventory", inventory.serializeNBT(registries));
        if (remainingEssences != null) {
            CompoundTag ess = new CompoundTag();
            remainingEssences.forEach((essence, amount) ->
                    ess.putInt(essence.getId().toString(), amount)
            );
            tag.put("RemainingEssences", ess);
        }
        if (pendingItems != null) {
            tag.putInt("PendingCount", pendingItems.size());
            int i = 0;
            for (Pair<ItemStack, BlockPos> pair : pendingItems) {
                Tag rawTag = pair.getLeft().save(registries);
                CompoundTag it;
                if (rawTag instanceof CompoundTag ctag) {
                    it = ctag;
                } else {
                    it = new CompoundTag();
                }
                it.putLong("SourcePos", pair.getRight().asLong());
                tag.put("Item" + (i++), it);
            }
        }
        if (currentRecipe != null) {
            tag.putString("RecipeID", currentRecipe.getId().toString());
        }

        super.saveAdditional(tag, registries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.handleUpdateTag(tag, lookupProvider);
        loadAdditional(tag, lookupProvider);
    }

    public void clearContents(){
        inventory.setStackInSlot(0, ItemStack.EMPTY);
    }

    public ItemStack getItem() {
        return inventory.getStackInSlot(0);
    }

    public boolean hasItem() {
        return !inventory.getStackInSlot(0).isEmpty();
    }

    public void setItem(ItemStack item) {
        this.inventory.setStackInSlot(0, item.isEmpty() ? ItemStack.EMPTY : item);
        markUpdated();
        if(level != null){
            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
        }
    }

    public void markUpdated() {
        setChanged();
        if (level != null) {
            level.markAndNotifyBlock(getBlockPos(), level.getChunkAt(getBlockPos()), getBlockState(), getBlockState(), Block.UPDATE_ALL,1);
        }
    }

    public InfusionRecipe getCurrentRecipe() {
        return currentRecipe;
    }

    public void startCraft() {
        if (level == null || level.isClientSide() || currentRecipe != null) return;
        List<ItemStack> items = getPedestals().stream()
                .map(PedestalEntity::getItem)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        ItemStack catalyst = inventory.getStackInSlot(0);
        Optional<InfusionRecipe> recipeOpt = level.registryAccess()
                .registryOrThrow(ModRegistries.INFUSION_RECIPES_REGISTRY_KEY)
                .stream()
                .filter(r -> r.matches(items, catalyst, getAvailableEssences(), level))
                .findFirst();
        if (recipeOpt.isEmpty()) return;
        currentRecipe = recipeOpt.get();
        remainingEssences = new HashMap<>(currentRecipe.getRequiredEssences());
        pendingItems = new LinkedList<>();
        getPedestals().forEach(p -> {
            ItemStack s = p.getItem().copy();
            int count = s.getCount();
            BlockPos pPos = p.getBlockPos();
            while (count-- > 0) {
                pendingItems.add(Pair.of(new ItemStack(s.getItem()), pPos));
            }
        });
        essenceTickCounter = 0;
        itemTickCounter = 0;
        level.playSound(null, worldPosition, SoundEvents.BELL_BLOCK,
                SoundSource.BLOCKS, 1f, 1f);
        setChanged();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CopperAltarEntity altar) {
        altar.tick();
    }

    private void tick() {
        if (level == null || level.isClientSide || currentRecipe == null) return;
        if(getItem().isEmpty()) {
                level.playSound(null, worldPosition, SoundEvents.GLASS_BREAK,
                        SoundSource.BLOCKS, 1f, 1f);
            resetState();
            return;
        }
        essenceTickCounter++;
        if (absorbEssenceStep()) return;
        itemTickCounter++;
        if (absorbItemStep()) return;
        finishCraft();
    }

    private boolean absorbEssenceStep() {
        for (var entry : remainingEssences.entrySet()) {
            Essence essence = entry.getKey();
            int needed = entry.getValue();
            if (needed > 0) {
                if (essenceTickCounter < TICKS_PER_STEP) return true;
                essenceTickCounter = 0;
                for (CrystalClusterEntity cluster : getNearbyClusters()) {
                    if (cluster.getStoredEssence().filter(e -> e == essence).isPresent()) {
                        int drained = cluster.drain(1);
                        if (drained > 0) {
                            remainingEssences.put(essence, needed - 1);
                            sendEssenceParticle(essence, cluster.getBlockPos(), worldPosition);
                            return true;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean absorbItemStep() {
        if (pendingItems == null || pendingItems.isEmpty()) return false;
        Pair<ItemStack, BlockPos> pair = pendingItems.peek();
        if (level instanceof ServerLevel) {
            BlockPos pedestalPos = pair.getRight();
            double sx = pedestalPos.getX() + 0.5;
            double sy = pedestalPos.getY() + 0.5;
            double sz = pedestalPos.getZ() + 0.5;

            ItemStack stack = pair.getLeft();
            BlockPos altarPos = getBlockPos();
            double tx = altarPos.getX() + 0.5;
            double ty = altarPos.getY() + 0.5;
            double tz = altarPos.getZ() + 0.5;
            ((ServerLevel) level).sendParticles(
                    new ItemParticleOption(ModParticles.ITEM_SWIRL.get(), stack),
                    sx, sy, sz,   // точка спавна
                    1,            // count (одноиспользование)
                    0,0,0, // разброс по XYZ = 0 (мы сами считаем движение)
                    0.0D          // «скорость» не важна, всё будет рассчитано в CustomItemParticle
            );

            if (itemTickCounter < TICKS_PER_STEP) return true;
            itemTickCounter = 0;
            getPedestals().stream()
                    .filter(ped -> ped.getBlockPos().equals(pair.getRight()))
                    .findFirst()
                    .ifPresent(pedestal -> {

                        pedestal.decrementItem(1);
                        pedestal.setChanged();
                    });
            ItemStack copy = pair.getLeft();
            copy.shrink(1);
            if (copy.isEmpty()) {
                pendingItems.poll();
            }
        }
        return true;
    }

    private void finishCraft() {
        ItemStack result = currentRecipe.getResult().copy();
        inventory.setStackInSlot(0, result);
        currentRecipe.onCraft(level, worldPosition);
        resetState();
    }

    private void resetState() {
        currentRecipe = null;
        remainingEssences.clear();
        pendingItems.clear();
        itemTickCounter = 0;
        essenceTickCounter = 0;
        setChanged();
    }

    private Map<Essence, Integer> getAvailableEssences() {
        return getNearbyClusters().stream()
                .map(CrystalClusterEntity::getStoredEssence)
                .flatMap(Optional::stream)
                .collect(Collectors.groupingBy(e -> e, Collectors.summingInt(e -> 1)));
    }

    private List<CrystalClusterEntity> getNearbyClusters() {
        return BlockPos.betweenClosedStream(worldPosition.offset(-9, -2, -9), worldPosition.offset(9, 2, 9))
                .map(BlockPos::immutable).filter(pos -> level.getBlockEntity(pos) instanceof CrystalClusterEntity)
                .map(pos -> (CrystalClusterEntity) level.getBlockEntity(pos)).toList();
    }
    private List<PedestalEntity> getPedestals() {
        return BlockPos.betweenClosedStream(worldPosition.offset(-9, -2, -9), worldPosition.offset(9, 2, 9))
                .map(BlockPos::immutable).filter(pos -> level.getBlockEntity(pos) instanceof PedestalEntity)
                .map(pos -> (PedestalEntity) level.getBlockEntity(pos)).toList();
    }

    private void sendEssenceParticle(Essence essence, BlockPos from, BlockPos to) {
        if (level instanceof ServerLevel) return;
        var cLevel = (net.minecraft.client.multiplayer.ClientLevel) level;
        Vec3 start = Vec3.atCenterOf(from);
        Vec3 end = Vec3.atCenterOf(to);
        Vec3 motion = end.subtract(start).normalize().scale(end.distanceTo(start)/10.0);
        cLevel.addParticle(ParticleTypes.ENCHANT, start.x, start.y + .5, start.z,
                motion.x, motion.y + .25, motion.z);
    }
}