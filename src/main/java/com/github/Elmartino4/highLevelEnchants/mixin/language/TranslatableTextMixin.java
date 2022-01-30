package com.github.Elmartino4.highLevelEnchants.mixin.language;

import com.github.Elmartino4.highLevelEnchants.config.ModConfig;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.TreeMap;

@Mixin(TranslatableText.class)
public class TranslatableTextMixin {
    private final static TreeMap<Integer, String> map = new TreeMap<>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    @Redirect(method = "updateTranslations", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Language;get(Ljava/lang/String;)Ljava/lang/String;"))
    private String translateBetter(Language language, String key) {
        key = language.get(key);

        if (key.matches("enchantment\\.level\\.\\d+") && ModConfig.INSTANCE.bypassLanguageFiles) {
            int num = Integer.parseInt(key.replaceAll("\\D", ""));

            StringBuilder roman = new StringBuilder();
            while (num > 0) {
                int l =  map.floorKey(num);

                roman.append(map.get(l));

                num -= l;
            }

            key.replaceAll("enchantment\\.level\\.\\d+", roman.toString());

        }

        return key;
    }
}
