package net.undertaker.eldritch_arcanum.items.custom.cebbite;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.undertaker.eldritch_arcanum.util.ModTiers;

public class CebbiteShovelItem extends ShovelItem {
  public CebbiteShovelItem() {
    super(
        ModTiers.CEBBITE,
        new Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModTiers.CEBBITE, 1, -3)));
  }
}
