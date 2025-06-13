package net.selavyn.cocainecraft.block;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.selavyn.cocainecraft.CocaineCraft;
import net.selavyn.cocainecraft.item.ModItems;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputerBlock extends HorizontalFacingBlock {

    public static final BooleanProperty RUNNING = BooleanProperty.of("running");
    public static final Logger LOGGER = LoggerFactory.getLogger(CocaineCraft.MOD_ID);
    public static final MapCodec<ComputerBlock> CODEC = createCodec(ComputerBlock::new);

    public ComputerBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(RUNNING, false));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(RUNNING);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        boolean running = state.get(RUNNING);
        boolean newState = !running;

        world.setBlockState(pos, state.with(RUNNING, newState));

        if (newState) {
            // Si on vient d'allumer, on lance le premier tick
            world.scheduleBlockTick(pos, this, 25*20);
        }

        LOGGER.info("Computer running: " + newState);
        return ActionResult.SUCCESS;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(RUNNING)) {
            for (ServerPlayerEntity usr : world.getPlayers()) {
                usr.giveItemStack(new ItemStack(ModItems.PINK_COCAINE));
                usr.giveItemStack(new ItemStack(ModItems.SOBER_PILL));
            }
            // Replanifie le tick pour continuer tant que c’est allumé
            world.scheduleBlockTick(pos, this, 25*20);
        } else {
            LOGGER.info("Computer is off — no drop.");
        }
    }
}
