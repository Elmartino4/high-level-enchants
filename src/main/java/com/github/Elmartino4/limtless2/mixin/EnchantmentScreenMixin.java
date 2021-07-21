package com.github.Elmartino4.limitless2.mixin;

import com.github.Elmartino4.limitless2.config.ModConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenMixin {
	@ModifyVariable(method = "method_17411", ordinal = 0, remap = false, at = @At(value = "INVOKE", target = "Ljava/util/Random;setSeed"))
	private int countBookshelves(int previous, ItemStack stack, World world, BlockPos pos) {
		int books = 0;
		//arg2.getBlockState(arg3.add(k * 2, 0, j * 2)).isOf(Blocks.BOOKSHELF)
		for (int r = 2; r <= 8; r++) {
			for (int x = -r; x <= r; x++) {
				int signX = Integer.signum(x);
				for (int z = -r; z <= r; z++) {
					if (1-r < x && x < r-1 && z == 1-r) {
						z = r;
					}

					int signZ = Integer.signum(z);
					for (int y = 0; y < r/2d+1; y++) {
						if (world.getBlockState(pos.add(x, y, z)).isOf(Blocks.BOOKSHELF)) {
							if (world.isAir(pos.add(x-signX, y, z-signZ))) {
								books++;
							}
						}
					}
				}
			}
		}
		int enchantPower = ModConfig.INSTANCE.bookshelfCounter.enchantPower;
		int level =
			(int)Math.floor(
				(1 -
					Math.pow(enchantPower,-books/100d)
				)
				*(ModConfig.INSTANCE.bookshelfCounter.enchantMultiplier) + books/(double)(ModConfig.INSTANCE.bookshelfCounter.enchantDivConst)
			);
		System.out.println("books = " + books + ", lvl = " + level);
		return level;
	}
}
/*
int enchantPower = 8;
int enchantMultiplier = 35;
int enchantDivConst = 40;
*/
