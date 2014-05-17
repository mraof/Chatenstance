
package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class RoomGlowstone extends Room 
{
	public RoomGlowstone(Block[] blocks, byte[] metadatas) 
	{
		super(blocks, metadatas);
	}

	@Override
	public void generate(ChunkProviderChatland chatland, int chunkX, int chunkZ)
	{
		super.generate(chatland, chunkX, chunkZ);
		for(int x = 1; x < 15; x++)
			for(int z = 1; z < 15; z++)
			{
				setBlock(x, 19, z, Blocks.glowstone);
				for(int y = 20; y < 34; y++)
					setBlock(x, y, z, Blocks.air);
			}
	}
}
