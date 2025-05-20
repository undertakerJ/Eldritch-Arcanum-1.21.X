package net.undertaker.eldritch_arcanum.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.items.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EldritchArcanum.MOD_ID);

    public static final DeferredBlock<Block> CEBBITE_ORE =
            registerBlock(
                    "cebbite_ore",
                    () ->
                            new Block(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                                            .destroyTime(25f)
                                            .explosionResistance(1200f)));
    public static final DeferredBlock<Block> SCULK_CEBBITE_ORE =
            registerBlock(
                    "sculk_cebbite_ore",
                    () ->
                            new Block(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                                            .destroyTime(25f)
                                            .explosionResistance(1200f)));
    public static final DeferredBlock<Block> DEEPSLATE_CEBBITE_ORE =
            registerBlock(
                    "deepslate_cebbite_ore",
                    () ->
                            new Block(
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                                            .destroyTime(25f)
                                            .explosionResistance(1200f)));


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
