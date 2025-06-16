package net.selavyn.cocainecraft;

import net.fabricmc.api.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.selavyn.cocainecraft.block.ModBlocks;
@Environment(EnvType.CLIENT)
public class CocaineCraftClient implements ClientModInitializer {
    public void onInitializeClient() {
        // To make some parts of the block transparent (like glass, saplings and doors):
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEED_CROP, RenderLayer.getCutout());
    }
}