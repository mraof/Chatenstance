package com.mraof.chatenstance.world.gen.room;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class RoomGrassy extends Room
{
	@Override
	public void generate(World world, int chunkX, int chunkZ)
	{
		super.generate(world, chunkX, chunkZ);
		for(int x = chunkX * 16 + 1; x < chunkX * 16 + 15; x++)
		{
			for(int z = chunkZ * 16 + 1; z < chunkZ * 16 + 15; z++)
			{
				world.setBlock(x, 20, z, Blocks.grass);
				for(int y = 21; y < 34; y++)
					world.setBlockToAir(x, y, z);
			}
		}
	}
}
