
package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class RoomEntrance extends RoomEmptyBig {
	public RoomEntrance(Block[] blocks, byte[] metadatas) {
		super(blocks, metadatas);
	}

	@Override
	public void generate(ChunkProviderChatland chatland, int chunkX, int chunkZ) 
	{
		super.generate(chatland, chunkX, chunkZ);

		int y = 20;
		for(; chunkBlocks[4096 * 8 | 256 * 8 | y] != null; y++)
		{
			setBlockWithMetadata(8, y, 8, Blocks.ladder, (byte) 4);
			setBlock(9, y, 8, Blocks.sandstone);
		}
		y--;
		setBlock(7, y, 7, Blocks.glowstone);
		setBlock(8, y, 7, Blocks.glowstone);
		setBlock(9, y, 7, Blocks.glowstone);
		setBlock(7, y, 8, Blocks.glowstone);
		setBlock(9, y, 8, Blocks.glowstone);
		setBlock(7, y, 9, Blocks.glowstone);
		setBlock(8, y, 9, Blocks.glowstone);
		setBlock(9, y, 9, Blocks.glowstone);
	}
}
