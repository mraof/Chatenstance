package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class RoomEmptyBig extends Room
{
	public RoomEmptyBig(Block[] blocks) {
		super(blocks);
	}

	@Override
	public void generate(ChunkProviderChatland chatland, int chunkX, int chunkZ) 
	{
		super.generate(chatland, chunkX, chunkZ);
		double[][] ids = new double[4][1];
		chatland.noiseGen0.generateNoiseOctaves(ids[0], chunkX - 1, chunkZ, 1, 1, 1.0D, 1.0D, 1.0D);
		chatland.noiseGen0.generateNoiseOctaves(ids[1], chunkX + 1, chunkZ, 1, 1, 1.0D, 1.0D, 1.0D);
		chatland.noiseGen0.generateNoiseOctaves(ids[2], chunkX, chunkZ - 1, 1, 1, 1.0D, 1.0D, 1.0D);
		chatland.noiseGen0.generateNoiseOctaves(ids[3], chunkX, chunkZ + 1, 1, 1, 1.0D, 1.0D, 1.0D);
		int x = ((int) ids[0][0]) <= -1 ? 0 : 1;
		int xMax = ((int) ids[1][0]) <= -1 ? 16 : 15;
		int zMin = ((int) ids[2][0]) <= -1 ? 0 : 1;
		int zMax = ((int) ids[3][0]) <= -1 ? 16 : 15;

		for(; x < xMax; x++)
			for(int z = zMin; z < zMax; z++)
				for(int y = 20; y < 34; y++)
					setBlock(x, y, z, Blocks.air);
	}
}
