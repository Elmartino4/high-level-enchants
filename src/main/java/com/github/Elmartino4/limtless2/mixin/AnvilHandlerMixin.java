package com.github.Elmartino4.limitless2.mixin;

import net.minecraft.screen.AnvilScreenHandler;
import com.github.Elmartino4.limitless2.config.ModConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreenHandler.class)
public class AnvilHandlerMixin {
	@Overwrite
	public static int getNextCost(int cost) {
		return (int)Math.ceil(1.3d*cost+0.2);
	}

	@ModifyConstant(method = "updateResult()V", constant = @Constant(intValue = 40), expect = 3)
	private int changeMaxLevel(int original) {
		return ModConfig.INSTANCE.MaxAnvilLevel;
	}

	@ModifyConstant(method = "updateResult()V", constant = @Constant(intValue = 39), expect = 1)
	private int changeMaxLevelAgain(int original) {
		return ModConfig.INSTANCE.MaxAnvilLevel - 1;
	}
}
