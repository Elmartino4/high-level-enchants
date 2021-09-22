package com.github.Elmartino4.highLevelEnchants.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public class CrossBowItemMixin {
    @ModifyVariable(method = "loadProjectiles", at = @At(value = "STORE", ordinal = 1))
    private static int setArrows(int original, LivingEntity shooter, ItemStack projectile){
        int i = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, projectile);
        System.out.println("called with level " + (i * 2 + 1) + " projectiles");
        //if((i == 0 && original == 1) || (i > 0 && original == 3))
        //    return 2 * i + 1;

        return i * 2 + 1;
    }

    @Inject(method = "loadProjectile", at = @At("HEAD"))
    private static void loadProjectileInject(LivingEntity shooter, ItemStack crossbow, ItemStack projectile, boolean simulated, boolean creative, CallbackInfoReturnable<Boolean> cir){
        System.out.println("called loadProj");
    }
}
