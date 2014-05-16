package com.mraof.chatenstance.chat;

import java.util.Random;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraftforge.event.ServerChatEvent;

public class LightningStrike extends ChatHandler
{
	Random rand = new Random();

	@Override
	public void handleMessage(ServerChatEvent event) 
	{
		String[] words = event.message.split(" ");
		int matches = 0;
		for(String word : words)
		{
			if(word.equalsIgnoreCase("lightning") || word.equalsIgnoreCase("lightening") || word.equalsIgnoreCase("strike"))
				matches++;
		}
		for(int i = 0; i < words.length; i++)
		if(.1 * matches / words.length > rand.nextDouble())
		{
			double x = event.player.posX + rand.nextInt(17) - 8;
			double z = event.player.posZ + rand.nextInt(17) - 8;
			EntityLightningBolt bolt = new EntityLightningBolt(event.player.worldObj, x, event.player.worldObj.getPrecipitationHeight((int) x, (int) z), z);
			event.player.worldObj.addWeatherEffect(bolt);

		}

	}

}
