package net.undertaker.eldritch_arcanum.items;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.items.custom.EssenceCrystalItem;
import net.undertaker.eldritch_arcanum.items.custom.adamantite.*;
import net.undertaker.eldritch_arcanum.items.custom.cebbite.*;

public class ModItems {
  public static final DeferredRegister.Items ITEMS =
      DeferredRegister.createItems(EldritchArcanum.MOD_ID);

  public static final DeferredItem<Item> RAW_CEBBITE_ORE =
      ITEMS.register("raw_cebbite_ore", () -> new Item(new Item.Properties()));
  public static final DeferredItem<Item> CEBBITE_INGOT =
      ITEMS.register("cebbite_ingot", () -> new Item(new Item.Properties()));
  public static final DeferredItem<Item> RAW_ADAMANTITE =
      ITEMS.register("raw_adamantite", () -> new Item(new Item.Properties()));
  public static final DeferredItem<Item> ADAMANTITE_INGOT =
      ITEMS.register("adamantite_ingot", () -> new Item(new Item.Properties()));

  public static final DeferredItem<Item> VELESCIL_WOOD_HANDLE =
      ITEMS.register("velescil_wood_handle", () -> new Item(new Item.Properties()));

  public static final DeferredItem<Item> CEBBITE_SWORD =
      ITEMS.register("cebbite_sword", CebbiteSwordItem::new);
  public static final DeferredItem<Item> CEBBITE_AXE =
      ITEMS.register("cebbite_axe", CebbiteAxeItem::new);
  public static final DeferredItem<Item> CEBBITE_PICKAXE =
      ITEMS.register("cebbite_pickaxe", CebbitePickaxeItem::new);
  public static final DeferredItem<Item> CEBBITE_SHOVEL =
      ITEMS.register("cebbite_shovel", CebbiteShovelItem::new);
  public static final DeferredItem<Item> CEBBITE_HOE =
      ITEMS.register("cebbite_hoe", CebbiteHoeItem::new);

  public static final DeferredItem<ArmorItem> CEBBITE_HELMET =
      ITEMS.register(
          "cebbite_helmet",
          () ->
              new ArmorItem(
                  ModArmorMaterials.CEBBITE_ARMOR_MATERIAL,
                  ArmorItem.Type.HELMET,
                  new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
public static final DeferredItem<ArmorItem> CEBBITE_CHESTPLATE =
      ITEMS.register(
          "cebbite_chestplate",
          () ->
              new ArmorItem(
                  ModArmorMaterials.CEBBITE_ARMOR_MATERIAL,
                  ArmorItem.Type.CHESTPLATE,
                  new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
public static final DeferredItem<ArmorItem> CEBBITE_LEGGINGS =
      ITEMS.register(
          "cebbite_leggings",
          () ->
              new ArmorItem(
                  ModArmorMaterials.CEBBITE_ARMOR_MATERIAL,
                  ArmorItem.Type.LEGGINGS,
                  new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
public static final DeferredItem<ArmorItem> CEBBITE_BOOTS =
      ITEMS.register(
          "cebbite_boots",
          () ->
              new ArmorItem(
                  ModArmorMaterials.CEBBITE_ARMOR_MATERIAL,
                  ArmorItem.Type.BOOTS,
                  new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));


  public static final DeferredItem<Item> ADAMANTITE_SWORD =
      ITEMS.register("adamantite_sword", AdamantiteSwordItem::new);
  public static final DeferredItem<Item> ADAMANTITE_AXE =
      ITEMS.register("adamantite_axe", AdamantiteAxeItem::new);
  public static final DeferredItem<Item> ADAMANTITE_PICKAXE =
      ITEMS.register("adamantite_pickaxe", AdamantitePickaxeItem::new);
  public static final DeferredItem<Item> ADAMANTITE_SHOVEL =
      ITEMS.register("adamantite_shovel", AdamantiteShovelItem::new);
  public static final DeferredItem<Item> ADAMANTITE_HOE =
      ITEMS.register("adamantite_hoe", AdamantiteHoeItem::new);

  public static final DeferredItem<ArmorItem> ADAMANTITE_HELMET =
          ITEMS.register(
                  "adamantite_helmet",
                  () ->
                          new ArmorItem(
                                  ModArmorMaterials.ADAMANTITE_ARMOR_MATERIAL,
                                  ArmorItem.Type.HELMET,
                                  new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(29))));
  public static final DeferredItem<ArmorItem> ADAMANTITE_CHESTPLATE =
          ITEMS.register(
                  "adamantite_chestplate",
                  () ->
                          new ArmorItem(
                                  ModArmorMaterials.ADAMANTITE_ARMOR_MATERIAL,
                                  ArmorItem.Type.CHESTPLATE,
                                  new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(29))));
  public static final DeferredItem<ArmorItem> ADAMANTITE_LEGGINGS =
          ITEMS.register(
                  "adamantite_leggings",
                  () ->
                          new ArmorItem(
                                  ModArmorMaterials.ADAMANTITE_ARMOR_MATERIAL,
                                  ArmorItem.Type.LEGGINGS,
                                  new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(29))));
  public static final DeferredItem<ArmorItem> ADAMANTITE_BOOTS =
          ITEMS.register(
                  "adamantite_boots",
                  () ->
                          new ArmorItem(
                                  ModArmorMaterials.ADAMANTITE_ARMOR_MATERIAL,
                                  ArmorItem.Type.BOOTS,
                                  new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(29))));

  public static final DeferredItem<Item> ESSENCE_CRYSTAL =
          ITEMS.register("essence_crystal", EssenceCrystalItem::new);

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
