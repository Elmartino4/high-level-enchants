package com.github.Elmartino4.highLevelEnchants.mixin.enchants;

import com.github.Elmartino4.highLevelEnchants.config.ModConfig;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {
    @Shadow @Final int typeIndex;

    @Inject(method = "canAccept", at = @At("HEAD"), cancellable = true)
    private void canAcceptInject(Enchantment other, CallbackInfoReturnable<Boolean> cir){
        if (other instanceof DamageEnchantment && ModConfig.INSTANCE.allowMixedDamage) {
            DamageEnchantment otherAsDamage = (DamageEnchantment) other;
            cir.setReturnValue(otherAsDamage.typeIndex != typeIndex);
        }
    }
}
