package net.undertaker.eldritch_arcanum.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.blocks.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

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
            .add(ModBlocks.SCULK_CEBBITE_ORE.get());

    tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.CEBBITE_ORE.get())
            .add(ModBlocks.DEEPSLATE_CEBBITE_ORE.get())
            .add(ModBlocks.SCULK_CEBBITE_ORE.get());
  }
}
