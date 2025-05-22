package net.undertaker.eldritch_arcanum.items.custom.cebbite;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.undertaker.eldritch_arcanum.util.ModTiers;

public class CebbitePickaxeItem extends PickaxeItem {
  public CebbitePickaxeItem() {
    super(
        ModTiers.CEBBITE,
        new Item.Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModTiers.CEBBITE, 1, -3f)));
  }
}
