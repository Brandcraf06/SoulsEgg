package com.brand.soulsegg;

import com.brand.soulsegg.soulseggs.AllSoulsEggs;
import com.brand.soulsegg.spawneggs.ModSpawnEggs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class SoulsEgg implements ModInitializer {
	
	public static final String MOD_ID = "soulsegg";
	public static final String VERSION = "1.0.1";
	public static final String NAME = "SoulsEgg";
	public static final ItemGroup SOULS_EGG = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "souls_egg"), () -> new ItemStack(AllSoulsEggs.SOULS_EGG));
	
	@Override
	public void onInitialize() {
		
		new AllSoulsEggs();
		new ModSpawnEggs();
	}
}

