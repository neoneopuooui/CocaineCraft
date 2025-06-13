package net.selavyn.cocainecraft.entity.damage;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.selavyn.cocainecraft.CocaineCraft;

public class ModDamageSources {
    public static final RegistryKey<DamageType> OVERDOSE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(CocaineCraft.MOD_ID, "overdose"));
}
