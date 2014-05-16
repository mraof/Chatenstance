package com.mraof.chatenstance.chat;

import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ServerChatEvent;

public class MagicWords extends ChatHandler
{
	public static HashMap<String, PotionEffect> effectWords = new HashMap<String, PotionEffect>();

	@Override
	public void handleMessage(ServerChatEvent event) 
	{
		String[] words = event.message.split(" ");
		HashSet<String> matches = new HashSet<String>();
		for(String word : words)
			if(effectWords.containsKey(word))
				matches.add(word);
	}
}
