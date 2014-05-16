package com.mraof.chatenstance.chat;

import net.minecraftforge.event.ServerChatEvent;

public abstract class ChatHandler
{
	public abstract void handleMessage(ChatMessage chatMessage);
}

