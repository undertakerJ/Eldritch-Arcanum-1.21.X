package net.undertaker.eldritch_arcanum.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.blocks.ModBlocks;
import net.undertaker.eldritch_arcanum.items.ModItems;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
  public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
    super(output, registries);
  }

  @Override
  protected void buildRecipes(RecipeOutput recipeOutput) {
    List<ItemLike> CEBBITE_SMELTABLES =
        List.of(
            ModItems.RAW_CEBBITE_ORE,
            ModBlocks.CEBBITE_ORE,
            ModBlocks.DEEPSLATE_CEBBITE_ORE,
            ModBlocks.SCULK_CEBBITE_ORE);
    List<ItemLike> ADAMANTITE_SMELTABLES =
        List.of(ModItems.RAW_ADAMANTITE, ModBlocks.ADAMANTITE_ORE);

    oreSmelting(
        recipeOutput,
        CEBBITE_SMELTABLES,
        RecipeCategory.MISC,
        ModItems.CEBBITE_INGOT.get(),
        0.25f,
        200,
        "cebbite");
    oreBlasting(
        recipeOutput,
        CEBBITE_SMELTABLES,
        RecipeCategory.MISC,
        ModItems.CEBBITE_INGOT.get(),
        0.25f,
        100,
        "cebbite");
    oreSmelting(
        recipeOutput,
        ADAMANTITE_SMELTABLES,
        RecipeCategory.MISC,
        ModItems.ADAMANTITE_INGOT.get(),
        0.25f,
        200,
        "adamantite");
    oreBlasting(
        recipeOutput,
        ADAMANTITE_SMELTABLES,
        RecipeCategory.MISC,
        ModItems.ADAMANTITE_INGOT.get(),
        0.25f,
        100,
        "adamantite");

    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CEBBITE_INGOT.get(), 9)
        .requires(ModBlocks.CEBBITE_BLOCK)
        .unlockedBy("has_cebbite_block", has(ModBlocks.CEBBITE_BLOCK))
        .save(recipeOutput);
    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ADAMANTITE_INGOT.get(), 9)
        .requires(ModBlocks.ADAMANTITE_BLOCK)
        .unlockedBy("has_adamantite_block", has(ModBlocks.ADAMANTITE_BLOCK))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ADAMANTITE_BLOCK)
        .pattern("###")
        .pattern("###")
        .pattern("###")
        .define('#', ModItems.ADAMANTITE_INGOT.get())
        .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CEBBITE_BLOCK)
        .pattern("###")
        .pattern("###")
        .pattern("###")
        .define('#', ModItems.CEBBITE_INGOT.get())
        .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CEBBITE_PICKAXE.get())
        .pattern("###")
        .pattern(" S ")
        .pattern(" S ")
        .define('#', ModItems.CEBBITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CEBBITE_AXE.get())
        .pattern("##")
        .pattern("#S")
        .pattern(" S")
        .define('#', ModItems.CEBBITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CEBBITE_AXE.get())
        .pattern("##")
        .pattern("S#")
        .pattern("S ")
        .define('#', ModItems.CEBBITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput, EldritchArcanum.MOD_ID + ":cebbite_axe_reversed");
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CEBBITE_SHOVEL.get())
        .pattern("#")
        .pattern("S")
        .pattern("S")
        .define('#', ModItems.CEBBITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CEBBITE_HOE.get())
        .pattern("##")
        .pattern(" S")
        .pattern(" S")
        .define('#', ModItems.CEBBITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CEBBITE_HOE.get())
        .pattern("##")
        .pattern("S ")
        .pattern("S ")
        .define('#', ModItems.CEBBITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput,EldritchArcanum.MOD_ID + ":cebbite_hoe_reversed");
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CEBBITE_SWORD.get())
        .pattern("#")
        .pattern("#")
        .pattern("S")
        .define('#', ModItems.CEBBITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADAMANTITE_PICKAXE.get())
        .pattern("###")
        .pattern(" S ")
        .pattern(" S ")
        .define('#', ModItems.ADAMANTITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADAMANTITE_AXE.get())
        .pattern("##")
        .pattern("#S")
        .pattern(" S")
        .define('#', ModItems.ADAMANTITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADAMANTITE_AXE.get())
        .pattern("##")
        .pattern("S#")
        .pattern("S ")
        .define('#', ModItems.ADAMANTITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput, EldritchArcanum.MOD_ID + ":adamantite_axe_reversed");
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADAMANTITE_SHOVEL.get())
        .pattern("#")
        .pattern("S")
        .pattern("S")
        .define('#', ModItems.ADAMANTITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADAMANTITE_HOE.get())
        .pattern("##")
        .pattern(" S")
        .pattern(" S")
        .define('#', ModItems.ADAMANTITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADAMANTITE_HOE.get())
        .pattern("##")
        .pattern("S ")
        .pattern("S ")
        .define('#', ModItems.ADAMANTITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput,EldritchArcanum.MOD_ID + ":adamantite_hoe_reversed");
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADAMANTITE_SWORD.get())
        .pattern("#")
        .pattern("#")
        .pattern("S")
        .define('#', ModItems.ADAMANTITE_INGOT.get())
        .define('S', ModItems.VELESCIL_WOOD_HANDLE.get())
        .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADAMANTITE_HELMET)
            .pattern("###")
            .pattern("# #")
            .define('#', ModItems.ADAMANTITE_INGOT.get())
            .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
            .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADAMANTITE_CHESTPLATE)
            .pattern("# #")
            .pattern("###")
            .pattern("###")
            .define('#', ModItems.ADAMANTITE_INGOT.get())
            .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
            .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADAMANTITE_LEGGINGS)
            .pattern("###")
            .pattern("# #")
            .pattern("# #")
            .define('#', ModItems.ADAMANTITE_INGOT.get())
            .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
            .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADAMANTITE_BOOTS)
            .pattern("# #")
            .pattern("# #")
            .define('#', ModItems.ADAMANTITE_INGOT.get())
            .unlockedBy("has_adamantite", has(ModItems.ADAMANTITE_INGOT.get()))
            .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CEBBITE_HELMET)
            .pattern("###")
            .pattern("# #")
            .define('#', ModItems.CEBBITE_INGOT.get())
            .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
            .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CEBBITE_CHESTPLATE)
            .pattern("# #")
            .pattern("###")
            .pattern("###")
            .define('#', ModItems.CEBBITE_INGOT.get())
            .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
            .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CEBBITE_LEGGINGS)
            .pattern("###")
            .pattern("# #")
            .pattern("# #")
            .define('#', ModItems.CEBBITE_INGOT.get())
            .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
            .save(recipeOutput);
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CEBBITE_BOOTS)
            .pattern("# #")
            .pattern("# #")
            .define('#', ModItems.CEBBITE_INGOT.get())
            .unlockedBy("has_cebbite", has(ModItems.CEBBITE_INGOT.get()))
            .save(recipeOutput);
  }

  protected static void oreSmelting(
      RecipeOutput recipeOutput,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTIme,
      String pGroup) {
    oreCooking(
        recipeOutput,
        RecipeSerializer.SMELTING_RECIPE,
        SmeltingRecipe::new,
        pIngredients,
        pCategory,
        pResult,
        pExperience,
        pCookingTIme,
        pGroup,
        "_from_smelting");
  }

  protected static void oreBlasting(
      RecipeOutput recipeOutput,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTime,
      String pGroup) {
    oreCooking(
        recipeOutput,
        RecipeSerializer.BLASTING_RECIPE,
        BlastingRecipe::new,
        pIngredients,
        pCategory,
        pResult,
        pExperience,
        pCookingTime,
        pGroup,
        "_from_blasting");
  }

  protected static <T extends AbstractCookingRecipe> void oreCooking(
      RecipeOutput recipeOutput,
      RecipeSerializer<T> pCookingSerializer,
      AbstractCookingRecipe.Factory<T> factory,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTime,
      String pGroup,
      String pRecipeName) {
    for (ItemLike itemlike : pIngredients) {
      SimpleCookingRecipeBuilder.generic(
              Ingredient.of(itemlike),
              pCategory,
              pResult,
              pExperience,
              pCookingTime,
              pCookingSerializer,
              factory)
          .group(pGroup)
          .unlockedBy(getHasName(itemlike), has(itemlike))
          .save(
              recipeOutput,
              EldritchArcanum.MOD_ID
                  + ":"
                  + getItemName(pResult)
                  + pRecipeName
                  + "_"
                  + getItemName(itemlike));
    }
  }
}
