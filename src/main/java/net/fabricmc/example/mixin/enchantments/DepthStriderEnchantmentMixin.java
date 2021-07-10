package net.fabricmc.example.mixin.enchantments;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.enchantment;

@Mixin(DepthStriderEnchantment.class)
public class DepthStriderEnchantmentMixin {
	@Inject(method = "getMaxLevel", at = @At("RETURN"))
	private void getMaxLevel(CallbackInfo ci) {
    return 15;
	}
}
