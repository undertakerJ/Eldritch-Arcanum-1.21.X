package net.undertaker.eldritch_arcanum.items.custom.cebbite;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.undertaker.eldritch_arcanum.items.ModToolTiers;

public class CebbiteShovelItem extends ShovelItem {
  public CebbiteShovelItem() {
    super(
        ModToolTiers.CEBBITE,
        new Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModToolTiers.CEBBITE, 1, -3)));
  }
}
