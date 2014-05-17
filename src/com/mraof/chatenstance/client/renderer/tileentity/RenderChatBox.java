package com.mraof.chatenstance.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
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
		RenderManager renderManager = RenderManager.instance;
		float scale = 0.0266666666;
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2, (float)var4 + 1.5F, (float)var6);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(scale, scale, scale);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendF
}

