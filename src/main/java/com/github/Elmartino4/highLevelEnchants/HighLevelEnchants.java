package com.github.Elmartino4.highLevelEnchants;

import net.fabricmc.api.ModInitializer;
import com.github.Elmartino4.highLevelEnchants.config.ModConfig;
import net.minecraft.data.server.recipe.ComplexRecipeJsonFactory;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HighLevelEnchants implements ModInitializer {
	public static Logger LOGGER = LogManager.getLogger("high-level-enchants");
	static Identifier id = new Identifier("high-level-enchants", "crafting_special_cheap_books");

	public static final SpecialRecipeSerializer<CheapBookRecipe> CHEAP_BOOKS =
			Registry.register(Registry.RECIPE_SERIALIZER, id, new SpecialRecipeSerializer<>(CheapBookRecipe::new));

	@Override
	public void onInitialize() {
		LOGGER.info("Loaded High Level Enchants!");
		ModConfig.init();
	}
}
