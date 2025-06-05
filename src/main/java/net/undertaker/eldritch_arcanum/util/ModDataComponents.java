package net.undertaker.eldritch_arcanum.util;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.undertaker.eldritch_arcanum.EldritchArcanum;

import java.util.function.Function;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(
                    Registries.DATA_COMPONENT_TYPE,
                    EldritchArcanum.MOD_ID
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> ESSENCE_COMPONENT =
            DATA_COMPONENT_TYPES.registerComponentType(
                    "essence",
                    builder -> builder
                            .persistent(Codec.STRING)
                            .networkSynchronized(ByteBufCodecs.STRING_UTF8)
            );
       public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> ESSENCE_AMOUNT =
            DATA_COMPONENT_TYPES.registerComponentType(
                    "essence_amount",
                    builder -> builder
                            .persistent(Codec.INT)
                            .networkSynchronized(ByteBufCodecs.INT)
            );


    public static void register(IEventBus bus) {
        DATA_COMPONENT_TYPES.register(bus);
    }
}
