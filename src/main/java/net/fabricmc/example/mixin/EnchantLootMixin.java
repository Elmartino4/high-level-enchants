package net.fabricmc.example.mixin;

import net.minecraft.village;
import java.lang.Math.*;
import java.util.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.math.MathHelper;

@Mixin(EnchantRandomlyLootFunction.class)
public class EnchantLootMixin {
	@Inject(method = "addEnchantmentToStack", at = @At(value = "FIELD", target = "net/minecraft/loot/function/EnchantRandomlyLootFunction.i:I", ordinal = 1))
	private void addEnchantmentToStack(CallbackInfo ci) {
    i = MathHelper.nextInt(
			random,
			enchantment.getMinLevel(),
			Math.min(
				enchantment.getMaxLevel(),
				Math.floor(
					Math.log(
						1d/(1-MathHelper.nextDouble(random, 0, 1))
					) * 16 + 1
				)
			)
		);
	}
}
