package net.undertaker.eldritch_arcanum.util;


import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.undertaker.eldritch_arcanum.items.ModItems;



public class ModTiers {
  public static final Tier CEBBITE =
      new SimpleTier(
          ModTags.Blocks.INCORRECT_FOR_CEBBITE_TOOL,
          2254,
          12f,
          4f,
          25,
          () -> Ingredient.of(ModItems.CEBBITE_INGOT));
    public static final Tier ADAMANTITE =
      new SimpleTier(
          ModTags.Blocks.INCORRECT_FOR_ADAMANTITE_TOOL,
              1504,15f,5f, 35,
          () -> Ingredient.of(ModItems.ADAMANTITE_INGOT));



}
