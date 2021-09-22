package com.github.Elmartino4.highLevelEnchants.mixin;

import com.github.Elmartino4.highLevelEnchants.SetMaxLevel;
import com.github.Elmartino4.highLevelEnchants.config.ModConfig;

import net.minecraft.village.TradeOffer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.MathHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

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
		//System.out.println("Villager");
		this.ench = previous;
		return previous;
	}

	@ModifyVariable(method = "create(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/village/TradeOffer;", ordinal = 0, at = @At("STORE"))
	private ItemStack getEnchantmentItem(ItemStack previous, Entity entity, Random random){
		//System.out.println("Villager");
		this.enchItem = previous;
		return previous;
	}

	@ModifyVariable(method = "create(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/village/TradeOffer;", ordinal = 0, at = @At("STORE"))
	private int changeEnchantLvl(int previous, Entity entity, Random random){
		//System.out.println("Villager start");
		int minLevel = ModConfig.INSTANCE.villagerMinMax.get(this.experience)[0];
		int maxLevel = ModConfig.INSTANCE.villagerMinMax.get(this.experience)[1];

		int level = clamp(
			(int)Math.floor(
				Math.log(
					1d/(1-MathHelper.nextDouble(random, 0, 1))
				) * Math.pow(this.experience + ModConfig.INSTANCE.tradePowerConst, 2)/ModConfig.INSTANCE.tradePowerDiv + minLevel
			),
			this.ench.getMinLevel(),
			Math.min(SetMaxLevel.getMaxLevel(this.ench), maxLevel)
		);
		this.level = level;
		//System.out.println("Villager finish");
		return level;
	}

	private static int clamp(int val, int min, int max){
		return Math.min(Math.max(val, min), max);
	}

	@ModifyVariable(method = "create(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/village/TradeOffer;", ordinal = 1, at = @At("RETURN"))
	private int setCost(int previous, Entity entity, Random random){
		//System.out.println("Villager start");
		int cost = 2 + random.nextInt(5 + this.level * 10) + 3 * this.level;

    if (this.ench.isTreasure()) {
      cost *= 2;
    }

		//System.out.println("Trade = " + this.ench.getTranslationKey());

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
		//System.out.println("Villager finish");
		return cost;
	}

	@Inject(at = @At("RETURN"), method = "create(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/village/TradeOffer;", cancellable = true)
	private void inject(CallbackInfoReturnable<TradeOffer> cir){
		//System.out.println("Villager");
		if(this.useBlock){
			cir.setReturnValue(new TradeOffer(new ItemStack((ItemConvertible)Blocks.EMERALD_BLOCK, this.Cost), new ItemStack((ItemConvertible)Items.BOOK), this.enchItem, 12, this.experience, 0.2F));
		}
	}
}
