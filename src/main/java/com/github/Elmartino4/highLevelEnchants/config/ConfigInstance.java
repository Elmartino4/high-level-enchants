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
  public boolean allowMixedDamage;

  public ConfigInstance() {
    ingotMultiplier = 1;
    blockMultiplier = 14;

    enchantCategoryMap.put("minecraft:aqua_affinity", 1);
    enchantCategoryMap.put("minecraft:bane_of_arthropods", 50);
    enchantCategoryMap.put("minecraft:blast_protection", 50);
    enchantCategoryMap.put("minecraft:channeling", 1);
    enchantCategoryMap.put("minecraft:binding_curse", 1);
    enchantCategoryMap.put("minecraft:vanishing_curse", 1);
    enchantCategoryMap.put("minecraft:depth_strider", 10);
    enchantCategoryMap.put("minecraft:efficiency", 50);
    enchantCategoryMap.put("minecraft:feather_falling", 50);
    enchantCategoryMap.put("minecraft:fire_aspect", 50);
    enchantCategoryMap.put("minecraft:fire_protection", 50);
    enchantCategoryMap.put("minecraft:flame", 50);
    enchantCategoryMap.put("minecraft:fortune", 50);
    enchantCategoryMap.put("minecraft:frost_walker", 50);
    enchantCategoryMap.put("minecraft:impaling", 50);
    enchantCategoryMap.put("minecraft:infinity", 1);
    enchantCategoryMap.put("minecraft:knockback", 50);
    enchantCategoryMap.put("minecraft:looting", 50);
    enchantCategoryMap.put("minecraft:loyalty", 50);
    enchantCategoryMap.put("minecraft:luck_of_the_sea", 50);
    enchantCategoryMap.put("minecraft:lure", 5);
    enchantCategoryMap.put("minecraft:mending", 1);
    enchantCategoryMap.put("minecraft:multishot", 1);
    enchantCategoryMap.put("minecraft:piercing", 50);
    enchantCategoryMap.put("minecraft:power", 50);
    enchantCategoryMap.put("minecraft:projectile_protection", 50);
    enchantCategoryMap.put("minecraft:protection", 50);
    enchantCategoryMap.put("minecraft:punch", 50);
    enchantCategoryMap.put("minecraft:respiration", 50);
    enchantCategoryMap.put("minecraft:quick_charge", 50);
    enchantCategoryMap.put("minecraft:riptide", 50);
    enchantCategoryMap.put("minecraft:sharpness", 50);
    enchantCategoryMap.put("minecraft:silk_touch", 1);
    enchantCategoryMap.put("minecraft:smite", 50);
    enchantCategoryMap.put("minecraft:soul_speed", 10);
    enchantCategoryMap.put("minecraft:sweeping", 50);
    enchantCategoryMap.put("minecraft:thorns", 50);
    enchantCategoryMap.put("minecraft:unbreaking", 50);

    villagerMinMax.put(1, new int[]{1, 3});
    villagerMinMax.put(5, new int[]{2, 5});
    villagerMinMax.put(10, new int[]{3, 9});
    villagerMinMax.put(15, new int[]{5, 25});

    maxAnvilLevel = 40;
    anvilMultiplier = 1.3d;

    bookshelfPower = 8;
    bookshelfMultiplier = 35;
    bookshelfDivConst = 40;

    commandEnchantMaxLevel = 32767;

    tradePowerConst = 20;
    tradePowerDiv = 270;

    bypassLanguageFiles = true;
    allowMixedProt = true;
    allowMixedDamage = false;

    costDivider = 7.0;
  }
}
