package net.undertaker.eldritch_arcanum.items.custom.adamantite;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.undertaker.eldritch_arcanum.items.ModToolTiers;

public class AdamantiteSwordItem extends SwordItem {
  public AdamantiteSwordItem() {
    super(
        ModToolTiers.ADAMANTITE,
        new Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModToolTiers.ADAMANTITE, 3, -2.2f)));
  }
}
