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
		super.generate();
		double[][] ids = new double[9][1];
		for(int i = 0; i < 9; i++)
			chatland.noiseGen0.generateNoiseOctaves(ids[i], chunkX + i / 3, chunkZ + i % 3, 1, 1, 1.0D, 1.0D, 1.0D);
		int x = ((int) ids[2][0]) == -1 ? 0 : 1;
		int zMin = ((int) ids[4][0]) == -1 ? 0 : 1;
		int zMax = ((int) ids[6][0]) == -1 ? 16 : 15;
		int xMax = ((int) ids[8][0]) == -1 ? 16 : 15;

		for(; x < xMax; x++)
			for(int z = zMin; z < zMax; z++)
				for(int y = 20; y < 34; y++)
					setBlock(x, y, z, Blocks.air);
	}
}
