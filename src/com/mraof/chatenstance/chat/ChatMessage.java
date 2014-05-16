package com.mraof.chatenstance.chat;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.ServerChatEvent;

public class ChatMessage
{
	public double x;
	public double y;
	public double z;
	public World world;
	public String message;
	public EntityPlayerMP player = null;

	public ChatMessage(ServerChatEvent event)
	{
		player = event.player;
		message = event.message;
		x = player.posX;
		y = player.posY;
		z = player.posZ;
		world = player.worldObj;
	}
	public ChatMessage(String message, double x, double y, double z, World world)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
