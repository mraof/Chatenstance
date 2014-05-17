package com.mraof.chatenstance.world.gen.room;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class RoomPool extends Room {
	public RoomPool(Block[] blocks) {
		super(blocks);
	}

	@Override
	public void generate()
	{
		super.generate();
		for(int x = 1; x < 15; x++)
			for(int z = 1; z < 15; z++)
				for(int y = 20; y < 34; y++)
					setBlock(x, y, z, Blocks.air);
		for(int x = 5; x < 10; x++)
			for(int z = 5; z < 10; z++)
				for(int y = 17; y < 20; y++)
					setBlock(x, y, z, Blocks.water);
	}
}
