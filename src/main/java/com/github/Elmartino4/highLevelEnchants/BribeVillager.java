package com.github.Elmartino4.highLevelEnchants;

import com.github.Elmartino4.highLevelEnchants.config.ModConfig;

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

    private TradeOfferList offerList;
    private boolean usedgoldBlock;
    private boolean success;
    private int tradeIndex;
    private Random random;
    private boolean decrementItem;

    public BribeVillager(TradeOfferList originalList, boolean goldBlock) {
        this.offerList = new TradeOfferList();
        this.usedgoldBlock = goldBlock;
        this.random = new Random();
        this.success = false;

        try {
            this.offerList = (TradeOfferList) originalList.clone();
        } catch (Exception ignored) {

        }

        this.decrementItem = false;
        this.tradeIndex = selectBookTrade();
        doBribe();
    }

    public boolean getDecrementItem() {
        return this.decrementItem;
    }

    public boolean isSuccessful() {
        return this.success;
    }

    public TradeOfferList getNewList() {
        return this.offerList;
    }

    private void doBribe() {
        this.success = false;
        if (this.tradeIndex != -1) {
            this.decrementItem = true;

            int level = getMaxLevel(this.offerList.get(this.tradeIndex));
            double probability = (this.usedgoldBlock) ? ModConfig.INSTANCE.blockMultiplier / (level + ModConfig.INSTANCE.blockMultiplier) : ModConfig.INSTANCE.ingotMultiplier / (level + ModConfig.INSTANCE.ingotMultiplier);

            TradeOffer oldOffer = this.offerList.get(this.tradeIndex);
            if (random.nextDouble() < probability) {
                setNewOffer();
                this.success = true;
            }
        } else {
            System.out.println("no Trades Found");
        }
    }

    private int selectBookTrade() {
        ArrayList<Integer> offerIds = new ArrayList<Integer>();
        for (int i = 0; i < this.offerList.size(); i++) {
            boolean isValid = true;
            if (this.offerList.get(i).getSellItem().getItem() == Items.ENCHANTED_BOOK) {
                Map<Enchantment, Integer> enchants = EnchantmentHelper.get(this.offerList.get(i).getSellItem());
                Set<Enchantment> keys = enchants.keySet();
                for (Enchantment next : keys) {
                    //System.out.println(next.getTranslationKey());
                    //System.out.println(getExpFromIndex(i));
                    int level = enchants.get(next);
                    if (level >= ModConfig.INSTANCE.enchantCategoryMap.get(next.getTranslationKey()) || level >= ModConfig.INSTANCE.villagerMinMax.get(getExpFromIndex(i))[1])
                        isValid = false;
                }
            } else {
                isValid = false;
            }
            if (isValid)
                offerIds.add(i);
        }

        if (offerIds.size() != 0)
            return offerIds.get(this.random.nextInt(offerIds.size()));
        return -1;
    }

    private static int getMaxLevel(TradeOffer offer) {
        int out = 0;
        Map<Enchantment, Integer> enchants = EnchantmentHelper.get(offer.getSellItem());
        Set<Enchantment> keys = enchants.keySet();
        Iterator<Enchantment> i = keys.iterator();
        while (i.hasNext()) {
            int level = enchants.get(i.next());
            if (level > out)
                out = level;
        }
        return out;
    }

    private void setNewOffer() {
        TradeOffer oldOffer = this.offerList.get(this.tradeIndex);

        Map<Enchantment, Integer> enchants = EnchantmentHelper.get(oldOffer.getSellItem());
        Set<Enchantment> keys = enchants.keySet();
        for (Enchantment next : keys) {
            int level = enchants.get(next) + 1;
            enchants.put(next, level);
        }
        ItemStack sellItem = new ItemStack((ItemConvertible) Items.ENCHANTED_BOOK);

        EnchantmentHelper.set(enchants, sellItem);

        this.offerList.set(this.tradeIndex, new TradeOffer(
                oldOffer.getOriginalFirstBuyItem(),
                oldOffer.getSecondBuyItem(),
                sellItem,
                oldOffer.getMaxUses(),
                oldOffer.getMerchantExperience(),
                oldOffer.getPriceMultiplier()
        ));
    }

    private static int getExpFromIndex(int index) {
        return switch (index) {
            case 2, 3 -> 5;
            case 4, 5 -> 10;
            case 6, 7 -> 15;
            default -> 1;
        };
    }
}
