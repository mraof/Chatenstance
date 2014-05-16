package com.mraof.chatenstance.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

import com.mraof.chatenstance.world.gen.ChunkProviderChatland;

public class WorldProviderChatland extends WorldProvider
{
	private ChunkProviderChatland provider;

	@Override
	public IChunkProvider createChunkGenerator()
	{
		if(provider == null)
			provider = new ChunkProviderChatland(this.worldObj, this.worldObj.getSeed());
		return provider;
	}

	@Override
	public String getDimensionName() {
		return "Chatland";
	}
}

