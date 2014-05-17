package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class Room
{
	public Block[] chunkBlocks;
	public byte[] chunkMetadata;
	public Room(Block[] blocks, byte[] metadatas)
	{
		this.chunkBlocks = blocks;
		this.chunkMetadata = metadatas;
	}
	public static Room getRoomFromId(int id, Block[] blocks, byte[] metadatas)
	{
		switch(id)
		{
		case 0:
			return new RoomEmpty(blocks, metadatas);
		case 1:
			return new RoomGrassy(blocks, metadatas);
		case 2:
			return new Room(blocks, metadatas);
		case 3:
			return new RoomPool(blocks, metadatas);
		case 4:
			return new RoomGlowstone(blocks, metadatas);
		case 5:
			return new RoomObelisk(blocks, metadatas);
		case -1:
			return new RoomEntrance(blocks, metadatas);
		default:
		case -2:
			return new RoomEmptyBig(blocks, metadatas);
		}
	}
	public void setBlock(int x, int y, int z, Block block)
	{
		chunkBlocks[x * 4096 | z * 256 | y] = block;
	}
	public void setBlockWithMetadata(int x, int y, int z, Block block, byte metadata)
	{
		chunkBlocks[x * 4096 | z * 256 | y] = block;
		chunkMetadata[x * 4096 | z * 256 | y] = metadata;
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

