package com.brand.soulsegg.soulseggs;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SoulsEggsItem extends Item {
	   public SoulsEggsItem(Item.Settings item$Settings_1) {
		      super(item$Settings_1);
		   }

		   @Environment(EnvType.CLIENT)
		   public boolean hasEnchantmentGlint(ItemStack itemStack_1) {
		      return true;
		   }
		}

