package com.mraof.chatenstance.client.renderer.tileentity;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.mraof.chatenstance.tileentity.TileEntityChatBox;

public class RenderChatBox extends TileEntitySpecialRenderer
 {

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double var2, double var4, double var6, float var8) 
	{
		TileEntityChatBox chatBox = (TileEntityChatBox) tileEntity;
		if(chatBox.ticker <= 20)
			renderText(tileEntity, chatBox.message, var2, var4, var6);
	}
	public void renderText(TileEntity tileEntity, String text, double var2, double var4, double var6)
	{
		FontRenderer fontRenderer = this.func_147498_b();
		RenderManager renderManager = RenderManager.instance;
		float scale = 0.0266666666F;
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2, (float)var4 + 1.5F, (float)var6);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(-scale, -scale, -scale);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		tessellator.startDrawingQuads();
		int width = fontRenderer.getStringWidth(text) / 2;
		tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
		tessellator.addVertex((double)(-width - 1), -1.0D, 0.0D);
		tessellator.addVertex((double)(-width - 1), 8.0D, 0.0D);
		tessellator.addVertex((double)(width + 1), 8.0D, 0.0D);
		tessellator.addVertex((double)(width + 1), -1.0D, 0.0D);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		fontRenderer.drawString(text, -width, 0, 0x20FFFFFF);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		fontRenderer.drawString(text, -width, 0, -1);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

}

