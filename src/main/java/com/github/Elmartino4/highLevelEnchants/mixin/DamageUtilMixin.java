package com.github.Elmartino4.highLevelEnchants.mixin;

import net.minecraft.entity.DamageUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DamageUtil.class)
public class DamageUtilMixin {
    @ModifyVariable(method = "getInflictedDamage(FF)F", at = @At("STORE"), ordinal = 2)
    private static float getInflictedDamageModify(float original, float damageDealt, float protection){
        float replace = 22 - 22 * (float)Math.pow(2, -protection/10);
        System.out.println(replace);
        return replace;
    }
}
