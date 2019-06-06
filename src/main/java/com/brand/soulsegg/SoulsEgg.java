package com.brand.illegal;

import com.brand.illegal.content.Test;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ThatsIllegal implements ModInitializer {
	
	public static final String MOD_ID = "illegal";
	public static final String VERSION = "1.0.0";
	public static final String NAME = "ThatsIllegal";
	public static final ItemGroup ILLEGAL = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "illegal"), () -> new ItemStack(Blocks.DIRT));
	
	@Override
	public void onInitialize() {
		
		Test.init();
	}
}

