package net.fabricmc.example.mixin.enchantments;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.enchantment;

@Mixin(FrostWalkerEnchantment.class)
public class FrostWalkerEnchantmentMixin {
	@Inject(method = "getMaxLevel", at = @At("RETURN"))
	private void getMaxLevel(CallbackInfo ci) {
    return 420;
	}
}
