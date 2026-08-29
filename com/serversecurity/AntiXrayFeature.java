package com.serversecurity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class AntiXrayFeature {
    public static BlockState maskOre(Level level, BlockPos pos, BlockState state) {
        if (state.is(Blocks.DIAMOND_ORE) || state.is(Blocks.DEEPSLATE_DIAMOND_ORE)) {
            for (Direction dir : Direction.values()) {
                if (level.getBlockState(pos.relative(dir)).isAir()) {
                    return state; // إذا مست الهواء تظهر
                }
            }
            return Blocks.STONE.defaultBlockState(); // خفية إذا كانت مدفونة
        }
        return state;
    }
}
