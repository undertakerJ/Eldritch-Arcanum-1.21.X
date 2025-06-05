package net.undertaker.eldritch_arcanum.util.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.blocks.entity.ModBlockEntities;
import net.undertaker.eldritch_arcanum.blocks.entity.model.CrystalClusterModel;
import net.undertaker.eldritch_arcanum.particles.CustomItemParticle;
import net.undertaker.eldritch_arcanum.particles.ModParticles;

@EventBusSubscriber(modid = EldritchArcanum.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_ENTITY.get(), PedestalRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.COPPER_ALTAR_ENTITY.get(), CopperAltarRenderer::new);
//        event.registerBlockEntityRenderer(ModBlockEntities.CRYSTAL_CLUSTER_ENTITY.get(), CrystalClusterRenderer::new);
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(
                ModBlockEntities.CRYSTAL_CLUSTER_ENTITY.get(),
                CrystalClusterRenderer::new);

    }
    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
                CrystalClusterModel.LAYER_LOCATION,
                CrystalClusterModel::createBodyLayer
        );
    }
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(ModParticles.ITEM_SWIRL.get(), CustomItemParticle.Provider::new);
    }
}
