package net.undertaker.eldritch_arcanum.infusion_recipes;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.infusion_recipes.custom.MagicSwordRecipe;
import net.undertaker.eldritch_arcanum.util.ModRegistries;

import java.util.function.Supplier;

public class InfusionRecipes { // Реестр рецептов
    public static final DeferredRegister<InfusionRecipe> RECIPES =
            DeferredRegister.create(
                    ModRegistries.INFUSION_RECIPE_REGISTRY,
                    EldritchArcanum.MOD_ID
            );

    public static final Supplier<InfusionRecipe> MAGIC_SWORD =
            RECIPES.register(
                    "magic_sword",
                    MagicSwordRecipe::new
            );


    public static void register(IEventBus modBus) {
        RECIPES.register(modBus);
    }
}
