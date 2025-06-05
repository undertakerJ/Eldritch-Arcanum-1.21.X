package net.undertaker.eldritch_arcanum.items.custom.adamantite;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Rarity;
import net.undertaker.eldritch_arcanum.items.ModToolTiers;

public class AdamantiteHoeItem extends HoeItem {
    public AdamantiteHoeItem() {
        super(ModToolTiers.ADAMANTITE, new Properties().rarity(Rarity.RARE).attributes(createAttributes(ModToolTiers.ADAMANTITE, -4, -1f)));
    }
}
