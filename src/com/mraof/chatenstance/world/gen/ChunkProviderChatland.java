package com.mraof.chatenstance.world.gen;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderChatland implements IChunkProvider
{
	Random rand;
	World world;

	public ChunkProviderChatland(World world, long seed)
	{
		this.world = world;
		rand = new Random(seed);
	}

	@Override
	public boolean chunkExists(int chunkX, int chunkZ) {
		return true;
	}

	@Override
	public Chunk provideChunk(int chunkX, int chunkZ) {
		Chunk chunk = new Chunk();
		return null;
	}

	@Override
	public Chunk loadChunk(int chunkX, int chunkZ) {
		// TODO Auto-generated method stub
		return this.provideChunk(chunkX, chunkZ);
	}

	@Override
	public void populate(IChunkProvider var1, int var2, int var3) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean saveChunks(boolean var1, IProgressUpdate var2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unloadQueuedChunks() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSave() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String makeString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3,
			int var4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChunkPosition func_147416_a(World var1, String var2, int var3,
			int var4, int var5) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void recreateStructures(int var1, int var2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveExtraData() {
		// TODO Auto-generated method stub

	}
}
