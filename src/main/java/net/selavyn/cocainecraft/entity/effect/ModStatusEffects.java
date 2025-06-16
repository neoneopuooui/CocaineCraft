package net.selavyn.cocainecraft.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.selavyn.cocainecraft.CocaineCraft;

public class ModStatusEffects {

    public static final RegistryEntry<StatusEffect> COCAINE_STATUS =
            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(CocaineCraft.MOD_ID, "cocaine_status"), new CocaineStatusEffect());

    public static final RegistryEntry<StatusEffect> WEED_STATUS =
            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(CocaineCraft.MOD_ID, "weed_status"), new WeedStatusEffect());

    public static void init(){}
}
