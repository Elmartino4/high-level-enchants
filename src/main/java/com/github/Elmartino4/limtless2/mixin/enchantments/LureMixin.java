package com.github.Elmartino4.limitless2.mixin.enchantments;

import com.github.Elmartino4.limitless2.config.ModConfig;
import net.minecraft.enchantment.LureEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LureEnchantment.class)
public class LureMixin {
	@ModifyConstant(method = "getMaxLevel", constant = @Constant(intValue = 3), expect = 1)
	private int changeMaxLevel(int original) {
		return ModConfig.INSTANCE.Luck;
	}
}
