package com.github.Elmartino4.highLevelEnchants.config;

import java.util.HashMap;

public class ConfigInstance {
  public int maxAnvilLevel;
  public double anvilMultiplier;

  public HashMap<String, Integer> enchantCategoryMap = new HashMap<>();

  public HashMap<Integer, int[]> villagerMinMax = new HashMap<>();

  public int bookshelfPower;
  public int bookshelfMultiplier;
  public int bookshelfDivConst;

  public double ingotMultiplier;
  public double blockMultiplier;

  public int commandEnchantMaxLevel;

  public int tradePowerConst;
  public int tradePowerDiv;

  public double costDivider;

  public boolean bypassLanguageFiles;
  public boolean allowMixedProt;

  public ConfigInstance() {
    ingotMultiplier = 1;
    blockMultiplier = 14;

    enchantCategoryMap.put("enchantment.minecraft.aqua_affinity", 1);
    enchantCategoryMap.put("enchantment.minecraft.bane_of_arthropods", 420);
    enchantCategoryMap.put("enchantment.minecraft.blast_protection", 420);
    enchantCategoryMap.put("enchantment.minecraft.channeling", 1);
    enchantCategoryMap.put("enchantment.minecraft.binding_curse", 1);
    enchantCategoryMap.put("enchantment.minecraft.vanishing_curse", 1);
    enchantCategoryMap.put("enchantment.minecraft.depth_strider", 20);
    enchantCategoryMap.put("enchantment.minecraft.efficiency", 420);
    enchantCategoryMap.put("enchantment.minecraft.feather_falling", 420);
    enchantCategoryMap.put("enchantment.minecraft.fire_aspect", 420);
    enchantCategoryMap.put("enchantment.minecraft.fire_protection", 420);
    enchantCategoryMap.put("enchantment.minecraft.flame", 420);
    enchantCategoryMap.put("enchantment.minecraft.fortune", 420);
    enchantCategoryMap.put("enchantment.minecraft.frost_walker", 420);
    enchantCategoryMap.put("enchantment.minecraft.impaling", 420);
    enchantCategoryMap.put("enchantment.minecraft.infinity", 1);
    enchantCategoryMap.put("enchantment.minecraft.knockback", 420);
    enchantCategoryMap.put("enchantment.minecraft.looting", 420);
    enchantCategoryMap.put("enchantment.minecraft.loyalty", 420);
    enchantCategoryMap.put("enchantment.minecraft.luck_of_the_sea", 420);
    enchantCategoryMap.put("enchantment.minecraft.lure", 5);
    enchantCategoryMap.put("enchantment.minecraft.mending", 1);
    enchantCategoryMap.put("enchantment.minecraft.multishot", 20);
    enchantCategoryMap.put("enchantment.minecraft.piercing", 420);
    enchantCategoryMap.put("enchantment.minecraft.power", 420);
    enchantCategoryMap.put("enchantment.minecraft.projectile_protection", 420);
    enchantCategoryMap.put("enchantment.minecraft.protection", 420);
    enchantCategoryMap.put("enchantment.minecraft.punch", 420);
    enchantCategoryMap.put("enchantment.minecraft.respiration", 420);
    enchantCategoryMap.put("enchantment.minecraft.quick_charge", 420);
    enchantCategoryMap.put("enchantment.minecraft.riptide", 420);
    enchantCategoryMap.put("enchantment.minecraft.sharpness", 420);
    enchantCategoryMap.put("enchantment.minecraft.silk_touch", 1);
    enchantCategoryMap.put("enchantment.minecraft.smite", 420);
    enchantCategoryMap.put("enchantment.minecraft.soul_speed", 20);
    enchantCategoryMap.put("enchantment.minecraft.sweeping", 420);
    enchantCategoryMap.put("enchantment.minecraft.thorns", 420);
    enchantCategoryMap.put("enchantment.minecraft.unbreaking", 420);

    villagerMinMax.put(1, new int[]{1, 5});
    villagerMinMax.put(5, new int[]{3, 8});
    villagerMinMax.put(10, new int[]{6, 20});
    villagerMinMax.put(15, new int[]{10, 410});

    maxAnvilLevel = 60;
    anvilMultiplier = 1.3d;

    bookshelfPower = 8;
    bookshelfMultiplier = 35;
    bookshelfDivConst = 40;

    commandEnchantMaxLevel = 32767;

    tradePowerConst = 12;
    tradePowerDiv = 50;

    bypassLanguageFiles = false;
    allowMixedProt = true;

    costDivider = 7.0;
  }
}
