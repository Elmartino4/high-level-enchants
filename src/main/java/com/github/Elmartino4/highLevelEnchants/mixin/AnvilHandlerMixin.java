package com.github.Elmartino4.highLevelEnchants.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.enchantment.Enchantment;

import com.github.Elmartino4.highLevelEnchants.config.ModConfig;
import com.github.Elmartino4.highLevelEnchants.SetMaxLevel;

import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilHandlerMixin extends ForgingScreenHandler {
	public AnvilHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
		super(type, syncId, playerInventory, context);
	}

	@Inject(method = "getNextCost(I)I", at = @At("TAIL"), cancellable = true)
	private static void getNextCost(int cost, CallbackInfoReturnable<Integer> cir) {
		//System.out.println("anvil");
		cir.setReturnValue((int)Math.ceil(ModConfig.INSTANCE.anvilMultiplier*cost+0.2));
	}

	@ModifyConstant(method = "updateResult()V", constant = @Constant(intValue = 40), expect = 3)
	private int changeMaxLevel(int original) {
		//System.out.println("anvil");
		return ModConfig.INSTANCE.maxAnvilLevel;
	}

	@ModifyConstant(method = "updateResult()V", constant = @Constant(intValue = 39), expect = 1)
	private int changeMaxLevelAgain(int original) {
		//System.out.println("anvil");
		return ModConfig.INSTANCE.maxAnvilLevel - 1;
	}

	@Redirect(method = "updateResult()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
	private int setMaxLevel(Enchantment ench){
		//System.out.println("anvil");
		return SetMaxLevel.getMaxLevel(ench);
	}

	@ModifyArg(method = "updateResult()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;set(I)V"))
	private int modifyCost(int previous){
 		ItemStack stack0 = input.getStack(0);
		ItemStack stack1 = input.getStack(1);

		double diamonds = 0;
		diamonds += stack0.getOrCreateNbt().getDouble("high-level-enchants-cost");
		diamonds += stack1.getOrCreateNbt().getDouble("high-level-enchants-cost");

		diamonds /= ModConfig.INSTANCE.costDivider;

		diamonds++;
		return (int)Math.ceil(previous / diamonds);
	}
}
