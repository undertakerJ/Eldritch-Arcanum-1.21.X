package net.undertaker.eldritch_arcanum.essence;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.infusion_recipes.InfusionRecipe;
import net.undertaker.eldritch_arcanum.util.ModRegistries;

import java.util.function.Supplier;

public class ModEssences {
    public static final DeferredRegister<Essence> ESSENCES =
            DeferredRegister.create(ModRegistries.ESSENCE_REGISTRY, EldritchArcanum.MOD_ID);

    public static final Supplier<Essence> VOID_ESSENCE = registerEssence(
            "void_essence", 0xFF2A0A4A);

    public static final Supplier<Essence> LUNAR_ESSENCE = registerEssence(
            "lunar_essence", 0xFF8FAEFF);

    private static Supplier<Essence> registerEssence(String name, int color) {
        return ESSENCES.register(name, () ->
                new Essence(ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, name), color));
    }

    public static void register(IEventBus eventBus){
        ESSENCES.register(eventBus);
    }
}
