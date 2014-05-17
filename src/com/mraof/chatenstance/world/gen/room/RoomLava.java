package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class RoomLava extends RoomEmptyBig {
	public RoomLava(Block[] blocks, byte[] metadatas) {
		super(blocks, metadatas);
	}

	@Override
	public void generate(ChunkProviderChatland chatland, int chunkX, int chunkZ)
	{
		super.generate(chatland, chunkX, chunkZ);
		int x = ids[0] == -5 ? 0 : 4;
		int xMax = ids[1] == -5 ? 16 : 12;
		int zMin = ids[2] == -5 ? 0 : 4;
		int zMax = ids[3] == -5 ? 16 : 12;

		for(; x < xMax; x++)
			for(int z = zMin; z < zMax; z++)
				for(int y = 17; y < 20; y++)
					setBlock(x, y, z, Blocks.lava);
	}
}
