package com.github.Elmartino4.limitless2;

import com.github.Elmartino4.limitless2.config.ModConfig;
import net.minecraft.enchantment.Enchantment;

import java.util.HashMap;

public class SetMaxLevel {
	public static int getMaxLevel(Enchantment ench) {
    System.out.println("Some indicator....." + ench.getTranslationKey());

    if(ModConfig.INSTANCE.enchantCategoryMap.containsKey(ench.getTranslationKey()))
      return ModConfig.INSTANCE.enchantCategoryMap.get(ench.getTranslationKey());

    return ench.getMaxLevel();
  }
}
