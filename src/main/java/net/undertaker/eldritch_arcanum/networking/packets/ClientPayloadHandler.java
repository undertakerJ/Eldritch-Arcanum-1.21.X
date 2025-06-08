package net.undertaker.eldritch_arcanum.networking.packets;

import net.minecraft.client.multiplayer.ClientLevel;
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
import net.undertaker.eldritch_arcanum.particles.CustomItemParticle;
import net.undertaker.eldritch_arcanum.particles.ModParticles;

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
                  double sx = pedestalPos.getX() + 0.5;
                  double sy = pedestalPos.getY() + 0.5;
                  double sz = pedestalPos.getZ() + 0.5;
                level.addParticle(new ItemParticleOption(ModParticles.ITEM_SWIRL.get(), itemStack),
                        true, sx, sy, sz,   // точка спавна// count (одноиспользование)
                        0,0,0); // разброс по XYZ = 0 (мы сами считаем движени);
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
