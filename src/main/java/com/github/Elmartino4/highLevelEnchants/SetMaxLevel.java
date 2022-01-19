package com.github.Elmartino4.highLevelEnchants;

import com.github.Elmartino4.highLevelEnchants.config.ModConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class SetMaxLevel {
	public static int getMaxLevel(Enchantment ench) {
        //System.out.println("SetMaxLevel");

        String IDString = Registry.ENCHANTMENT.getId(ench).toString();

        if(ModConfig.INSTANCE.enchantCategoryMap.containsKey(IDString))
            return ModConfig.INSTANCE.enchantCategoryMap.get(IDString);

        return ench.getMaxLevel();
    }
}
