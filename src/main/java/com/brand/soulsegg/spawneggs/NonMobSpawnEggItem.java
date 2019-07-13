package com.brand.soulsegg.spawneggs;

import java.util.Map;
import java.util.Objects;

import com.brand.soulsegg.Nullable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class NonMobSpawnEggItem extends Item {
	   private static final Map<EntityType<?>, NonMobSpawnEggItem> SPAWN_EGGS = Maps.newIdentityHashMap();
	   private final EntityType<?> type;

	   public NonMobSpawnEggItem(EntityType<?> entityType_1, Item.Settings item$Settings_1) {
	      super(item$Settings_1);
	      this.type = entityType_1;
	      SPAWN_EGGS.put(entityType_1, this);
	   }

	   public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1) {
	      World world_1 = itemUsageContext_1.getWorld();
	      if (world_1.isClient) {
	         return ActionResult.SUCCESS;
	      } else {
	         ItemStack itemStack_1 = itemUsageContext_1.getStack();
	         BlockPos blockPos_1 = itemUsageContext_1.getBlockPos();
	         Direction direction_1 = itemUsageContext_1.getSide();
	         BlockState blockState_1 = world_1.getBlockState(blockPos_1);
	         Block block_1 = blockState_1.getBlock();
	         if (block_1 == Blocks.SPAWNER) {
	            BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
	            if (blockEntity_1 instanceof MobSpawnerBlockEntity) {
	               MobSpawnerLogic mobSpawnerLogic_1 = ((MobSpawnerBlockEntity)blockEntity_1).getLogic();
	               EntityType<?> entityType_1 = this.getEntityType(itemStack_1.getTag());
	               mobSpawnerLogic_1.setEntityId(entityType_1);
	               blockEntity_1.markDirty();
	               world_1.updateListeners(blockPos_1, blockState_1, blockState_1, 3);
	               itemStack_1.decrement(1);
	               return ActionResult.SUCCESS;
	            }
	         }

	         BlockPos blockPos_3;
	         if (blockState_1.getCollisionShape(world_1, blockPos_1).isEmpty()) {
	            blockPos_3 = blockPos_1;
	         } else {
	            blockPos_3 = blockPos_1.offset(direction_1);
	         }

	         EntityType<?> entityType_2 = this.getEntityType(itemStack_1.getTag());
	         if (entityType_2.spawnFromItemStack(world_1, itemStack_1, itemUsageContext_1.getPlayer(), blockPos_3, SpawnType.SPAWN_EGG, true, !Objects.equals(blockPos_1, blockPos_3) && direction_1 == Direction.UP) != null) {
	            itemStack_1.decrement(1);
	         }

	         return ActionResult.SUCCESS;
	      }
	   }

	   @SuppressWarnings({ "unchecked", "rawtypes" })
	public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
	      ItemStack itemStack_1 = playerEntity_1.getStackInHand(hand_1);
	      if (world_1.isClient) {
	         return new TypedActionResult(ActionResult.PASS, itemStack_1);
	      } else {
	         HitResult hitResult_1 = rayTrace(world_1, playerEntity_1, RayTraceContext.FluidHandling.SOURCE_ONLY);
	         if (hitResult_1.getType() != HitResult.Type.BLOCK) {
	            return new TypedActionResult(ActionResult.PASS, itemStack_1);
	         } else {
	            BlockHitResult blockHitResult_1 = (BlockHitResult)hitResult_1;
	            BlockPos blockPos_1 = blockHitResult_1.getBlockPos();
	            if (!(world_1.getBlockState(blockPos_1).getBlock() instanceof FluidBlock)) {
	               return new TypedActionResult(ActionResult.PASS, itemStack_1);
	            } else if (world_1.canPlayerModifyAt(playerEntity_1, blockPos_1) && playerEntity_1.canPlaceOn(blockPos_1, blockHitResult_1.getSide(), itemStack_1)) {
	               EntityType<?> entityType_1 = this.getEntityType(itemStack_1.getTag());
	               if (entityType_1.spawnFromItemStack(world_1, itemStack_1, playerEntity_1, blockPos_1, SpawnType.SPAWN_EGG, false, false) == null) {
	                  return new TypedActionResult(ActionResult.PASS, itemStack_1);
	               } else {
	                  if (!playerEntity_1.abilities.creativeMode) {
	                     itemStack_1.decrement(1);
	                  }

	                  playerEntity_1.incrementStat(Stats.USED.getOrCreateStat(this));
	                  return new TypedActionResult(ActionResult.SUCCESS, itemStack_1);
	               }
	            } else {
	               return new TypedActionResult(ActionResult.FAIL, itemStack_1);
	            }
	         }
	      }
	   }

	   public boolean isOfSameEntityType(@Nullable CompoundTag compoundTag_1, EntityType<?> entityType_1) {
	      return Objects.equals(this.getEntityType(compoundTag_1), entityType_1);
	   }

	   @Environment(EnvType.CLIENT)
	   public static NonMobSpawnEggItem forEntity(@Nullable EntityType<?> entityType_1) {
	      return (NonMobSpawnEggItem)SPAWN_EGGS.get(entityType_1);
	   }

	   public static Iterable<NonMobSpawnEggItem> getAll() {
	      return Iterables.unmodifiableIterable(SPAWN_EGGS.values());
	   }

	   @SuppressWarnings("rawtypes")
	public EntityType<?> getEntityType(@Nullable CompoundTag compoundTag_1) {
	      if (compoundTag_1 != null && compoundTag_1.containsKey("EntityTag", 10)) {
	         CompoundTag compoundTag_2 = compoundTag_1.getCompound("EntityTag");
	         if (compoundTag_2.containsKey("id", 8)) {
	            return (EntityType)EntityType.get(compoundTag_2.getString("id")).orElse(this.type);
	         }
	      }

	      return this.type;
	   }
	}

