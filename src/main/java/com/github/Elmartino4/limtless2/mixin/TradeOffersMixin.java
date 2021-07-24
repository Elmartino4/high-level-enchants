package com.github.Elmartino4.limitless2.mixin;

import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradeOffers.EnchantBookFactory;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
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
		int level = MathHelper.nextInt(
			random,
			this.ench.getMinLevel(),
			Math.min(
				this.ench.getMaxLevel(),
				(int)Math.floor(
					Math.log(
						1d/(1-MathHelper.nextDouble(random, 0, 1))
					) * Math.pow(this.experience + 20, 2)/140 + 1
				)
			)
		);
		//System.out.println("villager mixin; experience = " + this.experience);
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

		if (cost > 36) {
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
	/*@Overwrite
	public TradeOffer create(Entity entity, Random random) {
    List<Enchantment> list = (List<Enchantment>)Registry.ENCHANTMENT.stream().filter(Enchantment::isAvailableForEnchantedBookOffer).collect(Collectors.toList());
		Enchantment lv = list.get(random.nextInt(list.size()));
		System.out.println("[fabric example mod] TradeOffersMixin");

    ItemStack lv2 = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(lv, level));



    int cost = 2 + random.nextInt(5 + level * 10) + 3 * level;

    if (lv.isTreasure()) {
      cost *= 2;
    }

    if (cost > 64) {
      cost = 64;
    }



    return new TradeOffer(new ItemStack((ItemConvertible)Items.EMERALD, cost), new ItemStack((ItemConvertible)Items.BOOK), lv2, 12, this.experience, 0.2F);
  }*/
}
