package com.brand.soulsegg;

import com.brand.soulsegg.content.ModItems;
import com.brand.soulsegg.content.ModSpawnEggs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoulsEgg implements ModInitializer {

	public static final String MOD_ID = "soulsegg";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final ItemGroup SOULS_EGG = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "souls_egg"), () -> new ItemStack(ModItems.SOULS_EGG));

	@Override
	public void onInitialize() {
		new ModItems();
		new ModSpawnEggs();
	}
}

