package net.undertaker.eldritch_arcanum.items.custom.cebbite;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.undertaker.eldritch_arcanum.items.ModToolTiers;

public class CebbitePickaxeItem extends PickaxeItem {
  public CebbitePickaxeItem() {
    super(
        ModToolTiers.CEBBITE,
        new Item.Properties()
            .rarity(Rarity.RARE)
            .attributes(createAttributes(ModToolTiers.CEBBITE, 1, -3f)));
  }

}
