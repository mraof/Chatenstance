package com.mraof.chatenstance.client.renderer.entity.sandworm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public abstract class EntitySandwormPart extends EntityCreature
{
	public EntitySandwormHead head;
	public EntitySandwormPart(World world)
	{
		super(world);
	}

	@Override
	public boolean isAIEnabled()
	{
		return false;
	}
	protected void collideWithEntity(Entity par1Entity)
	{
		if(!head.parts.contains(par1Entity))
			par1Entity.applyEntityCollision(this);
	}
}
