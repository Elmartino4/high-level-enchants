package net.fabricmc.example.mixin.enchantments;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.enchantment;

@Mixin(EfficiencyEnchantment.class)
public class EfficiencyEnchantmentMixin {
	@Inject(method = "getMaxLevel", at = @At("RETURN"))
	private int getMaxLevel(CallbackInfo ci) {
    return 420;
	}
}
