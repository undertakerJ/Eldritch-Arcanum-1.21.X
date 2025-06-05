package net.undertaker.eldritch_arcanum.items.custom.adamantite;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.undertaker.eldritch_arcanum.items.ModToolTiers;

public class AdamantitePickaxeItem extends PickaxeItem {
    public AdamantitePickaxeItem() {
        super(ModToolTiers.ADAMANTITE, new Properties().rarity(Rarity.RARE).attributes(createAttributes(ModToolTiers.ADAMANTITE, 1, -3f)));
    }
}
