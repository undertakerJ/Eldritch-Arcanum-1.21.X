package net.undertaker.eldritch_arcanum.datagen;


import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.items.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,  ExistingFileHelper existingFileHelper) {
        super(output, EldritchArcanum.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_CEBBITE_ORE.get());
        basicItem(ModItems.CEBBITE_INGOT.get());
        basicItem(ModItems.RAW_ADAMANTITE.get());
        basicItem(ModItems.ADAMANTITE_INGOT.get());
        basicItem(ModItems.VELESCIL_WOOD_HANDLE.get());

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


    }
}
