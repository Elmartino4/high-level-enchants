package com.github.Elmartino4.limitless2.mixin;

import com.github.Elmartino4.limitless2.SetMaxLevel;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.item.EnchantedBookItem;
import net.minecraft.enchantment.Enchantment;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantedBookItem.class)
public class EnchantedBookItemMixin {
	@Redirect(method = "appendStacks", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
	private int setMaxLevel(Enchantment ench){
		System.out.println("book");
		int lvl = SetMaxLevel.getMaxLevel(ench);
		System.out.println("book 2 ");
		return lvl;
	}
}
