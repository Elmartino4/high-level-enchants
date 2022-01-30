package com.github.Elmartino4.highLevelEnchants.mixin.enchants;

import com.github.Elmartino4.highLevelEnchants.config.ModConfig;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {
    @Shadow @Final public int typeIndex;

    @Inject(method = "canAccept", at = @At("HEAD"), cancellable = true)
    private void canAcceptInject(Enchantment other, CallbackInfoReturnable<Boolean> cir){
        if (other instanceof DamageEnchantment otherAsDamage && ModConfig.INSTANCE.allowMixedDamage) {
            cir.setReturnValue(otherAsDamage.typeIndex != typeIndex);
        }
    }
}
