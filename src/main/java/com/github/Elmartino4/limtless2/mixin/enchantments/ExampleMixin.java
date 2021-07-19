package net.fabricmc.example.mixin;

import net.minecraft.enchantment.EfficiencyEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EfficiencyEnchantment.class)
public class ExampleMixin {
	@ModifyConstant(method = "getMaxLevel", constant = @Constant(intValue = 5), expect = 1)
	private int changeMaxLevel(int original) {
		return 30;
	}
}
