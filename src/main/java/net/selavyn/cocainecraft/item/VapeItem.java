package net.selavyn.cocainecraft.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.FillCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.selavyn.cocainecraft.CocaineCraft;
import net.selavyn.cocainecraft.entity.damage.ModDamageSources;
import net.selavyn.cocainecraft.entity.effect.ModStatusEffects;
import net.selavyn.cocainecraft.sound.ModSounds;
import org.apache.logging.log4j.core.jmx.Server;

public class VapeItem extends Item {
    public VapeItem(Settings settings) {
        super(settings);
    }
    float smokespeed = 5;
    PlayerEntity player;
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        CocaineCraft.LOGGER.info("vape lulz");

        if(user.hasStatusEffect(StatusEffects.HEALTH_BOOST)){
            if(user.getStatusEffect(StatusEffects.HEALTH_BOOST).getAmplifier()==4){
                DamageSource damageSource = new DamageSource(
                        world.getRegistryManager()
                                .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                                .getEntry(ModDamageSources.OVERDOSE.getValue()).get()
                );
                user.serverDamage(damageSource, 10000F);
            }
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 2147483647, user.getStatusEffect(StatusEffects.HEALTH_BOOST).getAmplifier()+1, false, false));
        }

        user.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 2147483647, 1, false, false));

        for (int i = 0; i <= 1; i = i + 1) {
            user.getFacing().getVector().getX();
            world.addParticleClient(
                    ParticleTypes.LARGE_SMOKE,
                    user.getX(),
                    user.getY() + 1.5,
                    user.getZ(),
                    user.getFacing().getVector().getX()/smokespeed, 0.1, user.getFacing().getVector().getZ()/smokespeed
            );
        }


        return super.finishUsing(stack, world, user);
    }
}
