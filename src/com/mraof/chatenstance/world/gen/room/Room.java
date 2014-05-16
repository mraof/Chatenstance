package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;

public class Room
{
	public Block[] chunkBlocks;
	public Room(Block[] blocks)
	{
		this.chunkBlocks = blocks;
	}
	public void generate()
	{
		for(int i = 0; i < 2; i++)
			for(int y = 20; y < 24; y++)
			{
				setBlockToAir(7 + i, y, 0);
				setBlockToAir(7 + i, y, 15);
				setBlockToAir(0, y, 7 + i);
				setBlockToAir(15, y, 7 + i);
			}
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
		}
	}
	public setBlock(int x, int y, int z, Block block)
	{
		chunkBlocks[x * 4096 | z * 256 | y] = block;
	}
}

