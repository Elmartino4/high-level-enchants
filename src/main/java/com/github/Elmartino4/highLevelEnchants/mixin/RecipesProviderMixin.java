package com.github.Elmartino4.highLevelEnchants.mixin;

import com.github.Elmartino4.highLevelEnchants.HighLevelEnchants;
import net.minecraft.data.server.RecipesProvider;
import net.minecraft.data.server.recipe.ComplexRecipeJsonFactory;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.RecipeSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(RecipesProvider.class)
public class RecipesProviderMixin {

    @Inject(method = "generate(Ljava/util/function/Consumer;)V", at = @At("HEAD"))
    private static void generateInject(Consumer<RecipeJsonProvider> exporter, CallbackInfo ci){
        System.out.println("added recipe");
        ComplexRecipeJsonFactory.create(HighLevelEnchants.CHEAP_BOOKS)
                .offerTo(exporter, "cheap_books");
    }
}
