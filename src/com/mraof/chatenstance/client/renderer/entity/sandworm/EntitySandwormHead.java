package com.mraof.chatenstance.client.renderer.entity.sandworm;

import java.util.ArrayList;

import net.minecraft.world.World;

public class EntitySandwormHead extends EntitySandwormPart
{
	public ArrayList<EntitySandwormPart> parts = new ArrayList<EntitySandwormPart>();
	public EntitySandwormHead(World world)
	{
		super(world);
		this.head = this;
		parts.add(this);
	}
}	
