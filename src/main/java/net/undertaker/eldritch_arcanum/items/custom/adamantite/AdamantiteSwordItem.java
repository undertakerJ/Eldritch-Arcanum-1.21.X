package net.undertaker.eldritch_arcanum.items.custom.adamantite;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.undertaker.eldritch_arcanum.util.ModTiers;

public class AdamantiteSwordItem extends SwordItem {
  public AdamantiteSwordItem() {
    super(
        ModTiers.ADAMANTITE,
        new Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModTiers.ADAMANTITE, 3, -2.2f)));
  }
}
