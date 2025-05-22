package net.undertaker.eldritch_arcanum.items.custom.adamantite;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.undertaker.eldritch_arcanum.util.ModTiers;

public class AdamantiteShovelItem extends ShovelItem {
    public AdamantiteShovelItem() {
        super(ModTiers.ADAMANTITE, new Properties().rarity(Rarity.RARE).attributes(createAttributes(ModTiers.ADAMANTITE,0, -3f)));
    }
}
