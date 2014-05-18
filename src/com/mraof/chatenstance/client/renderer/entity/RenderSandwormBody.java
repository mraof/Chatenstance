package com.mraof.chatenstance.client.renderer.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSandwormBody extends Render
{
	ResourceLocation bodyTexture = new ResourceLocation("chatenstance", "textures/mobs/SandwormBody.png");
	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return bodyTexture;
	}
}
