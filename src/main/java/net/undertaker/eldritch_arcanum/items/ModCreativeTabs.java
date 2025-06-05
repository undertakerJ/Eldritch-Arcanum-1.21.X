package net.undertaker.eldritch_arcanum.items;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.blocks.ModBlocks;
import net.undertaker.eldritch_arcanum.essence.Essence;
import net.undertaker.eldritch_arcanum.items.custom.EssenceCrystalItem;
import net.undertaker.eldritch_arcanum.util.ModRegistries;

import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EldritchArcanum.MOD_ID);

  public static final Supplier<CreativeModeTab> ITEMS_TAB =
      CREATIVE_MODE_TABS.register(
          "items_tab",
          () ->
              CreativeModeTab.builder()
                  .icon(() -> new ItemStack(ModItems.CEBBITE_INGOT.get()))
                  .title(Component.translatable("itemGroup.eldritch_arcanum.items"))
                  .displayItems(
                      (itemDisplayParameters, output) -> {
                        ModItems.ITEMS.getEntries().stream()
                            .map(Holder::value)
                                .filter(item -> !(item instanceof BlockItem))
                                .filter(item -> !(item instanceof EssenceCrystalItem))
                            .forEach(output::accept);
                      })
                  .build());

    public static final Supplier<CreativeModeTab> BLOCKS_TAB =
            CREATIVE_MODE_TABS.register(
                    "blocks_tab",
                    () ->
                            CreativeModeTab.builder()
                                    .icon(() -> new ItemStack(ModBlocks.CEBBITE_ORE.get()))
                                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, "items_tab"))
                                    .title(Component.translatable("itemGroup.eldritch_arcanum.blocks"))
                                    .displayItems(
                                            (itemDisplayParameters, output) -> {
                                                ModBlocks.BLOCKS.getEntries().stream()
                                                        .map(Holder::value)
                                                        .forEach(output::accept);
                                            })
                                    .build());
    public static final Supplier<CreativeModeTab> CRYSTALS_TAB =
            CREATIVE_MODE_TABS.register(
                    "crystals_tab",
                    () ->
                            CreativeModeTab.builder()
                                    .icon(() -> new ItemStack(ModItems.ESSENCE_CRYSTAL.get()))
                                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, "blocks_tab"))
                                    .title(Component.translatable("itemGroup.eldritch_arcanum.crystals"))
                                    .displayItems((params, output) -> {
                                            Registry<Essence> reg = ModRegistries.ESSENCE_REGISTRY;

                                            for(Essence essence : reg.stream().toList()){
                                                ItemStack stack = new ItemStack(ModItems.ESSENCE_CRYSTAL.get());
                                                ((EssenceCrystalItem)stack.getItem()).bindToEssence(stack, essence);
                                                output.accept(stack);
                                            }
                                    }

                                    ).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
