package com.github.Elmartino4.limitless2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.screen.AnvilScreenHandler;


@Mixin(AnvilScreenHandler.class)
public class AnvilHandlerMixin {
	@Overwrite
	public static int getNextCost(int cost) {
		return (int)Math.ceil(1.3d*cost+0.2);
	}
}
