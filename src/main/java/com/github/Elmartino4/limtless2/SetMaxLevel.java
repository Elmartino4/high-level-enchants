package com.github.Elmartino4.limitless2;

import com.github.Elmartino4.limitless2.config.ModConfig;

public class SetMaxLevel {
	public int getMaxLevel(Enchantment ench) {
    System.out.println("Some indicator....." + ench.getTranslationKey());
    String[] categoryList = {"TravelSpeed", "MiningSpeed", "Luck", "Attack", "Protection", "ToolOther", "ArmourOther"};
    for(category : categoryList){
      if(ModConfig.INSTANCE.enchantCategories.["category"].contains(ench.getTranslationKey()))
        return ModConfig.INSTANCE["category"];
    }
    return ench.getMaxLevel();
  }
}
