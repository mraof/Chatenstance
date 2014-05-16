package com.mraof.chatenstance.chat;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
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
		System.out.println("Teleporting player from " +  player.dimension + " to " + dimension);
		int currentDimension = player.dimension;
		WorldServer currentWorldServer = player.mcServer.worldServerForDimension(currentDimension);
		player.dimension = dimension;
		WorldServer destinationWorldServer = player.mcServer.worldServerForDimension(dimension);
		S07PacketRespawn respawnPacket = new S07PacketRespawn(player.dimension, player.worldObj.difficultySetting, destinationWorldServer.getWorldInfo().getTerrainType(), player.theItemInWorldManager.getGameType());
		player.playerNetServerHandler.sendPacket(respawnPacket);

		currentWorldServer.removePlayerEntityDangerously(player);
		player.isDead = false;

		destinationWorldServer.spawnEntityInWorld(player);
		double destinationY = destinationWorldServer.getPrecipitationHeight((int) player.posX, (int) player.posZ);
		player.setLocationAndAngles(player.posX, destinationY, player.posZ, player.rotationYaw, player.rotationPitch);
		destinationWorldServer.updateEntityWithOptionalForce(player, false);
		player.setWorld(destinationWorldServer);

		WorldServer worldserver2 = player.getServerForPlayer();
		currentWorldServer.getPlayerManager().removePlayer(player);
		worldserver2.getPlayerManager().addPlayer(player);
		worldserver2.theChunkProviderServer.loadChunk((int) player.posX >> 4, (int) player.posZ >> 4);

		player.playerNetServerHandler.setPlayerLocation(player.posX, destinationY, player.posZ, player.rotationYaw, player.rotationPitch);
		player.theItemInWorldManager.setWorld(destinationWorldServer);
		player.mcServer.getConfigurationManager().updateTimeAndWeatherForPlayer(player, destinationWorldServer);
		player.mcServer.getConfigurationManager().syncPlayerInventory(player);
		Iterator<?> iterator = player.getActivePotionEffects().iterator();

		while(iterator.hasNext())
		{
			PotionEffect potionEffect = (PotionEffect) iterator.next();
			player.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(player.getEntityId(), potionEffect));
		}
	}
}
