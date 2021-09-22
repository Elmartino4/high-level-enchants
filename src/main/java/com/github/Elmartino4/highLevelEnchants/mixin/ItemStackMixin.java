package com.github.Elmartino4.highLevelEnchants.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    private int level;
    @Inject(method = "addEnchantment", at = @At("HEAD"))
    private void getUsingInject(Enchantment enchantment, int level, CallbackInfo ci){
        this.level = level;
    }

    @ModifyArg(method = "addEnchantment", index = 1, at =  @At(value = "INVOKE",
            target = "Lnet/minecraft/enchantment/EnchantmentHelper;createNbt(Lnet/minecraft/util/Identifier;I)Lnet/minecraft/nbt/NbtCompound;"))
    private int modifyArg(Identifier id, int original){
        return this.level;
    }
}
