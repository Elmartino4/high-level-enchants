package com.github.Elmartino4.highLevelEnchants.mixin;

import com.github.Elmartino4.highLevelEnchants.SetMaxLevel;
import com.github.Elmartino4.highLevelEnchants.config.ModConfig;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.*;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
	@Overwrite
	public static int calculateRequiredExperienceLevel(Random random, int slotIndex, int bookshelfCount, ItemStack stack) {
		//System.out.println("helper strt");
     if (stack.getItem().getEnchantability() <= 0){
       return 0;
     }

     int l = random.nextInt(8) + 1 + (bookshelfCount >> 1) + random.nextInt(bookshelfCount + 1);

     if (slotIndex == 0) {
       return Math.max(l / 3, 1);
     }

     if (slotIndex == 1) {
       return l * 2 / 3 + 1;
     }
		 //System.out.println("helper end");
     return Math.max(l, bookshelfCount * 2);
	}

	@Redirect(method = "getPossibleEntries(ILnet/minecraft/item/ItemStack;Z)Ljava/util/List;", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
	private static int setMaxLevel(Enchantment ench){
		//System.out.println("helper");
		return SetMaxLevel.getMaxLevel(ench);
	}

	@ModifyArg(method = "getLevelFromNbt(Lnet/minecraft/nbt/NbtCompound;)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(III)I"), index = 2)
  private static int injectMaxEnchantmentLevel(int x) {
		//System.out.println("helper ");
  	return ModConfig.INSTANCE.commandEnchantMaxLevel;
  }
}
