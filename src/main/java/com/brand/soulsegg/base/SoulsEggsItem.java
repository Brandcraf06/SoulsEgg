package com.brand.soulsegg.base;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class SoulsEggsItem extends Item {

	public SoulsEggsItem(Settings settings) {
		super(settings);
	}

	public boolean hasGlint(ItemStack stack) {
		return true;
	}
}

