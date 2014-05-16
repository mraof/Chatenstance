package com.mraof.chatenstance.chat;

import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ServerChatEvent;

public class MagicWords extends ChatHandler
{
	public static HashMap<String, Integer> effectWords = new HashMap<String, Integer>();

	static
	{
		effectWords.put("vroom", 1);
	}

	@Override
	public void handleMessage(ServerChatEvent event) 
	{
		String[] words = event.message.split(" ");
		HashSet<String> matches = new HashSet<String>();
		for(String word : words)
			if(effectWords.containsKey(word.toLowerCase()))
				matches.add(word.toLowerCase());
		for(String match : matches)
		event.player.addPotionEffect(new PotionEffect(effectWords.get(match), 180, 0, false));
	}
}
