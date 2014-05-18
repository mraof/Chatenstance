package com.mraof.chatenstance.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.mraof.chatenstance.client.model.ModelSandwormBody;

public class RenderSandwormBody extends RenderLiving
{
	ResourceLocation bodyTexture = new ResourceLocation("chatenstance", "textures/mobs/SandwormBody.png");
	public RenderSandwormBody()
	{
		super((ModelBase) new ModelSandwormBody(), 2.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) 
	{
		return bodyTexture;
	}
}
