package net.undertaker.eldritch_arcanum.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.blocks.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
  public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
    super(output, EldritchArcanum.MOD_ID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    blockWithItem(ModBlocks.CEBBITE_ORE);
    blockWithItem(ModBlocks.DEEPSLATE_CEBBITE_ORE);
    blockWithItem(ModBlocks.SCULK_CEBBITE_ORE);
    blockWithItem(ModBlocks.CEBBITE_BLOCK);
    blockWithItem(ModBlocks.ADAMANTITE_BLOCK);
    blockWithItem(ModBlocks.ADAMANTITE_ORE);

    logBlock((RotatedPillarBlock) ModBlocks.VELESCIL_LOG.get());
    axisBlock(
        (RotatedPillarBlock) ModBlocks.VELESCIL_WOOD.get(),
        blockTexture(ModBlocks.VELESCIL_LOG.get()),
        blockTexture(ModBlocks.VELESCIL_LOG.get()));

    logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_VELESCIL_LOG.get());
    axisBlock(
        (RotatedPillarBlock) ModBlocks.STRIPPED_VELESCIL_WOOD.get(),
        blockTexture(ModBlocks.STRIPPED_VELESCIL_LOG.get()),
        blockTexture(ModBlocks.STRIPPED_VELESCIL_LOG.get()));

    blockWithItem(ModBlocks.VELESCIL_PLANKS);

    blockItem(ModBlocks.VELESCIL_LOG);
    blockItem(ModBlocks.STRIPPED_VELESCIL_LOG);
    blockItem(ModBlocks.VELESCIL_WOOD);
    blockItem(ModBlocks.STRIPPED_VELESCIL_WOOD);
    simpleBlockWithItem(ModBlocks.PEDESTAL.get(), new ModelFile.UncheckedModelFile(modLoc("block/pedestal_block")));
  }

  private void blockWithItem(DeferredBlock<?> deferredBlock) {
    simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
  }

  private void blockItem(DeferredBlock<?> deferredBlock) {
    simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("eldritch_arcanum:block/" + deferredBlock.getId().getPath()));
  }

  private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
    simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("eldritch_arcanum:block/" + deferredBlock.getId().getPath() + appendix));
  }
}
