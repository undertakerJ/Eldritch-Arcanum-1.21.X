package net.undertaker.eldritch_arcanum.blocks.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.blocks.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, EldritchArcanum.MOD_ID);

    public static final Supplier<BlockEntityType<PedestalEntity>> PEDESTAL_ENTITY =
            BLOCK_ENTITIES.register("pedestal_entity", () -> BlockEntityType.Builder.of(
                    PedestalEntity::new, ModBlocks.PEDESTAL.get()).build(null)
            );
    public static final Supplier<BlockEntityType<CopperAltarEntity>> COPPER_ALTAR_ENTITY =
            BLOCK_ENTITIES.register("copper_altar_entity", () -> BlockEntityType.Builder.of(
                    CopperAltarEntity::new, ModBlocks.COPPER_ALTAR.get()).build(null)
            );
    public static final Supplier<BlockEntityType<CrystalClusterEntity>> CRYSTAL_CLUSTER_ENTITY =
            BLOCK_ENTITIES.register("crystal_cluster_entity", () -> BlockEntityType.Builder.of(
                    CrystalClusterEntity::new, ModBlocks.CRYSTAL_CLUSTER.get()).build(null)
            );


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
