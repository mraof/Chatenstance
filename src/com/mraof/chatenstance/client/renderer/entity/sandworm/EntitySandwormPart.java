package com.mraof.chatenstance.client.renderer.entity.sandworm;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntitySandwormPart extends EntityCreature
{
	public EntitySandwormPart(World world)
	{
		super(world);
	}

	@Override
	public boolean isAIEnabled()
	{
		return false;
	}
}
