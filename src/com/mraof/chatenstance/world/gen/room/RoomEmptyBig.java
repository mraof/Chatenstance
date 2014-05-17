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
		double[] ids = new double[9];
		chatland.noiseGen0.generateNoiseOctaves(ids, chunkX - 1, chunkZ - 1, 3, 3, 1.0D, 1.0D, 1.0D);
		int x = ((int) ids[2]) == -1 ? 0 : 1;
		int z = ((int) ids[4]) == -1 ? 0 : 1;
		int zMax = ((int) ids[6]) == -1 ? 15 : 16;
		int xMax = ((int) ids[8]) == -1 ? 15 : 16;

		for(; x < xMax + 15; x++)
			for(; z < zMax; z++)
				for(int y = 20; y < 34; y++)
				{
					setBlock(x, y, z, Blocks.air);
					System.out.println(x + " " + y + " " + z + " | " + xMax + " " + zMax);
				}
	}
}
