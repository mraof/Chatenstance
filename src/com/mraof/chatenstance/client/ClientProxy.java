package com.mraof.chatenstance.client;

import com.mraof.chatenstance.client.renderer.tileentity.RenderChatBox;
import com.mraof.chatenstance.tileentity.TileEntityChatBox;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy
{
	@SideOnly(Side.CLIENT)
	public static void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChatBox.class, new RenderChatBox());
	}
}
