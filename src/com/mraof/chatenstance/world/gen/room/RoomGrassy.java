package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class RoomGrassy extends Room
{
	public RoomGrassy(Block[] blocks) {
		super(blocks);
	}

	@Override
	public void generate()
	{
		super.generate();
		for(int x = 1; x < 15; x++)
		{
			for(int z = 1; z < 15; z++)
			{
				setBlock(x, 20, z, Blocks.grass);
				for(int y = 21; y < 34; y++)
					setBlock(x, y, z, Blocks.air);
			}
		}
	}
}
