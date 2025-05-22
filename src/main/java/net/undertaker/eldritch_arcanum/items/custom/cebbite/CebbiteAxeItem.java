package net.undertaker.eldritch_arcanum.items.custom.cebbite;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Rarity;
import net.undertaker.eldritch_arcanum.util.ModTiers;

public class CebbiteAxeItem extends AxeItem {

  public CebbiteAxeItem() {
    super(
        ModTiers.CEBBITE,
        new Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModTiers.CEBBITE, 5, -3.1f)));
  }
}
