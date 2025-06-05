package net.undertaker.eldritch_arcanum;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.undertaker.eldritch_arcanum.blocks.ModBlocks;
import net.undertaker.eldritch_arcanum.blocks.entity.ModBlockEntities;
import net.undertaker.eldritch_arcanum.essence.ModEssences;
import net.undertaker.eldritch_arcanum.infusion_recipes.InfusionRecipes;
import net.undertaker.eldritch_arcanum.items.ModCreativeTabs;
import net.undertaker.eldritch_arcanum.items.ModItems;
import net.undertaker.eldritch_arcanum.particles.ModParticles;
import net.undertaker.eldritch_arcanum.util.ModDataComponents;
import org.slf4j.Logger;

@Mod(EldritchArcanum.MOD_ID)
public class EldritchArcanum {
  public static final String MOD_ID = "eldritch_arcanum";
  private static final Logger LOGGER = LogUtils.getLogger();

  public EldritchArcanum(IEventBus modEventBus, ModContainer modContainer) {
    modEventBus.addListener(this::commonSetup);
    NeoForge.EVENT_BUS.register(this);

    ModItems.register(modEventBus);
    ModBlocks.register(modEventBus);
    ModCreativeTabs.register(modEventBus);
    ModBlockEntities.register(modEventBus);
    InfusionRecipes.register(modEventBus);
    ModEssences.register(modEventBus);
    ModDataComponents.register(modEventBus);
    ModParticles.register(modEventBus);

    modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {

  }


  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {

  }

}
