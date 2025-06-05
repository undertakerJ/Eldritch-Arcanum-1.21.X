package net.undertaker.eldritch_arcanum.datagen;


import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.items.ModItems;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,  ExistingFileHelper existingFileHelper) {
        super(output, EldritchArcanum.MOD_ID, existingFileHelper);
    }

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_CEBBITE_ORE.get());
        basicItem(ModItems.CEBBITE_INGOT.get());
        basicItem(ModItems.RAW_ADAMANTITE.get());
        basicItem(ModItems.ADAMANTITE_INGOT.get());
        basicItem(ModItems.VELESCIL_WOOD_HANDLE.get());
        basicItem(ModItems.ESSENCE_CRYSTAL.get());

        handheldItem(ModItems.CEBBITE_AXE.get());
        handheldItem(ModItems.CEBBITE_PICKAXE.get());
        handheldItem(ModItems.CEBBITE_SHOVEL.get());
        handheldItem(ModItems.CEBBITE_SWORD.get());
        handheldItem(ModItems.CEBBITE_HOE.get());

        handheldItem(ModItems.ADAMANTITE_AXE.get());
        handheldItem(ModItems.ADAMANTITE_PICKAXE.get());
        handheldItem(ModItems.ADAMANTITE_SHOVEL.get());
        handheldItem(ModItems.ADAMANTITE_SWORD.get());
        handheldItem(ModItems.ADAMANTITE_HOE.get());

        trimmedArmorItem(ModItems.ADAMANTITE_BOOTS);
        trimmedArmorItem(ModItems.ADAMANTITE_LEGGINGS);
        trimmedArmorItem(ModItems.ADAMANTITE_CHESTPLATE);
        trimmedArmorItem(ModItems.ADAMANTITE_HELMET);

        trimmedArmorItem(ModItems.CEBBITE_BOOTS);
        trimmedArmorItem(ModItems.CEBBITE_LEGGINGS);
        trimmedArmorItem(ModItems.CEBBITE_CHESTPLATE);
        trimmedArmorItem(ModItems.CEBBITE_HELMET);

    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        final String MOD_ID = EldritchArcanum.MOD_ID;

        if(itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }
}
