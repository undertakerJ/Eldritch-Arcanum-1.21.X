package net.undertaker.eldritch_arcanum.items.custom.cebbite;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.undertaker.eldritch_arcanum.items.ModToolTiers;

public class CebbiteSwordItem extends SwordItem {

  public CebbiteSwordItem() {
    super(
        ModToolTiers.CEBBITE,
        new Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModToolTiers.CEBBITE, 4, -2.4f)));
  }
}
