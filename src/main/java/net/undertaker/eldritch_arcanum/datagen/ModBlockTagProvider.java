package net.undertaker.eldritch_arcanum.datagen;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.blocks.ModBlocks;
import net.undertaker.eldritch_arcanum.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class ModBlockTagProvider extends BlockTagsProvider {
  public ModBlockTagProvider(
      PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, EldritchArcanum.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .add(ModBlocks.CEBBITE_ORE.get())
        .add(ModBlocks.DEEPSLATE_CEBBITE_ORE.get())
        .add(ModBlocks.SCULK_CEBBITE_ORE.get())
        .add(ModBlocks.CEBBITE_BLOCK.get())
        .add(ModBlocks.ADAMANTITE_ORE.get())
        .add(ModBlocks.ADAMANTITE_BLOCK.get());

    tag(BlockTags.NEEDS_DIAMOND_TOOL)
        .add(ModBlocks.CEBBITE_ORE.get())
        .add(ModBlocks.DEEPSLATE_CEBBITE_ORE.get())
        .add(ModBlocks.SCULK_CEBBITE_ORE.get())
        .add(ModBlocks.CEBBITE_BLOCK.get());

    tag(ModTags.Blocks.NEEDS_CEBBITE_TOOL)
        .add(ModBlocks.ADAMANTITE_ORE.get())
        .add(ModBlocks.ADAMANTITE_BLOCK.get())
        .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

    tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
        .add(ModBlocks.ADAMANTITE_ORE.get())
        .add(ModBlocks.ADAMANTITE_BLOCK.get());
    tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
        .add(ModBlocks.ADAMANTITE_ORE.get())
        .add(ModBlocks.ADAMANTITE_BLOCK.get());
    tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
        .add(ModBlocks.ADAMANTITE_ORE.get())
        .add(ModBlocks.ADAMANTITE_BLOCK.get());
    tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
        .add(ModBlocks.ADAMANTITE_ORE.get())
        .add(ModBlocks.ADAMANTITE_BLOCK.get());
    tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
        .add(ModBlocks.ADAMANTITE_ORE.get())
        .add(ModBlocks.ADAMANTITE_BLOCK.get());
    tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
        .add(ModBlocks.ADAMANTITE_ORE.get())
        .add(ModBlocks.ADAMANTITE_BLOCK.get());

    tag(ModTags.Blocks.INCORRECT_FOR_CEBBITE_TOOL)
        .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
        .remove(ModTags.Blocks.NEEDS_CEBBITE_TOOL);

    tag(BlockTags.LOGS_THAT_BURN)
            .add(ModBlocks.VELESCIL_LOG.get())
            .add(ModBlocks.VELESCIL_WOOD.get())
            .add(ModBlocks.STRIPPED_VELESCIL_LOG.get())
            .add(ModBlocks.STRIPPED_VELESCIL_WOOD.get());
  }
}
