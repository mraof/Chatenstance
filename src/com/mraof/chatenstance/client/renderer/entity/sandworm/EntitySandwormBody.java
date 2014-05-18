package com.mraof.chatenstance.client.renderer.entity.sandworm;

import java.util.ArrayList;

import net.minecraft.world.World;

public class EntitySandwormBody extends EntitySandwormPart {
	public ArrayList<EntitySandwormPart> parts = new ArrayList<EntitySandwormPart>();

	public EntitySandwormBody(World world)
	{
		super(world);
	}

	public EntitySandwormBody(World world,
			EntitySandwormHead entitySandwormHead, int size) {
		// TODO Auto-generated constructor stub
	}
}	
