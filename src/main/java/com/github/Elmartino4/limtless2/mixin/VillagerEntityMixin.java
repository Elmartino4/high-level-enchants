package com.github.Elmartino4.limitless2.mixin;

import com.github.Elmartino4.limitless2.BribeVillager;
import com.github.Elmartino4.limitless2.config.ModConfig;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.village.VillagerData;
import net.minecraft.util.ActionResult;
import net.minecraft.village.TradeOfferList;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.sound.SoundEvents;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin extends MerchantEntity {
	public VillagerEntityMixin(EntityType<? extends VillagerEntityMixin> arg, World arg2) {
		super((EntityType)arg, arg2);
	}

	boolean hasSpecialItem = false;
	boolean listEmpty = false;

	@Shadow public abstract VillagerData getVillagerData();


	@Inject(method = "interactMob(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;", at = @At("HEAD"), cancellable = true)
	private void injectInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
		ItemStack mainHandItem = player.getStackInHand(hand);
		if (mainHandItem.isOf(Items.GOLD_BLOCK) || mainHandItem.isOf(Items.GOLD_INGOT)) {
			this.hasSpecialItem = true;
			TradeOfferList newList;
			if (isLibrarian())
				if((newList = BribeVillager.doBribe(this.offers, mainHandItem.isOf(Items.GOLD_BLOCK))) != this.offers){
					System.out.println("found new offers");
					this.offers = newList;
					if (!(player.getAbilities()).creativeMode) {
						mainHandItem.decrement(1);
					}
					playSound(SoundEvents.ENTITY_VILLAGER_YES, getSoundVolume(), getSoundPitch());
					cir.setReturnValue(ActionResult.SUCCESS);
				}else{
					System.out.println("offers didnt change");
					playSound(SoundEvents.ENTITY_VILLAGER_NO, getSoundVolume(), getSoundPitch());
					cir.setReturnValue(ActionResult.FAIL);
					if (!(player.getAbilities()).creativeMode) {
						mainHandItem.decrement(1);
					}
				}
		}else{
			this.hasSpecialItem = false;
		}
	}

	@Redirect(method = "interactMob(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
	private boolean stopInteract(ItemStack itmStk, Item itm){
		if(this.hasSpecialItem && isLibrarian() && itm == Items.VILLAGER_SPAWN_EGG){
			System.out.println("stopd interact");
			return true;
		}

		return itmStk.isOf(itm);
	}

	@Redirect(method = "interactMob(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/village/TradeOfferList;isEmpty()Z"))
	private boolean stopInteract(TradeOfferList list){
		System.out.println("called isBaby");
		return list.isEmpty();
	}

	private boolean isLibrarian(){
		String id = this.getVillagerData().getProfession().getId();
		//System.out.println("id = " + id);
		return id == "librarian";
	}
}
