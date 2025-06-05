package net.undertaker.eldritch_arcanum.items.custom.adamantite;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.undertaker.eldritch_arcanum.items.ModToolTiers;

public class AdamantiteShovelItem extends ShovelItem {
    public AdamantiteShovelItem() {
        super(ModToolTiers.ADAMANTITE, new Properties().rarity(Rarity.RARE).attributes(createAttributes(ModToolTiers.ADAMANTITE,0, -3f)));
    }
}
