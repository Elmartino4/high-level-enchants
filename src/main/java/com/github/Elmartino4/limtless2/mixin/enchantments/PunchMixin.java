package com.github.Elmartino4.limitless2.mixin.enchantments;

import com.github.Elmartino4.limitless2.config.ModConfig;
import net.minecraft.enchantment.PunchEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PunchEnchantment.class)
public class PunchMixin {
	@ModifyConstant(method = "getMaxLevel", constant = @Constant(intValue = 2), expect = 1)
	private int changeMaxLevel(int original) {
		return ModConfig.INSTANCE.Attack;
	}
}
