package net.undertaker.eldritch_arcanum.datagen;

import java.util.Set;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.undertaker.eldritch_arcanum.blocks.ModBlocks;
import net.undertaker.eldritch_arcanum.items.ModItems;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        add(ModBlocks.CEBBITE_ORE.get(),
                block -> createOreDrop(ModBlocks.CEBBITE_ORE.get(), ModItems.RAW_CEBBITE_ORE.get()));
         add(ModBlocks.DEEPSLATE_CEBBITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_CEBBITE_ORE.get(), ModItems.RAW_CEBBITE_ORE.get()));
         add(ModBlocks.SCULK_CEBBITE_ORE.get(),
                block -> createOreDrop(ModBlocks.SCULK_CEBBITE_ORE.get(), ModItems.RAW_CEBBITE_ORE.get()));


    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
    return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
