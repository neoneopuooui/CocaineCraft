package net.selavyn.cocainecraft;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.selavyn.cocainecraft.entity.effect.CocaineStatusEffect;
import net.selavyn.cocainecraft.entity.effect.ModStatusEffects;
import net.selavyn.cocainecraft.item.ModItemGroups;
import net.selavyn.cocainecraft.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.selavyn.cocainecraft.item.ModItems;

public class CocaineCraft implements ModInitializer {
	public static final String MOD_ID = "cocainecraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		LOGGER.info("§6MODS ARE AWESOME YAY :DD");
		ModItems.initialize();
		ModItemGroups.registerItemGroups();
		ModSounds.initialize();
		ModStatusEffects.init();
	}

}