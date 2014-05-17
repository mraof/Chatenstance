package com.mraof.chatenstance.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
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
	public void registerWorldChunkManager()
	{
		super.registerWorldChunkManager();
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.plains, 0.5F);
	}

	@Override
	protected void generateLightBrightnessTable()
	{
		for(int i = 0; i <= 15; i++)
			this.lightBrightnessTable[15 - i] = (float) i / 15.0F;
	}
	@Override
	public boolean isDaytime()
	{
		return false;
	}

	@Override
	public float calculateCelestialAngle(long par1, float par3)
	{
		return 18000;
	}

	
}

