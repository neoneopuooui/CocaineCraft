package net.selavyn.cocainecraft.sound;

import net.minecraft.client.sound.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.selavyn.cocainecraft.CocaineCraft;
import org.jetbrains.annotations.Nullable;

public class ModSounds {
    private ModSounds() {
        // private empty constructor to avoid accidental instantiation
    }

    // ITEM_METAL_WHISTLE is the name of the custom sound event
    // and is called in the mod to use the custom sound
    public static SoundEvent MINECRAFT_ON_COCAINE = registerSound("minecraft_on_cocaine");

    public static SoundInstance cokeSound = PositionedSoundInstance.master(MINECRAFT_ON_COCAINE, 1.0F, 0.5F);
    // actual registration of all the custom SoundEvents
    private static SoundEvent registerSound(String name) {
        Identifier id = Identifier.of(CocaineCraft.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, name, SoundEvent.of(id));
    }

    // This static method starts class initialization, which then initializes
    // the static class variables (e.g. ITEM_METAL_WHISTLE).
    public static void initialize() {
        CocaineCraft.LOGGER.info("Registering " + CocaineCraft.MOD_ID + " Sounds");
        // Technically this method can stay empty, but some developers like to notify
        // the console, that certain parts of the mod have been successfully initialized
    }
}