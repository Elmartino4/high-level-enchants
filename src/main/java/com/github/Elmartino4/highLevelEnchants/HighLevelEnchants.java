package com.github.Elmartino4.highLevelEnchants;

import net.fabricmc.api.ModInitializer;
import com.github.Elmartino4.highLevelEnchants.config.ModConfig;
import net.minecraft.data.server.recipe.ComplexRecipeJsonFactory;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HighLevelEnchants implements ModInitializer {
	static Identifier id = new Identifier("high-level-enchants", "crafting_special_cheap_books");

	@Override
	public void onInitialize() {
		System.out.println("Loaded High Level Enchants!");
		ModConfig.init();
	}
}
