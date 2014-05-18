package com.mraof.chatenstance.client.renderer.entity.sandworm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public abstract class EntitySandwormPart extends EntityCreature
{
	public EntitySandwormHead head;
	int place;
	public EntitySandwormPart(World world)
	{
		super(world);
		place = 0;
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

	public void updatePartPosition() {
		// TODO Auto-generated method stub
		
	}
}
