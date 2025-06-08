package net.undertaker.eldritch_arcanum.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.Nullable;

public class CustomItemParticle extends TextureSheetParticle {
  private final Vec3 target;
  private double angle;
  private double radius;
  private final double rotationSpeed;
  private final float uo;
  private final float vo;

  public CustomItemParticle(
      ClientLevel level,
      double x,
      double y,
      double z,
      ItemStack itemStack,
      double targetX,
      double targetY,
      double targetZ) {

    super(level, x, y, z, 0, 0, 0);

    this.target = new Vec3(targetX, targetY, targetZ);
    this.angle = Math.random() * Math.PI * 2;
    this.radius = Math.sqrt(1.25);

    this.rotationSpeed = 0.1 + (Math.random() * 0.05);

    this.lifetime = 40 + this.random.nextInt(20);

    this.hasPhysics = false;
    this.friction = 1.0f;

    BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(itemStack, level, (LivingEntity)null, 0);
    this.sprite = model.getOverrides().resolve(model, itemStack, level,(LivingEntity)null, 0).getParticleIcon(ModelData.EMPTY);
    this.uo = this.random.nextFloat() * 3.0F;
    this.vo = this.random.nextFloat() * 3.0F;
    this.quadSize = 0.05f;
  }

  protected float getU0() {
    return this.sprite.getU((this.uo + 1.0F) / 4.0F);
  }

  protected float getU1() {
    return this.sprite.getU(this.uo / 4.0F);
  }

  protected float getV0() {
    return this.sprite.getV(this.vo / 4.0F);
  }

  protected float getV1() {
    return this.sprite.getV((this.vo + 1.0F) / 4.0F);
  }

  @Override
  public void tick() {
    this.xo = this.x;
    this.yo = this.y;
    this.zo = this.z;

    if (this.age++ >= this.lifetime) {
      this.remove();
      return;
    }

    this.angle += this.rotationSpeed;
    this.radius *= 0.95;

    double offsetX = Math.cos(this.angle) * this.radius;
    double offsetZ = Math.sin(this.angle) * this.radius;

    this.x = this.target.x + offsetX;
    this.y = this.target.y + 0.5;
    this.z = this.target.z + offsetZ;

    this.xd = 0;
    this.yd = 0;
    this.zd = 0;

    float fadeProgress = (float) this.age / this.lifetime;
    this.alpha = 1.0f - (fadeProgress * fadeProgress);
  }

  @Override
  public ParticleRenderType getRenderType() {
    return ParticleRenderType.TERRAIN_SHEET;
  }

  public static class Provider implements ParticleProvider<ItemParticleOption> {
    public Provider(SpriteSet spriteSet) {
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

      double targetX = x + vx;
      double targetY = y + vy;
      double targetZ = z + vz;

      return new CustomItemParticle(clientLevel, x, y, z, stack, targetX, targetY, targetZ);
    }
  }
}