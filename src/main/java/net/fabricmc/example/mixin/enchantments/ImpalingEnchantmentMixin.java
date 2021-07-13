package net.fabricmc.example.mixin;

import net.minecraft.enchantment.ImpalingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ImpalingEnchantment.class)
public class ImpalingEnchantmentMixin {
	@ModifyConstant(method = "getMaxLevel", constant = @Constant(intValue = 5), expect = 1)
	private int changeMaxLevel(int original) {
		System.out.println("Printed by mixin for Impaling Enchantment");
		return 420;
	}
}
