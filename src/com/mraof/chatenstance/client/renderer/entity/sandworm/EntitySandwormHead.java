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
		for(int i = 0; i < 8; i++)
		{
			EntitySandwormBody body = new EntitySandwormBody(world, this, parts.size());
			world.spawnEntityInWorld(body);
			parts.add(body);
		}
	}
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		if(parts.size() > place + 1)
			parts.get(place + 1).updatePartPosition();
	}
}	
