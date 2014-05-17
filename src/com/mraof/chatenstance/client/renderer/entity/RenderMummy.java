package com.mraof.chatenstance.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderMummy extends RenderBiped 
{
	ResourceLocation mummyTexture = new ResourceLocation("chatenstance", "textures/mobs/Mummy.png");

        public RenderMummy(ModelBiped par1ModelBiped, float par2) 
        {
                super(par1ModelBiped, par2);
        }

        @Override
        protected ResourceLocation getEntityTexture(EntityLiving par1EntityLiving) 
        {
                return mummyTexture;
        }

}
