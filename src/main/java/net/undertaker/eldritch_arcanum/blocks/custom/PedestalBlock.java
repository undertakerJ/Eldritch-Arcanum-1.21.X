package net.undertaker.eldritch_arcanum.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.undertaker.eldritch_arcanum.blocks.entity.PedestalEntity;
import org.jetbrains.annotations.Nullable;

public class PedestalBlock extends BaseEntityBlock {
  public PedestalBlock(Properties properties) {
    super(properties);
  }

  public static final VoxelShape SHAPE = Block.box(3, 0, 3, 13, 14, 13);
  public static final MapCodec<PedestalBlock> CODEC = simpleCodec(PedestalBlock::new);

  @Override
  public VoxelShape getShape(
      BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    return SHAPE;
  }

  @Override
  public RenderShape getRenderShape(BlockState pState) {
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
      if (blockEntity instanceof PedestalEntity && ((PedestalEntity) blockEntity).hasItem()) {
        pLevel.addFreshEntity(
            new ItemEntity(
                pLevel,
                pPos.getX(),
                pPos.getY(),
                pPos.getZ(),
                ((PedestalEntity) blockEntity).getItem()));
        ((PedestalEntity) blockEntity).clearContents();
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
    if (blockEntity instanceof PedestalEntity pedestalEntity) {
      ItemStack heldItem = player.getItemInHand(hand);

      if (!pedestalEntity.hasItem() && !heldItem.isEmpty()) {
          level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
          pedestalEntity.setItem(new ItemStack(heldItem.getItem(), 1));
          player.setItemInHand(hand, new ItemStack(heldItem.getItem(), heldItem.getCount() - 1));
      } else if (pedestalEntity.hasItem()) {
          player.addItem(pedestalEntity.getItem());
          level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
          ((PedestalEntity) blockEntity).clearContents();

      }
      level.sendBlockUpdated(pos, state, state, 3);
    }

    return ItemInteractionResult.SUCCESS;
  }


  @Override
  public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new PedestalEntity(blockPos, blockState);
  }
}
