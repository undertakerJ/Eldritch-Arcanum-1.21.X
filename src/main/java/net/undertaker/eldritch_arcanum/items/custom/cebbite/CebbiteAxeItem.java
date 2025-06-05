package net.undertaker.eldritch_arcanum.items.custom.cebbite;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Rarity;
import net.undertaker.eldritch_arcanum.items.ModToolTiers;

public class CebbiteAxeItem extends AxeItem {

  public CebbiteAxeItem() {
    super(
        ModToolTiers.CEBBITE,
        new Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModToolTiers.CEBBITE, 5, -3.1f)));
  }
}
