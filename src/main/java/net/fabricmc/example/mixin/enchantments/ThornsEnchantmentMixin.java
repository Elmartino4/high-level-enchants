package net.fabricmc.example.mixin;

import net.minecraft.enchantment.ThornsEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ThornsEnchantment.class)
public class ThornsEnchantmentMixin {
	@ModifyConstant(method = "getMaxLevel", constant = @Constant(intValue = 3), expect = 1)
	private int changeMaxLevel(int original) {
		System.out.println("Printed by mixin for Thorns Enchantment");
		return 420;
	}
}
