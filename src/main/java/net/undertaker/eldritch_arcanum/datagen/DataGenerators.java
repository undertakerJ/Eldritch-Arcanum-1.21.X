package net.undertaker.eldritch_arcanum.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.undertaker.eldritch_arcanum.EldritchArcanum;

@EventBusSubscriber(modid = EldritchArcanum.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    ExistingFileHelper fileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    generator.addProvider(
        event.includeServer(),
        new LootTableProvider(
            packOutput,
            Collections.emptySet(),
            List.of(
                new LootTableProvider.SubProviderEntry(
                    ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
            lookupProvider));

    generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, fileHelper));
    generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, fileHelper));

    BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider, fileHelper);
    generator.addProvider(event.includeServer(), blockTagsProvider);

    generator.addProvider(event.includeServer(),  new ModItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), fileHelper));

    generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));
    generator.addProvider(event.includeServer(), new ModDataMapProvider(packOutput, lookupProvider));

    generator.addProvider(
            event.includeServer(), new CuriosCustomDataProvider(packOutput, fileHelper,lookupProvider));

  }
}
