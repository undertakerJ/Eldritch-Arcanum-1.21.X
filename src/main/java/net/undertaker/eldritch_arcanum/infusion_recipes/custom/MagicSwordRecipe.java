package net.undertaker.eldritch_arcanum.infusion_recipes.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.essence.Essence;
import net.undertaker.eldritch_arcanum.essence.ModEssences;
import net.undertaker.eldritch_arcanum.infusion_recipes.InfusionRecipe;
import net.undertaker.eldritch_arcanum.items.ModItems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagicSwordRecipe implements InfusionRecipe {
    private static final Map<Essence, Integer> REQUIRED_ESSENCES = Map.of(
            ModEssences.VOID_ESSENCE.get(), 4,
            ModEssences.LUNAR_ESSENCE.get(), 8
    );

    @Override
    public boolean matches(List<ItemStack> pedestalItems, ItemStack catalyst, Map<Essence, Integer> availableEssences , Level level) {
        if (catalyst.getItem() != Items.DIAMOND_SWORD) return false;
        // Проверка пьедесталов: 4 изумруда и 2 огненных порошка
        long emeralds = pedestalItems.stream()
                .filter(s -> s.is(Items.EMERALD))
                .count();
        long blazePowder = pedestalItems.stream()
                .filter(s -> s.is(Items.BLAZE_POWDER))
                .count();

        return emeralds >= 4 && blazePowder >= 2;
    }


    @Override
    public ItemStack getResult() {
        return new ItemStack(ModItems.ADAMANTITE_SWORD.get());
    }

    @Override
    public float getInstability() {
        return 0.3f;
    }

    @Override
    public ResourceLocation getId() {
        return ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, "magic_sword");
    }

    @Override
    public Map<Essence, Integer> getRequiredEssences() {
        return REQUIRED_ESSENCES;
    }

    @Override
    public void onCraft(Level level, BlockPos altarPos) {
        level.explode(null, altarPos.getX(), altarPos.getY(), altarPos.getZ(), 2.0f, Level.ExplosionInteraction.NONE);
    }
}