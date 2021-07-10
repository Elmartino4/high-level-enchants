package net.fabricmc.example.mixin;

import net.minecraft.village;
import java.lang.Math.*;
import java.util.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.math.MathHelper;

@Mixin(TradeOffers$EnchantBookFactory.class)
public class EnchantmentMixin {
	@Inject(method = "getMaxLevel", at = @At("RETURN"))
	private void getMaxLevel(CallbackInfo ci) {
    return 420;
	}
}
