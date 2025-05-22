package net.undertaker.eldritch_arcanum.items.custom.cebbite;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Rarity;
import net.undertaker.eldritch_arcanum.util.ModTiers;

public class CebbiteHoeItem extends HoeItem {
  public CebbiteHoeItem() {
    super(
        ModTiers.CEBBITE,
        new Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModTiers.CEBBITE, -4, -1)));
  }
}
