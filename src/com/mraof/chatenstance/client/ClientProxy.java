package com.mraof.chatenstance.client;

import net.minecraft.client.model.ModelBiped;

import com.mraof.chatenstance.client.renderer.entity.RenderMummy;
import com.mraof.chatenstance.client.renderer.tileentity.RenderChatBox;
import com.mraof.chatenstance.entity.EntityMummy;
import com.mraof.chatenstance.entity.sandworm.EntitySandwormBody;
import com.mraof.chatenstance.tileentity.TileEntityChatBox;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy
{
	@SideOnly(Side.CLIENT)
	public static void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new RenderMummy(new ModelBiped()));
		//RenderingRegistry.registerEntityRenderingHandler(EntitySandwormBody.class, new RenderMummy(new ModelBiped()));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChatBox.class, new RenderChatBox());
	}
}
