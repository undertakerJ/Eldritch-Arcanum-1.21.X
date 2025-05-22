package net.undertaker.eldritch_arcanum.items.custom.adamantite;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.undertaker.eldritch_arcanum.util.ModTiers;

public class AdamantiteAxeItem extends AxeItem {
    public AdamantiteAxeItem() {
        super(ModTiers.ADAMANTITE, new Properties().rarity(Rarity.RARE).attributes(createAttributes(ModTiers.ADAMANTITE,4, -3f)));
    }
}
