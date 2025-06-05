package net.undertaker.eldritch_arcanum.networking.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.undertaker.eldritch_arcanum.blocks.entity.PedestalEntity;

public class ClientPayloadHandler {
    public static void handleDataOnMain(final ParticlePacket data, final IPayloadContext context) {
       Level level = context.player().level();
        if(level.isClientSide()){
           BlockPos altarPos = parseBlockPos(data.altar());
           BlockPos pedestalPos = parseBlockPos(data.pedestal());
      context
          .enqueueWork(
              () -> {
                BlockEntity blockEntity = level.getBlockEntity(pedestalPos);
                if (!(blockEntity instanceof PedestalEntity pedestal)) return;
                ItemStack itemStack = pedestal.getItem();
                  Vec3 start  = Vec3.atCenterOf(pedestalPos);
                  Vec3 end    = Vec3.atCenterOf(altarPos);
                  Vec3 raw    = end.subtract(start);
                  double dist = raw.length();
                  double travelTicks = 10.0;
                  double speed = dist / travelTicks;
                  Vec3 motion = raw.normalize().scale(speed);
                level.addAlwaysVisibleParticle(
                    new ItemParticleOption(ParticleTypes.ITEM, itemStack),
                    start.x,
                    start.y,
                    start.z,
                    motion.x,
                    motion.y+0.25,
                    motion.z);
              })
          .exceptionally(
              throwable -> {
                context.disconnect(Component.literal(throwable.getCause().toString()));
                return null;
              });
        }
    }

    private static BlockPos parseBlockPos(String str) {
        String[] parts = str.split(",");
        return new BlockPos(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2])
        );
    }

}
