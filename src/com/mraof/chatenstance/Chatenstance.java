package com.mraof.chatenstance;

import net.minecraftforge.common.MinecraftForge;

import com.mraof.chatenstance.chat.ChatParser;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "Chatenstance", name = "Chatenstance", version = "@VERSION@")
public class Chatenstance
{
	ChatParser chatParser;
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		System.out.println("Chatenstance loaded");
		chatParser = new ChatParser();
		MinecraftForge.EVENT_BUS.register(chatParser);
	}
}
