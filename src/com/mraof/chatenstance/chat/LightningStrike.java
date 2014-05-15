package com.mraof.chatenstance.chat;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraftforge.event.ServerChatEvent;

public class LightningStrike extends ChatHandler
 {

	@Override
	public void handleMessage(ServerChatEvent event) 
	{
		String[] words = event.message.split(" ");
		int matches = 0;
		for(String word : words)
		{
			if(word.equalsIgnoreCase("lightning") || word.equalsIgnoreCase("lightening"))
				matches++;
		}
		if(matches > 1)
		{
			event.player.worldObj.addWeatherEffect(new EntityLightningBolt(event.player.worldObj, event.player.posX, event.player.worldObj.getPrecipitationHeight((int) event.player.posX, (int) event.player.posZ), event.player.posZ));
			System.out.println("Lightning strike added");
		}
			
	}

}
