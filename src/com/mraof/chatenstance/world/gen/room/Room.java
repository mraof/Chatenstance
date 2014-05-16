package com.mraof.chatenstance.world.gen.room;

import net.minecraft.world.World;

public class Room
{
	public void generate(World world, int chunkX, int chunkY)
	{
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

