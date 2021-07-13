package net.fabricmc.example.mixin;

import net.minecraft.enchantment.FireAspectEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FireAspectEnchantment.class)
public class FireAspectEnchantmentMixin {
	@ModifyConstant(method = "getMaxLevel", constant = @Constant(intValue = 2), expect = 1)
	private int changeMaxLevel(int original) {
		System.out.println("Printed by mixin for FireAspect Enchantment");
		return 420;
	}
}
