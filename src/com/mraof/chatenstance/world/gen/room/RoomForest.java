package com.mraof.chatenstance.world.gen.room;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class RoomForest extends RoomEmptyBig 
{
	private static Random rand = new Random();
	public RoomForest(Block[] blocks, byte[] metadatas) {
		super(blocks, metadatas);
	}

	@Override
	public void generate(ChunkProviderChatland chatland, int chunkX, int chunkZ)
	{
		super.generate(chatland, chunkX, chunkZ);
		rand.setSeed((chunkX << 32 | chunkZ) & chatland.getSeed());
		int x = ids[0] == -2 ? 3 : 4;
		int xMax = ids[1] == -2 ? 13 : 12;
		int zMin = ids[2] == -2 ? 3 : 4;
		int zMax = ids[3] == -2 ? 13 : 12;

		for(; x < xMax; x++)
			for(int z = zMin; z < zMax; z++)
				if(rand.nextDouble() < .05)
					createTree(x, 20, z);
	}
	public void createTree(int x, int y, int z)
	{
		int height = rand.nextInt(6) + 4;
		int yBase = y;
		for(; y < yBase + height; y++)
			setBlock(x, y, z, Blocks.log);

		for(y = y - height / 2; y < yBase + height; y++)
			for(int xCurrent = x - 2; xCurrent <= x + 2; xCurrent++)
				for(int zCurrent = z - 2; zCurrent <= z + 2; zCurrent++)
					setBlock(xCurrent, y, zCurrent, Blocks.leaves);

		for(int xCurrent = x - 1; xCurrent <= x + 1; xCurrent++)
			for(int zCurrent = z - 1; zCurrent <= z + 1; zCurrent++)
				setBlock(xCurrent, yBase + height + 1, zCurrent, Blocks.leaves);

	}
}
