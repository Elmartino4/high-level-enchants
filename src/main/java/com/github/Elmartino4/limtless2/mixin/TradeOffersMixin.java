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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

@Mixin(targets = "net/minecraft/village/TradeOffers$EnchantBookFactory")
public class TradeOffersMixin {
	@Shadow @Final private int experience;

	private Enchantment ench = null;
	private int level = 0;

	@ModifyVariable(method = "create(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/village/TradeOffer;", ordinal = 0, at = @At("STORE"))
	private Enchantment getEnchantment(Enchantment previous, Entity entity, Random random){
		this.ench = previous;
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
					) * Math.pow(experience + 20, 2)/140 + 1
				)
			)
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

    if (cost > 64) {
      cost = 64;
    }
		return cost;
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
