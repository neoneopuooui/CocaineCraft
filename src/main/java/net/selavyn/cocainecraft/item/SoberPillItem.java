package net.selavyn.cocainecraft.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.minecraft.block.CactusBlock;
import net.selavyn.cocainecraft.CocaineCraft;
import net.selavyn.cocainecraft.entity.damage.ModDamageSources;
import net.selavyn.cocainecraft.entity.effect.ModStatusEffects;
import net.selavyn.cocainecraft.sound.ModSounds;

public class SoberPillItem extends Item {
    public SoberPillItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!user.hasStatusEffect(ModStatusEffects.COCAINE_STATUS) && !user.hasStatusEffect(ModStatusEffects.WEED_STATUS)) {
            if (!world.isClient) { // Make sure this runs on server only
                DamageSource damageSource = new DamageSource(
                        world.getRegistryManager()
                                .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                                .getEntry(ModDamageSources.OVERDOSE.getValue()).get()
                );
                user.serverDamage(damageSource, 10.0F);
                MinecraftClient.getInstance().getSoundManager().stopAll();
                MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.translatable("dialogue.cocainecraft.soberpill_fail", user.getName()));

            }
        } else {
            if (world.isClient) {
                PlayerEntity player = world.getClosestPlayer(user, 3);
                if (player.getInventory().getSlotWithStack(new ItemStack(ModItems.TOTEM_OF_COCAINE)) == -1) {
                    MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.translatable("dialogue.cocainecraft.soberpill", user.getName()));
                }
                MinecraftClient.getInstance().getSoundManager().stop(ModSounds.cokeSound);
            }
        }
        return super.finishUsing(stack, world, user);
    }
}
