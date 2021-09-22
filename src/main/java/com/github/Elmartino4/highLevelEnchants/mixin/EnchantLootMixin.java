package com.github.Elmartino4.highLevelEnchants.mixin;

import com.github.Elmartino4.highLevelEnchants.SetMaxLevel;

import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.math.MathHelper;

import net.minecraft.item.ItemStack;

@Mixin(EnchantRandomlyLootFunction.class)
public class EnchantLootMixin {
	@ModifyVariable(method = "addEnchantmentToStack", ordinal = 0, at = @At("STORE"))
	private static int changeEnchantLvl(int prev, ItemStack stack, Enchantment enchantment, Random random) {
		//System.out.println("loot");
    int i = Math.min(
			enchantment.getMaxLevel(),
			(int)Math.floor(
				Math.log(
					1d/(1-MathHelper.nextDouble(random, 0, 1))
				) * 7 + 1
			)
		);

		return i;
  }

	@Redirect(method = "addEnchantmentToStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
	private static int setMaxLevel(Enchantment ench){
		//System.out.println("loot");
		return SetMaxLevel.getMaxLevel(ench);
	}
}
