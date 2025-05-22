package net.undertaker.eldritch_arcanum.items.custom.adamantite;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.undertaker.eldritch_arcanum.util.ModTiers;

public class AdamantiteHoeItem extends HoeItem {
    public AdamantiteHoeItem() {
        super(ModTiers.ADAMANTITE, new Properties().rarity(Rarity.RARE).attributes(createAttributes(ModTiers.ADAMANTITE, -4, -1f)));
    }
}
