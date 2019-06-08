package com.brand.soulsegg.soulseggs;

import com.brand.soulsegg.SoulsEgg;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class AllSoulsEggs {
	
	public static final Item BLANK_EGG = register("blank_egg", new Item(new Item.Settings().maxCount(64).group(SoulsEgg.SOULS_EGG)));
	public static final Item SOULS_EGG = register("souls_egg", new SoulsEggsItem(new Item.Settings().maxCount(64).group(SoulsEgg.SOULS_EGG)));
	public static final Item ADVANCED_SOULS_EGG = register("advanced_souls_egg", new SoulsEggsItem(new Item.Settings().maxCount(16).group(SoulsEgg.SOULS_EGG)));
	public static final Item ULTIMATE_SOULS_EGG = register("ultimate_souls_egg", new SoulsEggsItem(new Item.Settings().rarity(Rarity.RARE).maxCount(1).group(SoulsEgg.SOULS_EGG)));
	public static final Item ANIMALS_SOULS_EGG = register("animals_souls_egg", new SoulsEggsItem(new Item.Settings().maxCount(64).group(SoulsEgg.SOULS_EGG)));
	public static final Item SOULS_POWDER = register("souls_powder", new Item(new Item.Settings().maxCount(64).group(SoulsEgg.SOULS_EGG)));
	public static final Item ENDER_SKIN = register("ender_skin", new Item(new Item.Settings().maxCount(64).group(SoulsEgg.SOULS_EGG)));
	

	   public boolean hasEnchantmentGlint(ItemStack itemStack_1) {
	      return true;
	   }
	
	public static Item register(String name, Item item) {
	    return Registry.register(Registry.ITEM, new Identifier(SoulsEgg.MOD_ID, name), item);
	}
}
