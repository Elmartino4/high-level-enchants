package net.fabricmc.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.enchantment;
import net.minecraft.item.*;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
	@Inject(method = "calculateRequiredExperienceLevel", at = @At("HEAD"))
	private int calculateRequiredExperienceLevel(CallbackInfo ci) {
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
     return Math.max(l, bookshelfCount * 2);
	}
}
