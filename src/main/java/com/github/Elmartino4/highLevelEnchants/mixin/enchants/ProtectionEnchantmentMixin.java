package com.github.Elmartino4.highLevelEnchants.mixin.enchants;

import com.github.Elmartino4.highLevelEnchants.config.ModConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProtectionEnchantment.class)
public class ProtectionEnchantmentMixin {
    @Shadow ProtectionEnchantment.Type protectionType;
    @Inject(method = "canAccept", at = @At("HEAD"), cancellable = true)
    private void canAcceptInject(Enchantment other, CallbackInfoReturnable<Boolean> cir){
        if (other instanceof ProtectionEnchantment && ModConfig.INSTANCE.allowMixedProt) {
            ProtectionEnchantment otherAsProt = (ProtectionEnchantment)other;
            cir.setReturnValue(otherAsProt.protectionType != protectionType);
        }
    }
}
