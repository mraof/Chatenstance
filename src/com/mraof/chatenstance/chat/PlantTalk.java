package com.mraof.chatenstance.chat;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;

public class PlantTalk extends ChatHandler
{

	public Random rand = new Random();
	@Override
	public void handleMessage(ChatMessage event) 
	{
		int lowercase = 0;
		for(int i = 0; i < event.message.length(); i++)
			lowercase += Character.isUpperCase(event.message.charAt(i)) ? -1 : 1;
		int y = (int) event.y;
		for(int x = (int) event.x - 4; x < event.x + 4; x++)
			for(int z = (int) event.z - 4; z < event.z + 4; z++)
			{
				Block block;
				if((block = event.world.getBlock(x, y, z)) instanceof IGrowable && lowercase > rand.nextInt(event.message.length() * 16))
					((IGrowable) block).func_149853_b(event.world, rand, x, y, z);
			}

	}
}

