package net.undertaker.eldritch_arcanum.items.custom;

import java.util.Optional;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.undertaker.eldritch_arcanum.essence.Essence;
import net.undertaker.eldritch_arcanum.util.ModDataComponents;
import net.undertaker.eldritch_arcanum.util.ModRegistries;

public class EssenceCrystalItem extends Item {

  public EssenceCrystalItem() {
    super(new Item.Properties().stacksTo(64));
  }

  public void bindToEssence(ItemStack stack, Essence essence) {
    stack.set(ModDataComponents.ESSENCE_COMPONENT.get(), essence.getId().toString());
  }

  public Optional<Essence> getBoundEssence(ItemStack stack) {
    if (!stack.has(ModDataComponents.ESSENCE_COMPONENT.get())) {
      return Optional.empty();
    }
    String idString = stack.get(ModDataComponents.ESSENCE_COMPONENT.get());
    ResourceLocation id = ResourceLocation.tryParse(idString);
    if (id == null) return Optional.empty();
    return Optional.ofNullable(ModRegistries.ESSENCE_REGISTRY.get(id));
  }

  @Override
  public Component getName(ItemStack stack) {
    return getBoundEssence(stack)
        .map(
            essence ->
                Component.translatable("item.eldritch_arcanum.essence_crystal")
                    .append(Component.literal(" ("))
                    .append(essence.getDisplayName())
                    .append(Component.literal(")")))
        .orElseGet(() -> Component.translatable("item.eldritch_arcanum.empty_crystal"));
  }
}
