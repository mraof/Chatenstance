package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class RoomEmptyBig extends Room
{
	int[] ids;
	public RoomEmptyBig(Block[] blocks, byte[] metadatas) {
		super(blocks, metadatas);
	}

	@Override
	public void generate(ChunkProviderChatland chatland, int chunkX, int chunkZ) 
	{
		super.generate(chatland, chunkX, chunkZ);
		double[][] idsDouble = new double[4][1];
		chatland.noiseGen0.generateNoiseOctaves(idsDouble[0], chunkX - 1, chunkZ, 1, 1, 1.0D, 1.0D, 1.0D);
		chatland.noiseGen0.generateNoiseOctaves(idsDouble[1], chunkX + 1, chunkZ, 1, 1, 1.0D, 1.0D, 1.0D);
		chatland.noiseGen0.generateNoiseOctaves(idsDouble[2], chunkX, chunkZ - 1, 1, 1, 1.0D, 1.0D, 1.0D);
		chatland.noiseGen0.generateNoiseOctaves(idsDouble[3], chunkX, chunkZ + 1, 1, 1, 1.0D, 1.0D, 1.0D);
		ids = new int[4];
		for(int i = 0; i < 4; i++)
			ids[i] = (int) idsDouble[i][0];

		int x = ids[0] <= -1 ? 0 : 1;
		int xMax = ids[1] <= -1 ? 16 : 15;
		int zMin = ids[2] <= -1 ? 0 : 1;
		int zMax = ids[3] <= -1 ? 16 : 15;

		for(; x < xMax; x++)
			for(int z = zMin; z < zMax; z++)
				for(int y = 20; y < 34; y++)
					setBlock(x, y, z, Blocks.air);
	}
}
