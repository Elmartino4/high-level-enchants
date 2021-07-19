package com.github.Elmartino4.limitless2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.*;
import java.util.Random;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
	@Overwrite
	public static int calculateRequiredExperienceLevel(Random random, int slotIndex, int bookshelfCount, ItemStack stack) {
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
