package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class RoomEmptyBig extends Room
{
	public RoomEmptyBig(Block[] blocks, byte[] metadatas) {
		super(blocks, metadatas);
	}

	@Override
	public void generate(ChunkProviderChatland chatland, int chunkX, int chunkZ) 
	{
		super.generate(chatland, chunkX, chunkZ);

		int x = ids[0] <= -1 ? 0 : 1;
		int xMax = ids[1] <= -1 ? 16 : 15;
		int zMin = ids[2] <= -1 ? 0 : 1;
		int zMax = ids[3] <= -1 ? 16 : 15;

		for(; x < xMax; x++)
			for(int z = zMin; z < zMax; z++)
				for(int y = 20; y < 54; y++)
					setBlock(x, y, z, Blocks.air);
	}
}
