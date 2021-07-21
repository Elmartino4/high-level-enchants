package com.github.Elmartino4.limitless2.mixin.enchantments;

import com.github.Elmartino4.limitless2.config.ModConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ProtectionEnchantment.class)
public class ProtectionMixin {
	@ModifyConstant(method = "getMaxLevel", constant = @Constant(intValue = 4), expect = 1)
	private int changeMaxLevel(int original) {
		return ModConfig.INSTANCE.Protection;
	}

	@Overwrite
	public boolean canAccept(Enchantment other) {
    return true;
  }
}
