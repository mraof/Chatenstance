package com.mraof.chatenstance.chat;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.world.WorldServer;

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
		int currentDimension = player.dimension;
		WorldServer currentWorldServer = player.mcServer.worldServerForDimension(currentDimension);
		player.dimension = dimension;
		WorldServer destinationWorldServer = player.mcServer.worldServerForDimension(dimension);
		S07PacketRespawn respawnPacket = new S07PacketRespawn(player.dimension, player.worldObj.difficultySetting, destionationW
	}
}
