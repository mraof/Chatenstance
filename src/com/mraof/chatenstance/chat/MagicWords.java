package com.mraof.chatenstance.chat;

import java.util.HashMap;

import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ServerChatEvent;

public class MagicWords extends ChatHandler
{
	public HashMap<String, Integer> effectWords = new HashMap<String, Integer>();

	@Override
	public void handleMessage(ServerChatEvent event) 
	{
		event.player.addPotionEffect(new PotionEffect(effectWords.get(event.message.toLowerCase()), 180, 0, false));
	}
}
