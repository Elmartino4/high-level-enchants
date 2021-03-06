package com.github.Elmartino4.highLevelEnchants.mixin;

import com.github.Elmartino4.highLevelEnchants.config.ModConfig;

import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.server.command.EnchantCommand;
import net.minecraft.enchantment.Enchantment;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(EnchantCommand.class)
public class EnchantmentCommandMixin {
	@Redirect(method = "execute(Lnet/minecraft/server/command/ServerCommandSource;Ljava/util/Collection;Lnet/minecraft/enchantment/Enchantment;I)I",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"), expect = 2)
	private static int setMaxLevel(Enchantment ench){
		System.out.println("command");
		return ModConfig.INSTANCE.commandEnchantMaxLevel;
	}
	/*@Inject(method = "execute", at = @At("HEAD"))
	private static void testInject(ServerCommandSource source, Collection<? extends Entity> targets, Enchantment enchantment, int level, CallbackInfoReturnable<Integer> cir){
		System.out.println("command test");
	}*/
}
