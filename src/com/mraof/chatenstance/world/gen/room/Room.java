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
		default:
		case -1:
			return new RoomEmptyBig(blocks);
		}
	}
	public void setBlock(int x, int y, int z, Block block)
	{
		chunkBlocks[x * 4096 | z * 256 | y] = block;
	}
	public void generate(ChunkProviderChatland chatland, int chunkX, int chunkZ) 
	{
		double[][] ids = new double[4][1];
		chatland.noiseGen0.generateNoiseOctaves(ids[0], chunkX - 1, chunkZ, 1, 1, 1.0D, 1.0D, 1.0D);
		chatland.noiseGen0.generateNoiseOctaves(ids[1], chunkX + 1, chunkZ, 1, 1, 1.0D, 1.0D, 1.0D);
		chatland.noiseGen0.generateNoiseOctaves(ids[2], chunkX, chunkZ - 1, 1, 1, 1.0D, 1.0D, 1.0D);
		chatland.noiseGen0.generateNoiseOctaves(ids[3], chunkX, chunkZ + 1, 1, 1, 1.0D, 1.0D, 1.0D);
		for(int i = 0; i < 2; i++)
			for(int y = 20; y < 24; y++)
			{
				if(((int) ids[0][0]) != 2)
					setBlock(0, y, 7 + i, Blocks.air);
				if(((int) ids[1][0]) != 2)
					setBlock(15, y, 7 + i, Blocks.air);
				if(((int) ids[2][0]) != 2)
					setBlock(7 + i, y, 0, Blocks.air);
				if(((int) ids[3][0]) != 2)
					setBlock(7 + i, y, 15, Blocks.air);
			}

	}
}

