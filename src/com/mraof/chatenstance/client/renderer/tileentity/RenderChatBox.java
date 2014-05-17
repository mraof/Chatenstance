package com.mraof.chatenstance.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderChatBox extends TileEntitySpecialRenderer
 {

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double var2, double var4, double var6, float var8) 
	{

	}
	public void renderText(TileEntity tileEntity, String text, double var2, double var4, double var6, double var8)
	{
		FontRenderer = this.func_147498_b();
		float scale = 0.0266666666;
		GL11.glPushMatrix();
		GL11.glTran
}

