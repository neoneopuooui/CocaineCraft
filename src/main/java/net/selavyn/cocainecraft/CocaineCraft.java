package net.selavyn.cocainecraft;

import net.fabricmc.api.ModInitializer;

import net.selavyn.cocainecraft.datagen.ModModelProvider;
import net.selavyn.cocainecraft.entity.effect.ModStatusEffects;
import net.selavyn.cocainecraft.item.ModItemGroups;
import net.selavyn.cocainecraft.sound.ModSounds;
import net.selavyn.cocainecraft.villager.ModVillagers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.selavyn.cocainecraft.item.ModItems;

public class CocaineCraft implements ModInitializer {
	public static final String MOD_ID = "cocainecraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private boolean debug = true;


	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModVillagers.registerVillagers();
		ModItemGroups.registerItemGroups();
		ModSounds.initialize();
		ModStatusEffects.init();
		ModModelProvider.init();
	}

}