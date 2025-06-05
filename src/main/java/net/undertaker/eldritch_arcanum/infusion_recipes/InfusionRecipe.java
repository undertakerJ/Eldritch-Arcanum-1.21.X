package net.undertaker.eldritch_arcanum.infusion_recipes;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.undertaker.eldritch_arcanum.essence.Essence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;


public interface InfusionRecipe  {
    /**
     * @param pedestalItems Список предметов на пьедесталах (непустые стеки)
     * @param catalyst Предмет в самом алтаре
     * @param level Уровень (для проверки времени, биома и т.д.)
     * @param availableEssences Нужное количество эссенции для крафта.
     */
    boolean matches(List<ItemStack> pedestalItems, ItemStack catalyst, Map<Essence, Integer> availableEssences, Level level);

    ItemStack getResult();

    Map<Essence, Integer> getRequiredEssences();

    float getInstability();

    ResourceLocation getId();

    void onCraft(Level level, BlockPos altarPos);
}