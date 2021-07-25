package com.github.Elmartino4.limitless2.mixin;

import com.github.Elmartino4.limitless2.SetMaxLevel;
import com.github.Elmartino4.limitless2.config.ModConfig;

import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradeOffers.EnchantBookFactory;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

import net.minecraft.text.LiteralText;

@Mixin(targets = "net/minecraft/village/TradeOffers$EnchantBookFactory")
public class TradeOffersMixin {
	@Shadow @Final private int experience;

	private Enchantment ench = null;
	private ItemStack enchItem = null;
	private int level = 0;
	private int Cost = 0;
	private boolean useBlock = false;

	@ModifyVariable(method = "create(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/village/TradeOffer;", ordinal = 0, at = @At("STORE"))
	private Enchantment getEnchantment(Enchantment previous, Entity entity, Random random){
		this.ench = previous;
		return previous;
	}

	@ModifyVariable(method = "create(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/village/TradeOffer;", ordinal = 0, at = @At("STORE"))
	private ItemStack getEnchantmentItem(ItemStack previous, Entity entity, Random random){
		this.enchItem = previous;
		return previous;
	}

	@ModifyVariable(method = "create(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/village/TradeOffer;", ordinal = 0, at = @At("STORE"))
	private int changeEnchantLvl(int previous, Entity entity, Random random){
		int minLevel = ModConfig.INSTANCE.villagerMinMax.get(this.experience)[0];
		int maxLevel = ModConfig.INSTANCE.villagerMinMax.get(this.experience)[1];

		int level = MathHelper.clamp(
			(int)Math.floor(
				Math.log(
					1d/(1-MathHelper.nextDouble(random, 0, 1))
				) * Math.pow(this.experience + ModConfig.INSTANCE.tradePowerConst, 2)/ModConfig.INSTANCE.tradePowerDiv + 1
			),
			Math.max(this.ench.getMinLevel(), minLevel),
			Math.min(SetMaxLevel.getMaxLevel(this.ench), maxLevel)
		);
		this.level = level;
		return level;
	}

	@ModifyVariable(method = "create(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/village/TradeOffer;", ordinal = 1, at = @At("RETURN"))
	private int setCost(int previous, Entity entity, Random random){
		int cost = 2 + random.nextInt(5 + this.level * 10) + 3 * this.level;

    if (this.ench.isTreasure()) {
      cost *= 2;
    }

		System.out.println("Trade = " + this.ench.getTranslationKey());

		if (cost > 54) {
      cost /= 9;
			this.useBlock = true;
    }else{
			this.useBlock = false;
		}

    if (cost > 64) {
      cost = 64;
    }
		this.Cost = cost;
		return cost;
	}

	@Inject(at = @At("RETURN"), method = "create(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/village/TradeOffer;", cancellable = true)
	private void inject(CallbackInfoReturnable<TradeOffer> cir){
		if(this.useBlock){
			cir.setReturnValue(new TradeOffer(new ItemStack((ItemConvertible)Blocks.EMERALD_BLOCK, this.Cost), new ItemStack((ItemConvertible)Items.BOOK), this.enchItem, 12, this.experience, 0.2F));
		}
	}
}
