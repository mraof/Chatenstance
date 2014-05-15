package com.mraof.chatenstance.chat;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraftforge.event.ServerChatEvent;

public class PlantTalk extends ChatHandler
 {

	 public Random rand = new Random();
	@Override
	public void handleMessage(ServerChatEvent event) 
	{
		int lowercase = 0;
		for(int i = 0; i < event.message.length(); i++)
			lowercase += Character.isUpperCase(event.message.charAt(i)) ? -1 : 1;
		for(int x = (int) event.player.posX - 4; x < event.player.posX + 4; x++)
		for(int y = (int) event.player.posY - 4; y < event.player.posY + 4; y++)
		for(int z = (int) event.player.posZ - 4; z < event.player.posZ + 4; z++)
		{
			Block block;
			if((block = event.player.worldObj.getBlock(x, y, z)) instanceof IGrowable && lowercase > 0)
				((IGrowable) block).func_149853_b(event.player.worldObj, rand, x, y, z);
		}

	}
}

