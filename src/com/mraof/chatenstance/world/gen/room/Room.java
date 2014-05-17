package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class Room
{
	public Block[] chunkBlocks;
	public Room(Block[] blocks)
	{
		this.chunkBlocks = blocks;
	}
	public static Room getRoomFromId(int id, Block[] blocks)
	{
		switch(id)
		{
		default:
		case 0:
			return new RoomEmpty(blocks);
		case 1:
			return new RoomGrassy(blocks);
		case 2:
			return new Room(blocks);
		case 3:
			return new RoomPool(blocks);
		case 4:
			return new RoomGlowstone(blocks);
		case 5:
			return new RoomObelisk(blocks);
		case -1:
			return new RoomEmptyBig(blocks);
		}
	}
	public void setBlock(int x, int y, int z, Block block)
	{
		chunkBlocks[x * 4096 | z * 256 | y] = block;
	}
	public void generate(ChunkProviderChatland chunkProviderChatland, int chunkX, int chunkZ) 
	{
		for(int i = 0; i < 2; i++)
			for(int y = 20; y < 24; y++)
			{
				setBlock(7 + i, y, 0, Blocks.air);
				setBlock(7 + i, y, 15, Blocks.air);
				setBlock(0, y, 7 + i, Blocks.air);
				setBlock(15, y, 7 + i, Blocks.air);
			}

	}
}

