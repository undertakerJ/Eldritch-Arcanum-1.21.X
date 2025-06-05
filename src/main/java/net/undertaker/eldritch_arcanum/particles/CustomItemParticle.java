package net.undertaker.eldritch_arcanum.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class CustomItemParticle extends TextureSheetParticle {
  private final Vec3 target;

  private double angle;

  private double radius;

  private final double rotationSpeed;

  public CustomItemParticle(
      ClientLevel level,
      double x,
      double y,
      double z,
      ItemStack itemStack,
      double targetX,
      double targetY,
      double targetZ) {
    super(level, x, y, z, targetX, targetY, targetZ);
    this.target = new Vec3(targetX, targetY, targetZ);
    this.angle = Math.random() * Math.PI * 2;
    double dx = x - targetX;
    double dy = y - targetY;
    double dz = z - targetZ;
    this.radius = Math.sqrt(dx * dx + dy * dy + dz * dz);
    this.rotationSpeed = 0.1 + (Math.random() * 0.05);

    this.lifetime = 60 + this.random.nextInt(20);

    this.setSpriteFromItem(itemStack);

    this.quadSize = 0.3f + this.random.nextFloat() * 0.2f;
  }

  @Override
  public void tick() {
    super.tick();

    if (this.age++ >= this.lifetime) {
      this.remove();
      return;
    }

    this.angle += this.rotationSpeed;

    // Пересчитываем координаты на окружности
    double offsetX = Math.cos(this.angle) * this.radius;
    double offsetZ = Math.sin(this.angle) * this.radius;

    // Вычисляем, где должна быть частица сейчас (ось Y берем ближе к центру)
    double newX = this.target.x + offsetX;
    double newY = this.target.y + (this.target.y - this.y) * 0.1;
    // слегка поднимать/опускать по Y, чтобы не «застревать» на одной плоскости
    double newZ = this.target.z + offsetZ;

    // Приводим к «скорости» для плавного перемещения.
    // Чем дальше текущая позиция от newX, тем выше скорость.
    double dx = newX - this.x;
    double dy = newY - this.y;
    double dz = newZ - this.z;

    // Можно регулировать «жёсткость» эффекта: 0.1–0.2 → довольно плавно
    double speedFactor = 0.15;

    this.xd += dx * speedFactor;
    this.yd += dy * speedFactor;
    this.zd += dz * speedFactor;
  }

  @Override
  public ParticleRenderType getRenderType() {
    return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
  }

  private void setSpriteFromItem(ItemStack stack) {
    TextureAtlasSprite sprite =
        Minecraft.getInstance()
            .getItemRenderer()
            .getItemModelShaper()
            .getItemModel(stack)
            .getParticleIcon();
    this.setSprite(sprite);
  }

  public static class Provider implements ParticleProvider<ItemParticleOption> {
    private final SpriteSet spriteSet;

    public Provider(SpriteSet spriteSet) {
      this.spriteSet = spriteSet;
    }

    @Override
    public @Nullable Particle createParticle(
        ItemParticleOption itemParticleOption,
        ClientLevel clientLevel,
        double x,
        double y,
        double z,
        double vx,
        double vy,
        double vz) {

      ItemStack stack = itemParticleOption.getItem();

      return new CustomItemParticle(
          clientLevel,
          x,
          y,
          z,
          stack,
          vx,
          vy,
          vz);
    }
  }
}
