package com.mraof.chatenstance.chat;

import net.minecraft.entity.player.EntityPlayerMP;

public class DimensionWords extends ChatHandler
{
	public int dimension = 0;
	public String teleportPhrase = "I want to go to that place";
	public String returnPhrase = "I want to go back";
	public DimensionWords()
	{
	}

	@Override
	public void handleMessage(ChatMessage chatMessage) 
	{
		if(chatMessage.message.equalsIgnoreCase(teleportPhrase))
		{
			teleport(chatMessage.player, dimension);
		}
		else if(chatMessage.message.equalsIgnoreCase(returnPhrase))
		{
			teleport(chatMessage.player, 0);
		}
	}

	public void teleport(EntityPlayerMP player, int dimension)
	{
		
	}
}
