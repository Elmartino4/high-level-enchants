package com.github.Elmartino4.highLevelEnchants.mixin.darkenchants;

import com.github.Elmartino4.highLevelEnchants.SetMaxLevel;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.cottonmc.cotton.gui.widget.*;

@Pseudo @Mixin(targets = {"io/github/frqnny/darkenchanting/client/gui/DarkEnchanterGUI"})
public class DarkEnchanterGUIMixin {
    @Shadow @Final
    public Object2IntLinkedOpenHashMap<Enchantment> enchantmentsToApply;

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"),
            method = "addNewWidgetToList(ILnet/minecraft/enchantment/Enchantment;)Lio/github/cottonmc/cotton/gui/widget;")
    private int modifyRange(Enchantment enchantment) {
        return (int)Math.floor(Math.log(SetMaxLevel.getMaxLevel(enchantment)) * 2.0781) + 1;
    }

    @Inject(at = @At("RETURN"), method = "changeInMap(Lnet/minecraft/enchantment/Enchantment;I)V")
    private void rangeToLevelMap(Enchantment enchantment, int level, CallbackInfo ci) {

        enchantmentsToApply.put(enchantment, (int)Math.floor(Math.pow(1.618, level - 1)));
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/text/TranslatableText;<init>(Ljava/lang/String;)V", ordinal = 1),
            method = "getLabel(Lnet/minecraft/enchantment/Enchantment;I)Lnet/minecraft/text/Text;")
    private static String rangeToLevelLabel(String original) {
        //System.out.println(original.replaceAll("enchantment\\.level\\.", ""));
        int prevLev = Integer.parseInt(original.replaceAll("enchantment\\.level\\.", ""));
        return "enchantment.level." + (int)Math.floor(Math.pow(1.618, prevLev - 1));
    }

    /*@ModifyArg(at = @At(value = "INVOKE", target = "Lio/github/frqnny/darkenchanting/client/gui/DarkEnchanterGUI;addNewWidgetToList(ILnet/minecraft/enchantment/Enchantment;)Lio/github/cottonmc/cotton/gui/widget;"),
            method = "populateList()V", index = 0)
    private int addItem(int original) {
        if (original == 0) return 0;
        return (int)Math.floor(Math.log(original) * 2.0781) + 1;
    }*/
}
