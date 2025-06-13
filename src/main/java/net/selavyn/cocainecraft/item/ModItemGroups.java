package net.selavyn.cocainecraft.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.selavyn.cocainecraft.CocaineCraft;
import net.selavyn.cocainecraft.block.ModBlocks;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> COCAINECRAFT_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(CocaineCraft.MOD_ID, "cocainecraft"));
    public static final ItemGroup COCAINECRAFT = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CocaineCraft.MOD_ID, "cocainecraft"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PINK_COCAINE))
                    .displayName(Text.translatable("itemgroup.cocainecraft.cocainecraft"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PINK_COCAINE);
                        entries.add(ModItems.SOBER_PILL);
                        entries.add(ModItems.TOTEM_OF_COCAINE);
                    }).build());


    public static void registerItemGroups() {
        CocaineCraft.LOGGER.info("Registering Creative Tabs (" + CocaineCraft.MOD_ID + ")");
        ModBlocks.initialize();
  ;  }
}
