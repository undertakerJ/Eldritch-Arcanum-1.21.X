package net.undertaker.eldritch_arcanum.items.custom.adamantite;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.undertaker.eldritch_arcanum.util.ModTiers;

public class AdamantitePickaxeItem extends PickaxeItem {
    public AdamantitePickaxeItem() {
        super(ModTiers.ADAMANTITE, new Properties().rarity(Rarity.RARE).attributes(createAttributes(ModTiers.ADAMANTITE, 1, -3f)));
    }
}
