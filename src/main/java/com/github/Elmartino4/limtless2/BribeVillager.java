package com.github.Elmartino4.limitless2;

import com.github.Elmartino4.limitless2.config.ModConfig;

import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

//TODO MAKE THIS NON static
//IM SO STOOPID FOR MAKING THIS STATIC

public class BribeVillager {
	public static TradeOfferList doBribe(TradeOfferList originalList, boolean goldBlock) {
		TradeOfferList offerList = new TradeOfferList();
		try{
			offerList = (TradeOfferList)originalList.clone();
		}catch(Exception e){

		}

		Random random = new Random();
		int selectedTrade = selectBookTrade(offerList, random);
		if(selectedTrade != -1){
			int level = getLevel(offerList.get(selectedTrade));
			double probability = (goldBlock) ? ModConfig.INSTANCE.blockMultiplier/(level+ModConfig.INSTANCE.blockMultiplier) : ModConfig.INSTANCE.ingotMultiplier/(level+ModConfig.INSTANCE.ingotMultiplier);

			TradeOffer oldOffer = offerList.get(selectedTrade);
			if(random.nextDouble() < probability)
				offerList.set(selectedTrade, getNewOffer(oldOffer));
		}else{
			System.out.println("no Trades Found");
		}
		return offerList;
  }

	private static int selectBookTrade(TradeOfferList offerList, Random random){
		ArrayList<Integer> offerIds = new ArrayList<Integer>();

		for (int i = 0; i < offerList.size(); i++) {
			NbtCompound enchNBT = getEnchantmentNbt(offerList.get(i).getSellItem()).getCompound(0);

			if(offerList.get(i).getSellItem().getItem() == Items.ENCHANTED_BOOK){
				String path = EnchantmentHelper.getIdFromNbt(enchNBT).getPath();
				String namespace = EnchantmentHelper.getIdFromNbt(enchNBT).getNamespace();

				if(ModConfig.INSTANCE.pathConverter.containsKey(namespace)){
					String key = ModConfig.INSTANCE.pathConverter.get(namespace) + path;

					if(ModConfig.INSTANCE.enchantCategoryMap.containsKey(key))
						if(ModConfig.INSTANCE.enchantCategoryMap.get(key) > EnchantmentHelper.getLevelFromNbt(enchNBT) && ModConfig.INSTANCE.villagerMinMax.get(getExpFromIndex(i))[1] > EnchantmentHelper.getLevelFromNbt(enchNBT))
							offerIds.add(i);
				}
			}else{
				System.out.println("not a book");
			}
		}

		if(offerIds.size() != 0)
			return offerIds.get(random.nextInt(offerIds.size()));
		return -1;
	}

	private static int getLevel(TradeOffer offer){
		NbtCompound enchNBT = getEnchantmentNbt(offer.getSellItem()).getCompound(0);
		return EnchantmentHelper.getLevelFromNbt(enchNBT);
	}

	private static TradeOffer getNewOffer(TradeOffer oldOffer){
		/*NbtCompound enchNBT = oldOffer.getSellItem().getNbt();
		NbtCompound asItemStack = getEnchantmentNbt(oldOffer.getSellItem()).getCompound(0);

		EnchantmentHelper.writeLevelToNbt(asItemStack, getLevel(oldOffer) + 1);
		NbtElement storedEnchantments = asItemStack.get("StoredEnchantments");
		enchNBT.put("StoredEnchantments", storedEnchantments);*/

		Map<Enchantment, Integer> enchants = EnchantmentHelper.get(oldOffer.getSellItem());
		Set<Enchantment> keys = enchants.keySet();
		Iterator<Enchantment> i = keys.iterator();
		while (i.hasNext()) {
			Enchantment next = i.next();
			int level = enchants.get(next) + 1;
		  enchants.put(next, level);
		}
		ItemStack sellItem = new ItemStack((ItemConvertible)Items.ENCHANTED_BOOK);

		EnchantmentHelper.set(enchants, sellItem);

		return new TradeOffer(oldOffer.getOriginalFirstBuyItem(), oldOffer.getSecondBuyItem(), sellItem, oldOffer.getMaxUses(), oldOffer.getMerchantExperience(), oldOffer.getPriceMultiplier());
	}

	private static NbtList getEnchantmentNbt(ItemStack stack) {
		NbtCompound lv = stack.getNbt();
		if (lv != null) {
			return lv.getList("StoredEnchantments", 10);
		}
		return new NbtList();
	}

	private static int getExpFromIndex(int index){
		switch(index){
			case 0:
				return 1;
			case 1:
				return 1;
			case 2:
				return 5;
			case 3:
				return 5;
			case 4:
				return 10;
			case 5:
				return 10;
			case 6:
				return 15;
			case 7:
				return 15;
		}
		return 1;
	}
}
