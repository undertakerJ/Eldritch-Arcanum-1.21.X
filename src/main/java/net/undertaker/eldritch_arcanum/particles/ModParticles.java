package net.undertaker.eldritch_arcanum.particles;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.undertaker.eldritch_arcanum.EldritchArcanum;

import java.util.function.Supplier;

public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, EldritchArcanum.MOD_ID);

    public static final Supplier<ParticleType<ItemParticleOption>> ITEM_SWIRL =
            PARTICLE_TYPES.register("item_swirl", () -> new ParticleType<ItemParticleOption>(true) {
                @Override
                public MapCodec<ItemParticleOption> codec() {
                    return ItemParticleOption.codec(this);
                }

                @Override
                public StreamCodec<? super RegistryFriendlyByteBuf, ItemParticleOption> streamCodec() {
                    return ItemParticleOption.streamCodec(this);
                }
            });

    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}
