package net.undertaker.eldritch_arcanum.items.custom.adamantite;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Rarity;
import net.undertaker.eldritch_arcanum.items.ModToolTiers;

public class AdamantiteAxeItem extends AxeItem {
    public AdamantiteAxeItem() {
        super(ModToolTiers.ADAMANTITE, new Properties().rarity(Rarity.RARE).attributes(createAttributes(ModToolTiers.ADAMANTITE,4, -3f)));
    }
}
