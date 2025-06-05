package net.undertaker.eldritch_arcanum.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.undertaker.eldritch_arcanum.blocks.entity.CopperAltarEntity;
import net.undertaker.eldritch_arcanum.blocks.entity.ModBlockEntities;
import net.undertaker.eldritch_arcanum.blocks.entity.PedestalEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CopperAltarBlock extends BaseEntityBlock {

    public CopperAltarBlock(Properties properties) {
        super(properties);
    }
    public static final MapCodec<CopperAltarBlock> CODEC = simpleCodec(CopperAltarBlock::new);
    public static final VoxelShape SHAPE = Block.box(3, 0, 3, 13, 10.5, 13);

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new CopperAltarEntity(blockPos, blockState);
    }
    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
    @Override
    public void onRemove(
            BlockState pState,
            Level pLevel,
            BlockPos pPos,
            BlockState pNewState,
            boolean pMovedByPiston) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof CopperAltarEntity copperAltarEntity  && copperAltarEntity.hasItem()) {
                pLevel.addFreshEntity(
                        new ItemEntity(
                                pLevel,
                                pPos.getX(),
                                pPos.getY(),
                                pPos.getZ(),
                                copperAltarEntity.getItem()));
                copperAltarEntity.clearContents();
                blockEntity.invalidateCapabilities();
                pLevel.removeBlockEntity(pPos);
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof CopperAltarEntity pedestalEntity) {
            ItemStack heldItem = player.getItemInHand(hand);

            if (!pedestalEntity.hasItem() && !heldItem.isEmpty()) {
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
                pedestalEntity.setItem(new ItemStack(heldItem.getItem(), 1));
                player.setItemInHand(hand, new ItemStack(heldItem.getItem(), heldItem.getCount() - 1));
            } else if (pedestalEntity.hasItem()) {
                player.addItem(pedestalEntity.getItem());
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
                pedestalEntity.clearContents();

            }
            level.sendBlockUpdated(pos, state, state, 3);
        }

        return ItemInteractionResult.SUCCESS;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (level.isClientSide) return;

        if (level.hasNeighborSignal(pos)){
            if (level.getBlockEntity(pos) instanceof CopperAltarEntity altar) {
                altar.startCraft();
            }
        }
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.COPPER_ALTAR_ENTITY.get(), CopperAltarEntity::tick);
    }

}
