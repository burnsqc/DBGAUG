package com.dbgaug.util.math;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class PosDiffs {

	public static Vec3 entityRelativeToBlock(Entity entity, BlockPos blockPos) {
		double diffX = entity.getX() - blockPos.getX();
		double diffY = entity.getY() - blockPos.getY();
		double diffZ = entity.getZ() - blockPos.getZ();
		return new Vec3(diffX, diffY, diffZ);
	}

	public static Vec3 regionRelativeToLevel(BlockPos blockPos) {
		double diffX = blockPos.getX() >> 4 >> 5;
		double diffY = 0;
		double diffZ = blockPos.getZ() >> 4 >> 5;
		return new Vec3(diffX, diffY, diffZ);
	}

	public static Vec3i chunkRelativeToLevel(BlockPos blockPos) {
		int diffX = blockPos.getX() >> 4;
		int diffY = 0;
		int diffZ = blockPos.getZ() >> 4;
		return new Vec3i(diffX, diffY, diffZ);
	}
}
