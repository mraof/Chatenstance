package com.mraof.chatenstance.client.renderer.entity.sandworm;

import net.minecraft.world.World;

public class EntitySandwormBody extends EntitySandwormPart {
	public EntitySandwormBody(World world)
	{
		super(world);
	}

	public EntitySandwormBody(World world, EntitySandwormHead entitySandwormHead, int place)
	{
		this(world);
		this.head = entitySandwormHead;
		this.place = place;
	}
}	
