package net.undertaker.eldritch_arcanum.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.blocks.custom.CopperAltarBlock;
import net.undertaker.eldritch_arcanum.blocks.custom.CrystalClusterBlock;
import net.undertaker.eldritch_arcanum.blocks.custom.ModFlammableRotatedPillarBlock;
import net.undertaker.eldritch_arcanum.blocks.custom.PedestalBlock;
import net.undertaker.eldritch_arcanum.items.ModItems;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EldritchArcanum.MOD_ID);

    public static final DeferredBlock<Block> CEBBITE_ORE =
            registerBlock(
                    "cebbite_ore",
                    () ->
                            new Block(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                                            .destroyTime(20f)
                                            .explosionResistance(1200f)));
    public static final DeferredBlock<Block> SCULK_CEBBITE_ORE =
            registerBlock(
                    "sculk_cebbite_ore",
                    () ->
                            new Block(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                                            .destroyTime(20f)
                                            .explosionResistance(1200f)));
    public static final DeferredBlock<Block> DEEPSLATE_CEBBITE_ORE =
            registerBlock(
                    "deepslate_cebbite_ore",
                    () ->
                            new Block(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                                            .destroyTime(20f)
                                            .explosionResistance(1200f)));
    public static final DeferredBlock<Block> CEBBITE_BLOCK =
            registerBlock(
                    "cebbite_block",
                    () ->
                            new Block(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                                            .destroyTime(25f)
                                            .explosionResistance(9600f)));

    public static final DeferredBlock<Block> ADAMANTITE_ORE =
            registerBlock(
                    "adamantite_ore",
                    () ->
                            new Block(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                                            .destroyTime(30f)
                                            .explosionResistance(1200f)));


    public static final DeferredBlock<Block> ADAMANTITE_BLOCK =
            registerBlock(
                    "adamantite_block",
                    () ->
                            new Block(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                                            .destroyTime(35f)
                                            .explosionResistance(1200f)));

    public static final DeferredBlock<Block> VELESCIL_LOG =
            registerBlock(
                    "velescil_log",
                    () ->
                            new ModFlammableRotatedPillarBlock(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).lightLevel(new ToIntFunction<BlockState>() {
                                        @Override
                                        public int applyAsInt(BlockState value) {
                                            return 5;
                                        }
                                    })));
    public static final DeferredBlock<Block> VELESCIL_WOOD =
            registerBlock(
                    "velescil_wood",
                    () ->
                            new ModFlammableRotatedPillarBlock(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).lightLevel(new ToIntFunction<BlockState>() {
                                        @Override
                                        public int applyAsInt(BlockState value) {
                                            return 5;
                                        }
                                    })));
    public static final DeferredBlock<Block> STRIPPED_VELESCIL_LOG =
            registerBlock(
                    "stripped_velescil_log",
                    () ->
                            new ModFlammableRotatedPillarBlock(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_VELESCIL_WOOD =
            registerBlock(
                    "stripped_velescil_wood",
                    () ->
                            new ModFlammableRotatedPillarBlock(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

  public static final DeferredBlock<Block> VELESCIL_PLANKS =
      registerBlock(
          "velescil_planks",
          () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                  return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                  return 10;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                  return 5;
              }
          });

    public static final DeferredBlock<Block> PEDESTAL =
            registerBlock("pedestal_block",
                    () -> new PedestalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> COPPER_ALTAR =
            registerBlock("copper_altar",
                    () -> new CopperAltarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
  public static final DeferredBlock<Block> CRYSTAL_CLUSTER =
      registerBlock(
          "crystal_cluster",
          () -> new CrystalClusterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
