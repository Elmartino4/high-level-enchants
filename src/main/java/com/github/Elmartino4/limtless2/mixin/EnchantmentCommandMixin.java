package com.github.Elmartino4.limitless2.mixin;

import com.github.Elmartino4.limitless2.config.ModConfig;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.server.command.EnchantCommand;
import net.minecraft.enchantment.Enchantment;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantCommand.class)
public class EnchantmentCommandMixin {
	@Redirect(method = "execute(Lnet/minecraft/server/command/ServerCommandSource;Ljava/util/Collection;Lnet/minecraft/enchantment/Enchantment;I)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
	private int setMaxLevel(Enchantment ench){
		System.out.println("command");
		return ModConfig.INSTANCE.commandEnchantMaxLevel;
	}
}
