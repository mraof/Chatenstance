package com.mraof.chatenstance.world.gen.room;

import net.minecraft.world.World;

public class Room
{
	public void generate(World world, int chunkX, int chunkZ)
	{
		int xOffset = chunkX * 16;
		int zOffset = chunkZ * 16;
		for(int i = 0; i < 2; i++)
			for(int y = 20; y < 24; y++)
			{
				world.setBlockToAir(xOffset + 7 + i, y, zOffset);
				world.setBlockToAir(xOffset + 7 + i, y, zOffset + 15);
				world.setBlockToAir(xOffset, y, zOffset + 7 + i);
				world.setBlockToAir(xOffset + 15, y, zOffset + 7 + i);
			}
	}
	public static Room getRoomFromId(int id)
	{
		switch(id)
		{
		default:
		case 0:
			return new RoomEmpty();
		case 1:
			return new RoomGrassy();
		case 2:
			return new Room();
		}
	}
}

