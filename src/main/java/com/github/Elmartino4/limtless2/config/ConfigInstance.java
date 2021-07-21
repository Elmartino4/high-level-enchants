package com.github.Elmartino4.limitless2.config;

public class ConfigInstance {
  public int TravelSpeed;
  public int MiningSpeed;
  public int Luck;
  public int Attack;
  public int Protection;
  public int ToolOther;
  public int ArmourOther;

  public ConfigInstance() {
    TravelSpeed = 20;
    MiningSpeed = 420;
    Luck = 160;
    Attack = 420;
    Protection = 420;
    ToolOther = 420;
    ArmourOther = 420;
  }

  public class bookshelfCounter {
    public static int enchantPower = 8;
    public static int enchantMultiplier = 35;
    public static int enchantDivConst = 40;
  }
}
