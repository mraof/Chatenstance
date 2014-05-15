package com.mraof.chatenstance.chat;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.ServerChatEvent;

public class MobHate extends ChatHandler
{
	Random rand = new Random();
	static HashMap<String, Class<? extends Entity>> entities = new HashMap<String, Class<? extends Entity>>();

	static
	{
		entities.put("spider", net.minecraft.entity.monster.EntitySpider.class);
	};
	@Override
	public void handleMessage(ServerChatEvent event)
	{
		boolean hate = false;
		String message = event.message;
		for(; message.indexOf(' ') != -1; message = message.substring)
		{
			if(message.toUpperCase().startsWith("HATE")
	}
}
