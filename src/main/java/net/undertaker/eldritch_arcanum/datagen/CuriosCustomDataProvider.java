package net.undertaker.eldritch_arcanum.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.concurrent.CompletableFuture;

public class CuriosCustomDataProvider extends CuriosDataProvider {
    public CuriosCustomDataProvider(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(EldritchArcanum.MOD_ID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        createSlot("head_slot")
                .size(2)
                .dropRule(ICurio.DropRule.ALWAYS_DROP)
                .renderToggle(true);
        createEntities("entities")
                .addPlayer()
                .addSlots("head_slot");
    }
}
