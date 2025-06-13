package net.selavyn.cocainecraft.item;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.selavyn.cocainecraft.CocaineCraft;
import net.selavyn.cocainecraft.entity.damage.ModDamageSources;
import net.selavyn.cocainecraft.entity.effect.CocaineStatusEffect;
import net.selavyn.cocainecraft.entity.effect.ModStatusEffects;
import org.slf4j.Logger;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.selavyn.cocainecraft.sound.ModSounds;
import org.slf4j.LoggerFactory;

public class PinkCocaineItem extends Item {
    public PinkCocaineItem(Settings settings) {
        super(settings);
    }
    public static final Logger LOGGER = LoggerFactory.getLogger(CocaineCraft.MOD_ID);
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity player = world.getClosestPlayer(user, 3);
        LOGGER.info("TotemCheck: " + String.valueOf(player.getInventory().getSlotWithStack(new ItemStack(ModItems.TOTEM_OF_COCAINE))));
        if (world.isClient) {
            if (player.getInventory().getSlotWithStack(new ItemStack(ModItems.TOTEM_OF_COCAINE)) == -1) {
                MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.translatable("dialogue.cocainecraft.cocaine", user.getName()));
            }
        }
        if (!world.isClient) {
            if (player.getInventory().getSlotWithStack(new ItemStack(ModItems.TOTEM_OF_COCAINE)) == -1) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 1000, 1, false, false));
                MinecraftClient.getInstance().getSoundManager().play(ModSounds.cokeSound);
            }
            if (user.hasStatusEffect(ModStatusEffects.COCAINE_STATUS)){
                DamageSource damageSource = new DamageSource(
                        world.getRegistryManager()
                                .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                                .getEntry(ModDamageSources.OVERDOSE.getValue()).get()
                );
                user.serverDamage(damageSource, 999999.0F);
                MinecraftClient.getInstance().getSoundManager().stopAll();
            }
            //PlayerEntity player = world.getClosestPlayer(user, 3);


            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000, 1, false, false));
            user.addStatusEffect(new StatusEffectInstance(ModStatusEffects.COCAINE_STATUS, 1000, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1000, 2, false, false));
        }

        return super.finishUsing(stack, world, user);
    }
}
