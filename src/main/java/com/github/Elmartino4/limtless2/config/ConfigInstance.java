package com.github.Elmartino4.limitless2.config;

public class ConfigInstance {
  public int TravelSpeed;
  public int MiningSpeed;
  public int Luck;
  public int Attack;
  public int Protection;
  public int ToolOther;
  public int ArmourOther;
  public int MaxAnvilLevel;

  public BookshelfCounter bookshelfCounter;

  public ConfigInstance() {
    TravelSpeed = 20;
    MiningSpeed = 420;
    Luck = 160;
    Attack = 420;
    Protection = 420;
    ToolOther = 420;
    ArmourOther = 420;
    MaxAnvilLevel = 80;

    enchantCategories = new CategoriseEnchants();
    bookshelfCounter = new BookshelfCounter();
  }

  public class BookshelfCounter {
    public static int enchantPower = 8;
    public static int enchantMultiplier = 35;
    public static int enchantDivConst = 40;
  }

  public class CategoriseEnchants {
    public static String[] TravelSpeed = {};
    public static String[] MiningSpeed = {};
    public static String[] Luck = {};
    public static String[] Attack = {};
    public static String[] Protection = {};
    public static String[] ToolOther = {};
    public static String[] ArmourOther = {};
    public static String[] MaxAnvilLevel = {};
  }
}
