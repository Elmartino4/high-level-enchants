package com.github.Elmartino4.highLevelEnchants;

import com.github.Elmartino4.highLevelEnchants.config.ModConfig;
import net.minecraft.enchantment.Enchantment;

public class SetMaxLevel {
	public static int getMaxLevel(Enchantment ench) {
    //System.out.println("SetMaxLevel");

    if(ModConfig.INSTANCE.enchantCategoryMap.containsKey(ench.getTranslationKey()))
      return ModConfig.INSTANCE.enchantCategoryMap.get(ench.getTranslationKey());

    return ench.getMaxLevel();
  }
}
