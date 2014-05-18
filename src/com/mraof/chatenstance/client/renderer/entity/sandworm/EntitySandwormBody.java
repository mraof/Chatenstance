package com.mraof.chatenstance.client.renderer.entity.sandworm;

import java.util.ArrayList;

import net.minecraft.world.World;

public class EntitySandwormBody extends EntitySandwormPart {
	public ArrayList<EntitySandwormPart> parts = new ArrayList<EntitySandwormPart>();

	public EntitySandwormBody(World world)
	{
		super(world);
	}
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		if(head.parts.size() > place + 1)
			head.parts.get(place + 1).updatePartPosition();
	}
}	
