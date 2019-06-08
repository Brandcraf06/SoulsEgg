package com.brand.soulsegg.spawneggs;

import com.brand.soulsegg.SoulsEgg;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModSpawnEggs { 
	
	public static final Item WITHER_SPAWN_EGG = register("wither_spawn_egg", new SpawnEggItem(EntityType.WITHER, 986895, 9408399, new Item.Settings().rarity(Rarity.EPIC).maxCount(64).group(SoulsEgg.SOULS_EGG)));
	public static final Item ENDER_DRAGON_SPAWN_EGG = register("ender_dragon_spawn_egg", new SpawnEggItem(EntityType.ENDER_DRAGON, 1842204, 14711290, new Item.Settings().rarity(Rarity.EPIC).maxCount(64).group(SoulsEgg.SOULS_EGG)));
	public static final Item GIANT_SPAWN_EGG = register("giant_spawn_egg", new SpawnEggItem(EntityType.GIANT, 3891759, 4602533, new Item.Settings().rarity(Rarity.RARE).maxCount(64).group(SoulsEgg.SOULS_EGG)));
	public static final Item BOAT_SPAWN_EGG = register("boat_spawn_egg", new NonMobSpawnEggItem(EntityType.BOAT, new Item.Settings().maxCount(64).group(SoulsEgg.SOULS_EGG)));
	
	public static Item register(String name, Item item) {
	    return Registry.register(Registry.ITEM, new Identifier(SoulsEgg.MOD_ID, name), item);
	}
}