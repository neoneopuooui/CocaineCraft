package net.selavyn.cocainecraft.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.World;
import net.selavyn.cocainecraft.villager.ModVillagers;

public class DealerSpawnEggItem extends Item {
    public DealerSpawnEggItem(Settings settings) {
        super(settings);
    }

    /*@Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!world.isClient) {
            BlockPos pos = context.getBlockPos().offset(context.getSide());
            VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, world);
            villager.refreshPositionAndAngles(pos, 0, 0);
            // Set villager profession
            villager.setVillagerData(villager.getVillagerData()
                    .withProfession(RegistryEntry.of(ModVillagers.DEALER))
                    .withLevel(2));

            // Optional: prevent profession change by removing their POI
            villager.setPersistent();

            world.spawnEntity(villager);

            // Optionally: shrink stack
            if (!context.getPlayer().isCreative()) {
                context.getStack().decrement(1);
            }

            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        MinecraftClient client = MinecraftClient.getInstance();
        HitResult hit = client.crosshairTarget;
        BlockHitResult blockHit = (BlockHitResult) hit;
        BlockPos blockPos = blockHit.getBlockPos();
        VillagerEntity dealer = EntityType.VILLAGER.create(world);
        if (hit.getType()==HitResult.Type.BLOCK){
            if (dealer != null) {
                dealer.refreshPositionAndAngles(x, y, z, 0, 0); // set position

                // set profession, type and level
                dealer.setVillagerData(
                        dealer.getVillagerData()
                                .withProfession(ModVillagers.DEALER) // your custom profession
                                .withType(VillagerType.PLAINS) // biome type
                                .withLevel(1)
                );

                dealer.setPersistent(); // don't despawn
                world.spawnEntity(dealer); // actually spawn it
            }

        }
        return ActionResult.SUCCESS;*/
    }
