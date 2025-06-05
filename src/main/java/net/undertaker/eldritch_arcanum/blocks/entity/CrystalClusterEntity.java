package net.undertaker.eldritch_arcanum.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.undertaker.eldritch_arcanum.essence.Essence;
import net.undertaker.eldritch_arcanum.items.custom.EssenceCrystalItem;
import net.undertaker.eldritch_arcanum.util.ModDataComponents;
import net.undertaker.eldritch_arcanum.util.ModRegistries;

import java.util.Optional;

public class CrystalClusterEntity extends BlockEntity {
    public CrystalClusterEntity( BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CRYSTAL_CLUSTER_ENTITY.get(), pos, blockState);
    }
    private static final int MAX_AMOUNT = 64;
    private Essence storedEssence;
    private int amount;

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (storedEssence != null) {
            System.out.println("Saving: " + storedEssence.getId().toString() + " / " + amount);
            tag.putString("StoredEssence", storedEssence.getId().toString());
        }
        tag.putInt("EssenceAmount", amount);
    }


    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        this.amount = tag.getInt("EssenceAmount");
        if (tag.contains("StoredEssence")) {
            ResourceLocation id = ResourceLocation.tryParse(tag.getString("StoredEssence"));
            if (id != null) {
                this.storedEssence = ModRegistries.ESSENCE_REGISTRY.get(id);
                System.out.println("Loading: " + storedEssence.getId().toString() + " / " + amount);
            }
        } else {
            this.storedEssence = null;
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag tag = super.getUpdateTag(provider);
        saveAdditional(tag, provider);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider provider) {
        super.handleUpdateTag(tag, provider);
        loadAdditional(tag, provider);
    }


    public boolean fill(Essence essence, int addAmount) {
        if (storedEssence != null && !storedEssence.getId().equals(essence.getId()))
            return false;

        int newAmount = Math.min(amount + addAmount, MAX_AMOUNT);
        if (newAmount != amount) {
            this.storedEssence = essence;
            this.amount = newAmount;
            setChanged();
            return true;
        }
        return false;
    }

    public int drain(int drainAmount) {
        int drained = Math.min(amount, drainAmount);
        amount -= drained;
        if (amount <= 0) {
            storedEssence = null;
            amount = 0;
        }
        setChanged();
        return drained;
    }

    public InteractionResult use(Player player, InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand);
        if (item.getItem() instanceof EssenceCrystalItem crystal) {
            Optional<Essence> essenceOpt = crystal.getBoundEssence(item);
            if (essenceOpt.isPresent()) {
                if (fill(essenceOpt.get(), 8)) {
                    item.shrink(1);
                    setChanged();
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    public Optional<Essence> getStoredEssence() {
        return Optional.ofNullable(storedEssence);
    }

    public int getColor() {
        return storedEssence != null ?
                storedEssence.getColor() :
                0xFFFFFFFF; // Белый если пусто
    }

    public float getFillPercentage() {
        return (float) amount / MAX_AMOUNT;
    }
}
