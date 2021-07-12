package net.fabricmc.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.screen;
import net.minecraft.item.*;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentHelperMixin {
	@Inject(method = "onContentChanged", at = @At("HEAD"))
	private void onContentChanged(CallbackInfo ci) {
		if (inventory == this.inventory) {
			ItemStack lv = inventory.getStack(0);

			if (lv.isEmpty() || !lv.isEnchantable()) {
				for (int i = 0; i < 3; i++) {
					this.enchantmentPower[i] = 0;
					this.enchantmentId[i] = -1;
					this.enchantmentLevel[i] = -1;
				}
			} else {
				this.context.run((arg2, arg3) -> {
					int i = 0;
					//arg2.getBlockState(arg3.add(k * 2, 0, j * 2)).isOf(Blocks.BOOKSHELF)
					for (int r = 2; r <= 8; r++) {
						double c = (r-1)/r;
						for (int x = -r; x <= r; x++) {
							for (int z = -r; z <= r; z++) {
								if (1-r < x && x < r-1 && z == 1-r) {
									z = r;
								}
								for (int y = 0; y < r/2d+1; y++) {
									if (arg2.getBlockState(arg3.add(x, y, z)).isOf(Blocks.BOOKSHELF)) {
										if (arg2.getBlockState(arg3.add(Math.round(x*c), Math.round(y*c), Math.round(z*c))).isOf(Blocks.AIR)) {
											i++;
										}
									}
								}
							}
						}
					}
					this.random.setSeed(this.seed.get());
					for (int l = 0; l < 3; l++) {
						this.enchantmentPower[l] = EnchantmentHelper.calculateRequiredExperienceLevel(this.random, l, i, arg);
						this.enchantmentId[l] = -1;
						this.enchantmentLevel[l] = -1;
						if (this.enchantmentPower[l] < l + 1) {
							this.enchantmentPower[l] = 0;
						}
					}
					for (int m = 0; m < 3; m++) {
						if (this.enchantmentPower[m] > 0) {
							List<EnchantmentLevelEntry> list = generateEnchantments(arg, m, this.enchantmentPower[m]);
							if (list != null && !list.isEmpty()) {
								EnchantmentLevelEntry lv = list.get(this.random.nextInt(list.size()));
								this.enchantmentId[m] = Registry.ENCHANTMENT.getRawId(lv.enchantment);
								this.enchantmentLevel[m] = lv.level;
							}
						}
					}
					sendContentUpdates();
				});
			}
		}
		return;
	}
}
