package net.selavyn.cocainecraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;


public class CocaineSlabClass extends Block {
    public static final EnumProperty<SlabType> TYPE = null;
    private static final VoxelShape BOTTOM_SHAPE = null;
    private static final VoxelShape TOP_SHAPE = null;

    public CocaineSlabClass(Settings settings) {
        super(settings);
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape var10000;
        switch ((SlabType)state.get(TYPE)) {
            case TOP -> var10000 = TOP_SHAPE;
            case BOTTOM -> var10000 = BOTTOM_SHAPE;
            case DOUBLE -> var10000 = VoxelShapes.fullCube();
            default -> throw new MatchException((String)null, (Throwable)null);
        }

        return var10000;
    }
}
