package com.github.Elmartino4.limitless2.mixin;

import com.github.Elmartino4.limitless2.SetMaxLevel;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.enchantment.Enchantment;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Enchantment.class)
public class EnchantmentClassMixin {
	@Redirect(method = "getName(I)Lnet/minecraft/text/Text;", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
	private int setMaxLevel(Enchantment ench){
		return SetMaxLevel.getMaxLevel(ench);
	}
}
