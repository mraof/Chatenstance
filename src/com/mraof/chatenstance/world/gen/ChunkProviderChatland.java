package com.mraof.chatenstance.world.gen;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
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
		Block[] chunkBlocks = new Block[65536];
		for(int x = 0; x < 16; x++)
			for(int z = 0; z < 16; z++)
				chunkBlocks[x * 4096 | z * 256 | 0] = Blocks.bedrock;
		Chunk chunk = new Chunk(this.world, chunkBlocks, chunkX, chunkZ);
		return chunk;
	}

	@Override
	public Chunk loadChunk(int chunkX, int chunkZ) {
		return this.provideChunk(chunkX, chunkZ);
	}

	@Override
	public void populate(IChunkProvider var1, int var2, int var3) {

	}

	@Override
	public boolean saveChunks(boolean var1, IProgressUpdate var2) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "Chatland";
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3,
			int var4) {
		return null;
	}

	@Override
	public ChunkPosition func_147416_a(World var1, String var2, int var3,
			int var4, int var5) {
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(int var1, int var2) {
	}

	@Override
	public void saveExtraData() {
	}
}
