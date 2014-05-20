package com.mraof.chatenstance.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelSandwormHead extends ModelBase {
	private ModelRenderer segment;

	public ModelSandwormHead()
	{
		float offsetY = -16 + 24;
		this.textureWidth = 128;
		this.textureHeight = 64;
		segment = new ModelRenderer(this, 0, 0);
		segment.addBox(-16F, -16F, -16F, 32, 32, 32);
		segment.setRotationPoint(0F, offsetY, 0F);
	}

	@Override
	public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		GL11.glPushMatrix();
		float entityScaling = entity.width / 2F;
		GL11.glTranslatef(0.0F, -(entity.width - 2) / 4F * 3F, 0.0F);
		GL11.glScalef(entityScaling, entityScaling, entityScaling);
		this.segment.render(par7);
		GL11.glPopMatrix();
	}
}
