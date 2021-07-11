package net.fabricmc.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.screen;
import java.lang.Math.*;

@Mixin(AnvilScreenHandler.class)
public class AnvilHandlerMixin {
	@Inject(method = "getNextCost", at = @At("RETURN"))
	private void getNextCost(CallbackInfo ci) {
    return Math.roof((double)cost * 1.3 + 0.2);
	}
}
