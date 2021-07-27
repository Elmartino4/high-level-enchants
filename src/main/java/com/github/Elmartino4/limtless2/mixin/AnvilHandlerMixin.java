package com.github.Elmartino4.limitless2.mixin;

import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.enchantment.Enchantment;

import com.github.Elmartino4.limitless2.config.ModConfig;
import com.github.Elmartino4.limitless2.SetMaxLevel;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreenHandler.class)
public class AnvilHandlerMixin {
	@Overwrite
	public static int getNextCost(int cost) {
		System.out.println("anvil");
		return (int)Math.ceil(ModConfig.INSTANCE.anvilMultiplier*cost+0.2);
	}

	@ModifyConstant(method = "updateResult()V", constant = @Constant(intValue = 40), expect = 3)
	private int changeMaxLevel(int original) {
		System.out.println("anvil");
		return ModConfig.INSTANCE.maxAnvilLevel;
	}

	@ModifyConstant(method = "updateResult()V", constant = @Constant(intValue = 39), expect = 1)
	private int changeMaxLevelAgain(int original) {
		System.out.println("anvil");
		return ModConfig.INSTANCE.maxAnvilLevel - 1;
	}

	@Redirect(method = "updateResult()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
	private int setMaxLevel(Enchantment ench){
		System.out.println("anvil");
		return SetMaxLevel.getMaxLevel(ench);
	}
}
