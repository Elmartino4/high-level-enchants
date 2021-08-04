package com.github.Elmartino4.limitless2.mixin;

import com.github.Elmartino4.limitless2.SetMaxLevel;
import com.github.Elmartino4.limitless2.config.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.TranslatableText;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.TreeMap;

@Mixin(Enchantment.class)
public class EnchantmentClassMixin {

	private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

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

	@Redirect(method = "getName(I)Lnet/minecraft/text/Text;", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
	private int setMaxLevel(Enchantment ench){
		System.out.println("class");
		return SetMaxLevel.getMaxLevel(ench);
	}

	/*this doesnt work yet
	@Redirect(method = "<clinit>", at = @At(value = "NEW", target = "net/minecraft/text/TranslatableText", ordinal = 1))
  private TranslatableText tryBypassLanguageFiles(String prev) {
		/*if (ModConfig.INSTANCE.bypassLanguageFiles){
			try {
				int lvl = Integer.parseInt(prev.split(".", -2)[2]);
				return toRoman(lvl);
			}
			catch(Exception e) {
				return prev;
			}
		}
  	return new TranslatableText(prev);
  }
*/
	private static String toRoman(int number) {
		int l =  map.floorKey(number);
    if (number == l) {
      return map.get(number);
    }
    return map.get(l) + toRoman(number-l);
	}
}
