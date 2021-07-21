package com.github.Elmartino4.limitless2;

import net.fabricmc.api.ModInitializer;
import com.github.Elmartino4.limitless2.config.ModConfig;

public class HighLevelEnchants implements ModInitializer {
	@Override
	public void onInitialize() {
		ModConfig.init();
	}
}
