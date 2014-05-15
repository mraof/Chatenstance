package com.mraof.chatenstance.chat;

import net.minecraft.block.BlockCrops;
import net.minecraftforge.event.ServerChatEvent;

public class PlantTalk extends ChatHandler
 {

	@Override
	public void handleMessage(ServerChatEvent event) 
	{
		int lowercase = 0;
		for(int i = 0; i < event.message.length(); i++)
			lowercase += Character.isUpperCase(event.message.charAt(i)) ? -1 : 1;
		for(int x = event.player.posX - 4; x < event.player.posX + 4; x++)
		for(int y = event.player.posY - 4; y < event.player.posY + 4; y++)
		for(int z = event.player.posZ - 4; z < event.player.posZ + 4; z++)
		{
			Block block;
			if((block = event.player.worldObj.getBlock(x, y, z)) instanceof BlockCrops)
				((BlockCrops) block).harvestLevel
		}
		net.minecraft.item.ItemDye

	}
}

