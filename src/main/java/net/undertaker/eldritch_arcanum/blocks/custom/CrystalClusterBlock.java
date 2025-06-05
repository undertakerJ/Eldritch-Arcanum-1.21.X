package net.undertaker.eldritch_arcanum.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.undertaker.eldritch_arcanum.blocks.entity.CrystalClusterEntity;
import net.undertaker.eldritch_arcanum.items.custom.EssenceCrystalItem;
import org.jetbrains.annotations.Nullable;

public class CrystalClusterBlock extends HorizontalDirectionalBlock implements EntityBlock {
  public CrystalClusterBlock(Properties properties) {
    super(properties);
    this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
  }

  public static final MapCodec<CrystalClusterBlock> CODEC = simpleCodec(CrystalClusterBlock::new);
  public static final VoxelShape SHAPE = Block.box(3, 0, 3, 13, 11, 13);
  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }

  @Override
  protected VoxelShape getShape(
      BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
    return SHAPE;
  }

  @Override
  public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new CrystalClusterEntity(blockPos, blockState);
  }

  @Override
  public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState()
        .setValue(FACING, context.getHorizontalDirection().getOpposite());
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    super.createBlockStateDefinition(builder);
    builder.add(FACING);
  }

  @Override
  protected RenderShape getRenderShape(BlockState state) {
    return RenderShape.ENTITYBLOCK_ANIMATED;
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
      if(player.getMainHandItem().getItem() instanceof EssenceCrystalItem){
          BlockEntity blockEntity = level.getBlockEntity(pos);
          if(blockEntity instanceof CrystalClusterEntity crystalClusterEntity){
              crystalClusterEntity.use(player, hand);
              return ItemInteractionResult.SUCCESS;
          }
      }
    return ItemInteractionResult.FAIL;
  }
}
