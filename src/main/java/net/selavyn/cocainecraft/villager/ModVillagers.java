package net.selavyn.cocainecraft.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.selavyn.cocainecraft.CocaineCraft;
import net.selavyn.cocainecraft.block.ModBlocks;
import net.selavyn.cocainecraft.item.ModItems;

public class ModVillagers {

    public static final RegistryKey<PointOfInterestType> DEAL_POI_KEY = registerPoiKey("deal_poi");
    public static final PointOfInterestType DEAL_POI = registerPOI("deal_poi", ModBlocks.COCAINE_BLOCK);

    public static final RegistryKey<VillagerProfession> DEALER_KEY = registerProfessionKey("dealer");
    public static final VillagerProfession DEALER = registerProfession("dealer", DEAL_POI_KEY);


    public static final RegistryKey<PointOfInterestType> VAPE_POI_KEY = registerPoiKey("vape_poi");
    public static final PointOfInterestType VAPE_POI = registerPOI("vape_poi", Blocks.COMMAND_BLOCK);

    public static final RegistryKey<VillagerProfession> VAPER_KEY = registerProfessionKey("vaper");
    public static final VillagerProfession VAPER = registerProfession("vaper", VAPE_POI_KEY);

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of(CocaineCraft.MOD_ID, name),
                new VillagerProfession(Text.translatable("profession." + CocaineCraft.MOD_ID + "." + name), entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN));
    }

    private static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(Identifier.of(CocaineCraft.MOD_ID, name),
                1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> registerPoiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(CocaineCraft.MOD_ID, name));
    }

    private static RegistryKey<VillagerProfession> registerProfessionKey(String name) {
        return RegistryKey.of(RegistryKeys.VILLAGER_PROFESSION, Identifier.of(CocaineCraft.MOD_ID, name));
    }

    public static void registerVillagers() {
        CocaineCraft.LOGGER.info("Registering Villagers for " + CocaineCraft.MOD_ID);
        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEALER_KEY, 1, factories -> {

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD,1),
                    new ItemStack(ModItems.SOBER_PILL, 1), 5000, 1, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD,2),
                    new ItemStack(ModItems.PINK_COCAINE, 1), 5000, 1, 0));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEALER_KEY, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD,5),
                    new ItemStack(ModItems.WEED_SEEDS, 1), 5000, 2, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD,10),
                    new ItemStack(ModItems.TOTEM_OF_COCAINE, 1), 5000, 2, 0));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEALER_KEY, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD,20),
                    new ItemStack(ModBlocks.CRACKHEAD_COMPUTER.asItem() , 1), 5000, 3, 0));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.VAPER_KEY, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD,1),
                    new ItemStack(Items.ARROW , 1), 5000, 3, 0));
        });
    }
}
