package net.fabricmc.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.screen;


@Mixin(AnvilScreenHandler.class)
public class AnvilHandlerMixin {
	@Inject(method = "getNextCost", at = @At("RETURN"))
	private int getNextCost(CallbackInfo ci) {
    return (int)Math.ceil((double)cost * 1.3 + 0.2);
	}
}
