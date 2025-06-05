package net.undertaker.eldritch_arcanum.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.essence.Essence;
import net.undertaker.eldritch_arcanum.infusion_recipes.InfusionRecipe;

@EventBusSubscriber(modid = EldritchArcanum.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModRegistries {
  public static final ResourceKey<Registry<InfusionRecipe>> INFUSION_RECIPES_REGISTRY_KEY =
      ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, "infusion_recipes"));
    public static final ResourceKey<Registry<Essence>> ESSENCE_REGISTRY_KEY =
      ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, "essences"));


  public static final Registry<InfusionRecipe> INFUSION_RECIPE_REGISTRY = new RegistryBuilder<>(INFUSION_RECIPES_REGISTRY_KEY)
          .sync(true)
          .defaultKey(ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, "empty"))
          .maxId(256)
          .create();

    public static final Registry<Essence> ESSENCE_REGISTRY = new RegistryBuilder<>(ESSENCE_REGISTRY_KEY)
          .sync(true)
          .defaultKey(ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, "empty"))
            .maxId(1024)
          .create();

    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event) {
        event.register(INFUSION_RECIPE_REGISTRY);
        event.register(ESSENCE_REGISTRY);
    }
}
