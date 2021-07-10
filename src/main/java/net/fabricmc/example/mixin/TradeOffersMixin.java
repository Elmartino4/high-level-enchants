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
public class TradeOfferMixin {
	@Inject(method = "TradeOffer", at = @At(value = "FIELD", target = "net/minecraft/village/TradeOffer$EnchantBookFactory.i:I", ordinal = 1))
	private void create(CallbackInfo ci) {
    i = MathHelper.nextInt(
			random,
			lv.getMinLevel(),
			Math.min(
				lv.getMaxLevel(),
				Math.floor(
					Math.log(
						1d/(1-MathHelper.nextDouble(random, 0, 1))
					) * (experience + 4) + 1
				)
			)
		);
	}
}
