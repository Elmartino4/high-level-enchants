package net.fabricmc.example.mixin.enchantments;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.enchantment;

@Mixin(ImpalingEnchantment.class)
public class ImpalingEnchantmentMixin {
	@Inject(method = "getMaxLevel", at = @At("RETURN"))
	private int getMaxLevel(CallbackInfo ci) {
    return 420;
	}
}
