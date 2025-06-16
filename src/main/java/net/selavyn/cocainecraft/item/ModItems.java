package net.selavyn.cocainecraft.item;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.consume.RemoveEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.selavyn.cocainecraft.CocaineCraft;
import net.selavyn.cocainecraft.block.ModBlocks;
import net.selavyn.cocainecraft.entity.effect.ModStatusEffects;

import java.util.function.Function;

import static net.selavyn.cocainecraft.CocaineCraft.MOD_ID;

public class ModItems {

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    private static Function<Item.Settings, Item> createBlockItemWithUniqueName(Block block) {
        return (settings) -> new BlockItem(block, settings.useItemPrefixedTranslationKey());
    }

    public static final ConsumableComponent LA_COKE_C_SUPER_FOOD_CONSUMABLE_COMPONENT = ConsumableComponents.food()
            // The duration is in ticks, 20 ticks = 1 second
            .build();
    public static final ConsumableComponent SOBER_PILL_FOOD_CONSUMABLE_COMPONENT = ConsumableComponents.food()
            .consumeEffect(new RemoveEffectsConsumeEffect(StatusEffects.NAUSEA))
            .consumeEffect(new RemoveEffectsConsumeEffect(StatusEffects.SPEED))
            .consumeEffect(new RemoveEffectsConsumeEffect(StatusEffects.STRENGTH))
            .consumeEffect(new RemoveEffectsConsumeEffect(StatusEffects.LEVITATION))
            .consumeEffect(new RemoveEffectsConsumeEffect(StatusEffects.NIGHT_VISION))
            .consumeEffect(new RemoveEffectsConsumeEffect(ModStatusEffects.COCAINE_STATUS))
            .consumeEffect(new RemoveEffectsConsumeEffect(ModStatusEffects.WEED_STATUS))
            .build();
    public static final FoodComponent NORMAL_FOOD_COMPONENT = new FoodComponent.Builder()
            .alwaysEdible()
            .build();


    public static final Item PINK_COCAINE = register("pink_cocaine", PinkCocaineItem::new, new Item.Settings().food(NORMAL_FOOD_COMPONENT, LA_COKE_C_SUPER_FOOD_CONSUMABLE_COMPONENT));;
    public static final Item SOBER_PILL = register("sober_pill", SoberPillItem::new, new Item.Settings().food(NORMAL_FOOD_COMPONENT, SOBER_PILL_FOOD_CONSUMABLE_COMPONENT));
    public static final Item TOTEM_OF_COCAINE = register("totem_of_cocaine", TotemCocaineItem::new, new Item.Settings());
    public static final Item WEED_SEEDS = register("weed_seeds", createBlockItemWithUniqueName(ModBlocks.WEED_CROP), new Item.Settings());
    public static final Item WEED = register("weed", WeedItem::new, new Item.Settings().food(NORMAL_FOOD_COMPONENT, LA_COKE_C_SUPER_FOOD_CONSUMABLE_COMPONENT));

    //public static final Item DEALER_SPAWN_EGG = register("dealer_spawn_egg", DealerSpawnEggItem::new, new Item.Settings());


    public static final Item VILLAGER_KILLER = register("villager_killer", VillagerKillerItem::new, new Item.Settings());

    public static void initialize() {}
}