package net.selavyn.cocainecraft.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.selavyn.cocainecraft.CocaineCraft;
import net.selavyn.cocainecraft.block.custom.WeedCropBlock;
import net.selavyn.cocainecraft.item.ModItemGroups;

import java.util.function.Function;

public class ModBlocks {
	private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
		// Create a registry key for the block
		RegistryKey<Block> blockKey = keyOfBlock(name);
		// Create the block instance
		Block block = blockFactory.apply(settings.registryKey(blockKey));

		// Sometimes, you may not want to register an item for the block.
		// Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
		if (shouldRegisterItem) {
			// Items need to be registered with a different type of registry key, but the ID
			// can be the same.
			RegistryKey<Item> itemKey = keyOfItem(name);

			BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
			Registry.register(Registries.ITEM, itemKey, blockItem);
		}

		return Registry.register(Registries.BLOCK, blockKey, block);
	}

	private static RegistryKey<Block> keyOfBlock(String name) {
		return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CocaineCraft.MOD_ID, name));
	}

	private static RegistryKey<Item> keyOfItem(String name) {
		return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CocaineCraft.MOD_ID, name));
	}

	public static final Block COCAINE_BLOCK = register(
			"cocaine_block",
			CocaineBlockClass::new,
			AbstractBlock.Settings.copy(Blocks.SAND),
			true
	);

	public static final Block PINK_COCAINE_BLOCK = register(
			"pink_cocaine_block",
			CocaineBlockClass::new,
			AbstractBlock.Settings.copy(Blocks.SAND),
			true
	);

	public static final Block CRACKHEAD_COMPUTER = register(
			"crackhead_computer",
			ComputerBlock::new,
			AbstractBlock.Settings.create().nonOpaque().strength(1.0F),
			true
	);

	public static final Block WEED_CROP = register(
			"weed_crop",
			WeedCropBlock::new,
			Block.Settings.create().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).nonOpaque(),
			false
	);

	public static void initialize() {
		ItemGroupEvents.modifyEntriesEvent(ModItemGroups.COCAINECRAFT_KEY).register((itemGroup) -> {
			itemGroup.add(ModBlocks.CRACKHEAD_COMPUTER.asItem());
			itemGroup.add(ModBlocks.COCAINE_BLOCK.asItem());
			itemGroup.add(ModBlocks.PINK_COCAINE_BLOCK.asItem());
		});
	}
}