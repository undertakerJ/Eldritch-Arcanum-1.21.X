package net.undertaker.eldritch_arcanum.items.custom.cebbite;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Rarity;
import net.undertaker.eldritch_arcanum.items.ModToolTiers;

public class CebbiteHoeItem extends HoeItem {
  public CebbiteHoeItem() {
    super(
        ModToolTiers.CEBBITE,
        new Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModToolTiers.CEBBITE, -4, -1)));
  }
}
