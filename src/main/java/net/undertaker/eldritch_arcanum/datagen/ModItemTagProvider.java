package net.undertaker.eldritch_arcanum.datagen;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.blocks.ModBlocks;
import net.undertaker.eldritch_arcanum.items.ModItems;
import org.jetbrains.annotations.Nullable;

public class ModItemTagProvider extends ItemTagsProvider {
  public ModItemTagProvider(
      PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      CompletableFuture<TagLookup<Block>> blockTags,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, blockTags, EldritchArcanum.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    tag(ItemTags.SWORDS)
            .add(ModItems.CEBBITE_SWORD.get())
            .add(ModItems.ADAMANTITE_SWORD.get())
    ;
    tag(ItemTags.SHOVELS)
            .add(ModItems.CEBBITE_SHOVEL.get())
            .add(ModItems.ADAMANTITE_SHOVEL.get())
    ;
    tag(ItemTags.PICKAXES)
            .add(ModItems.CEBBITE_PICKAXE.get())
            .add(ModItems.ADAMANTITE_PICKAXE.get())
    ;
    tag(ItemTags.AXES)
            .add(ModItems.CEBBITE_AXE.get())
            .add(ModItems.CEBBITE_AXE.get())
    ;
    tag(ItemTags.HOES)
            .add(ModItems.CEBBITE_HOE.get())
            .add(ModItems.ADAMANTITE_HOE.get())
    ;
    tag(ItemTags.LOGS_THAT_BURN)
            .add(ModBlocks.VELESCIL_LOG.get().asItem())
            .add(ModBlocks.VELESCIL_WOOD.get().asItem())
            .add(ModBlocks.STRIPPED_VELESCIL_LOG.get().asItem())
            .add(ModBlocks.STRIPPED_VELESCIL_WOOD.get().asItem());
    tag(ItemTags.PLANKS)
            .add(ModBlocks.VELESCIL_PLANKS.get().asItem());
  }
}
