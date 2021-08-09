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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleEffect;

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
			if (isLibrarian()){
				BribeVillager bribe = new BribeVillager(this.offers, mainHandItem.isOf(Items.GOLD_BLOCK));

				if(bribe.getDecrementItem() && !(player.getAbilities()).creativeMode){
					mainHandItem.decrement(1);
				}

				if(bribe.isSuccessful()){
					this.offers = bribe.getNewList();
					produceParticles((ParticleEffect)ParticleTypes.HAPPY_VILLAGER);
					playSound(SoundEvents.ENTITY_VILLAGER_YES, getSoundVolume(), getSoundPitch());
					//System.out.println("did return");
					cir.setReturnValue(ActionResult.SUCCESS);
				}else{
					playSound(SoundEvents.ENTITY_VILLAGER_NO, getSoundVolume(), getSoundPitch());
					//System.out.println("did return");
					cir.setReturnValue(ActionResult.CONSUME);
				}
			}
		}else{
			this.hasSpecialItem = false;
		}
	}
	
	private boolean isLibrarian(){
		String id = this.getVillagerData().getProfession().getId();
		//System.out.println("id = " + id);
		return id == "librarian";
	}
}
