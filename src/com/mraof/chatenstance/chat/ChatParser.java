package com.mraof.chatenstance.chat;

import java.util.ArrayList;

import net.minecraftforge.event.ServerChatEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ChatParser
{
	ArrayList<ChatHandler> handlers = new ArrayList<ChatHandler>();
	public ChatParser()
	{
		addHandler(new LightningStrike());
		addHandler(new MobHate());
	}
	@SubscribeEvent
	public void onChatMessage(ServerChatEvent event)
	{
		System.out.println("ServerChatEvent recieved");
		for(ChatHandler handler : handlers)
			handler.handleMessage(event);
	}
	public void addHandler(ChatHandler handler)
	{
		handlers.add(handler);
	}
}
