package net.undertaker.eldritch_arcanum.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.undertaker.eldritch_arcanum.EldritchArcanum;

public record ParticlePacket(String altar, String pedestal) implements CustomPacketPayload {

  public static final CustomPacketPayload.Type<ParticlePacket> TYPE =
      new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, "particle_packet"));

  public static final StreamCodec<ByteBuf, ParticlePacket> STREAM_CODEC = StreamCodec.composite(
          ByteBufCodecs.STRING_UTF8,
          ParticlePacket::altar,
          ByteBufCodecs.STRING_UTF8,
          ParticlePacket::pedestal,
          ParticlePacket::new
  );


  @Override
  public Type<? extends CustomPacketPayload> type() {
    return TYPE;
  }
}
