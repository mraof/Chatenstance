package com.mraof.chatenstance.world.gen.room;

import java.util.Random;

import net.minecraft.block.Block;

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
		int x = ids[0] == -2 ? 2 : 3;
		int xMax = ids[1] == -2 ? 14 : 13;
		int zMin = ids[2] == -2 ? 2 : 3;
		int zMax = ids[3] == -2 ? 14 : 13;

		for(; x < xMax; x++)
			for(int z = zMin; z < zMax; z++)
				if(rand.nextDouble() < .05)
					createTree(x, 20, z);
	}
	public void createTree(int x, int y, int z)
	{
	}
}
