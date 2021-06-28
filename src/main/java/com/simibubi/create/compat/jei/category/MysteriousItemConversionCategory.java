package com.simibubi.create.compat.jei.category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.compat.jei.ConversionRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingOutput;
import com.simibubi.create.foundation.gui.AllGuiTextures;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.ingredients.IIngredients;

public class MysteriousItemConversionCategory extends CreateRecipeCategory<ConversionRecipe> {

	public static List<ConversionRecipe> getRecipes() {
		List<ConversionRecipe> recipes = new ArrayList<>();
		recipes.add(ConversionRecipe.create(AllItems.EMPTY_BLAZE_BURNER.asStack(), AllBlocks.BLAZE_BURNER.asStack()));
		recipes.add(ConversionRecipe.create(AllItems.CHROMATIC_COMPOUND.asStack(), AllItems.SHADOW_STEEL.asStack()));
		recipes.add(ConversionRecipe.create(AllItems.CHROMATIC_COMPOUND.asStack(), AllItems.REFINED_RADIANCE.asStack()));
		recipes.add(ConversionRecipe.create(AllBlocks.PECULIAR_BELL.asStack(), AllBlocks.CURSED_BELL.asStack()));
		return recipes;
	}

	public MysteriousItemConversionCategory() {
		super(itemIcon(AllItems.CHROMATIC_COMPOUND.get()), emptyBackground(177, 50));
	}

	@Override
	public Class<? extends ConversionRecipe> getRecipeClass() {
		return ConversionRecipe.class;
	}

	@Override
	public void setIngredients(ConversionRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getRollableResultsAsItemStacks());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ConversionRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
		List<ProcessingOutput> results = recipe.getRollableResults();
		itemStacks.init(0, true, 26, 16);
		itemStacks.set(0, Arrays.asList(recipe.getIngredients().get(0).getMatchingStacks()));
		itemStacks.init(1, false, 131, 16);
		itemStacks.set(1, results.get(0).getStack());
	}

	@Override
	public void draw(ConversionRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		AllGuiTextures.JEI_SLOT.draw(matrixStack, 26, 16);
		AllGuiTextures.JEI_SLOT.draw(matrixStack, 131, 16);
		AllGuiTextures.JEI_LONG_ARROW.draw(matrixStack, 52, 20);
		AllGuiTextures.JEI_QUESTION_MARK.draw(matrixStack, 77, 5);
	}

}
