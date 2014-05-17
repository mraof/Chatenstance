
package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class RoomEntrance extends Room {
	public RoomEntrance(Block[] blocks) {
		super(blocks);
	}

	@Override
	public void generate(ChunkProviderChatland chatland, int chunkX, int chunkZ) 
	{
		super.generate(chatland, chunkX, chunkZ);

		for(int x = 1; x < 15; x++)
			for(int z = 1; z < 15; z++)
				for(int y = 20; y < 34; y++)
					setBlock(x, y, z, Blocks.air);
		int y = 20;
		for(; chunkBlocks[4096 * 8 | 256 * 8 | y] != null; y++)
		{
			setBlock(8, y, 8, Blocks.ladder);
			setBlock(9, y, 8, Blocks.sandstone);
		}
	}
}
