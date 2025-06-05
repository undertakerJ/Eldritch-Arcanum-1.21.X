package net.undertaker.eldritch_arcanum.networking;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handlers.ServerPayloadHandler;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.networking.packets.ClientPayloadHandler;
import net.undertaker.eldritch_arcanum.networking.packets.ParticlePacket;

@EventBusSubscriber(modid = EldritchArcanum.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModNetworking {
  @SubscribeEvent
  public static void register(final RegisterPayloadHandlersEvent event) {
    final PayloadRegistrar registrar = event.registrar("1");

    registrar.playToClient(
        ParticlePacket.TYPE, ParticlePacket.STREAM_CODEC, ClientPayloadHandler::handleDataOnMain);
  }
}
