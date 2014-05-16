package com.mraof.chatenstance.chat;

import java.util.ArrayList;

import net.minecraftforge.event.ServerChatEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ChatParser
{
	ArrayList<ChatHandler> handlers = new ArrayList<ChatHandler>();
	@SubscribeEvent
	public void onChatMessage(ServerChatEvent event)
	{
		System.out.println("ServerChatEvent recieved");
		ChatMessage chatMessage = new ChatMessage(event);
		for(ChatHandler handler : handlers)
			handler.handleMessage(chatMessage);
	}
	public void onChatMessage(String message, double x, double y, double z, World world)
	{
		ChatMessage chatMessage = new ChatMessage(message, x, y, z, world);
		for(ChatHandler handler : handlers)
			handler.handleMessage(chatMessage);
	}
	public void addHandler(ChatHandler handler)
	{
		handlers.add(handler);
	}
}
