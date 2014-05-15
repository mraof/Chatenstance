package com.mraof.chatenstance;

import net.minecraftforge.common.MinecraftForge;

import com.mraof.chatenstance.chat.ChatParser;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "Chatenstance", name = "Chatenstance", version = "@VERSION@")
public class Chatenstance
{
	@SubscribeEvent
	public void load(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new ChatParser());
	}
}
