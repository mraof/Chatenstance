package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class RoomHallway extends Room {
	public RoomHallway(Block[] blocks, byte[] metadatas) {
		super(blocks, metadatas);
	}

	@Override
	public void generate(ChunkProviderChatland chatland, int chunkX, int chunkZ)
	{
		super.generate(chatland, chunkX, chunkZ);
		int x1 = ids[0] == 2 ? 6 : 0;
		int x2 = ids[1] == 2 ? 10 : 15;
		int z1 = ids[2] == 2 ? 6 : 0;
		int z2 = ids[3] == 2 ? 10 : 15;

		for(int x = x1; x < x2; x++)
			for(int z = 6; z < 10; z++)
				for(int y = 20; y < 26; y++)
					setBlock(x, y, z, Blocks.air);
		for(int x = 6; x < 10; x++)
			for(int z = z1; z < z2; z++)
				for(int y = 20; y < 26; y++)
					setBlock(x, y, z, Blocks.air);
	}
}
