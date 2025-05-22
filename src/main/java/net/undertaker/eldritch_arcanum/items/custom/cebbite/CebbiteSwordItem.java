package net.undertaker.eldritch_arcanum.items.custom.cebbite;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.undertaker.eldritch_arcanum.util.ModTiers;

public class CebbiteSwordItem extends SwordItem {

  public CebbiteSwordItem() {
    super(
        ModTiers.CEBBITE,
        new Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModTiers.CEBBITE, 4, -2.4f)));
  }
}
