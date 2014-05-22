package com.mraof.chatenstance.entity.sandworm;

import io.netty.buffer.ByteBuf;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySandwormBody extends EntityLiving implements IMob, IEntityAdditionalSpawnData
{
	public EntitySandwormHead head;
	private int headId = -1;
	int place = -1;

	public EntitySandwormBody(World world)
	{
		super(world);
		this.setSize(.5F, .5F);
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}


	public EntitySandwormBody(EntitySandwormHead entitySandwormHead, int place)
	{
		super(entitySandwormHead.worldObj);
		this.setSize(.5F, .5F);
		this.head = entitySandwormHead;
		this.place = place;
	}

	public void setMaxHealth(float health)
	{
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(health);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		float sizeSingle = tagCompound.getFloat("Width");
		if(this.head != null)
			sizeSingle = head.width;
		else
			sizeSingle = 2F;
		this.setSize(sizeSingle, sizeSingle);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		if(this.head != null)
		{
			super.writeEntityToNBT(tagCompound);
			tagCompound.setFloat("Width", width);
		}
		else
			this.setDead();
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();
		if((this.head == null || (this.head).isDead))
		{
			if(!worldObj.isRemote)
				this.setDead();
			else
			{
				this.setHeadById(headId);
				System.out.println("Setting head in onUpdate()");
			}
		}
	}

	@Override
	protected void updateFallState(double par1, boolean par3)
	{
		if(head != null)
		{
			int index = head.parts.indexOf(this);
			if(index > 0)
			{
				EntityLiving part = head.parts.get(index - 1);
				if(0.75 * (part.posY - this.posY) >= this.posY)
					par3 = true;
			}
		}
		super.updateFallState(par1, par3);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}
	public void setWidth(float width)
	{
		setSize(width, width);
		this.experienceValue = (int)(width * 5);
	}

	@Override
	public float getShadowSize()
	{
		return this.width / 2;
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
		buffer.writeFloat(this.width);
		buffer.writeInt(this.place);
		if(this.head != null)
			buffer.writeInt(head.getEntityId());
		else
			buffer.writeInt(-1);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) 
	{
		setWidth(additionalData.readFloat());
		this.place = additionalData.readInt();
		int id = additionalData.readInt();
		this.headId = id;
		setHeadById(id);
	}

	public void setHeadById(int id)
	{
		Entity head = this.worldObj.getEntityByID(id);
		if(head != null)
		{
			System.out.printf("Successfully set head with id %d for part with id of %d and place of %d\n", id, this.getEntityId(), this.place);
			this.head = (EntitySandwormHead) head;
			if(this.head.parts.size() == 1)
			{
				this.head.parts.add(this);
				System.out.println("Head had no parts, adding at end");
			}
			else
			{
				for(int i = 1; i < this.head.parts.size(); i++)
				{
					EntityLiving part = this.head.parts.get(i);
					if(((EntitySandwormBody)part).place == this.place - 1)
					{
						this.head.parts.add(i + 1, this);
						break;
					}
					else if(((EntitySandwormBody)part).place > this.place)
					{
						this.head.parts.add(i, this);
						break;
					}
				}
			}

		}
		else
			System.out.printf("Recieved null entity with id %d for head\n", id);

	}
	@Override
	public void collideWithEntity(Entity entity)
	{
		if(head == null || !head.parts.contains(entity))
			super.collideWithEntity(entity);
	}

	public void attackEntityFromHead(DamageSource source, float damage)
	{
		super.attackEntityFrom(source, damage);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		if(source != DamageSource.fall && source != DamageSource.inWall)
		{
			if(head != null)
			{
				damage = damage / 3 * 2;
				head.attackEntityFrom(source, damage);
			}
			return super.attackEntityFrom(source, damage);
		}
		else
			return false;
	}

	@Override
	public void setDead()
	{
		super.setDead();
		if(head != null)
		{
			this.place = head.parts.indexOf(this);
			if(this.place < 0)
				this.place = 1;
			head.parts.remove(this);
			head.size--;
			if(head.parts.size() > this.place)
				((EntitySandwormBody)head.parts.get(this.place)).correctPlace();
		}
	}

	public void correctPlace()
	{
		this.place = head.parts.indexOf(this);
		System.out.println(head.parts.indexOf(head));
		if(head.parts.size() > this.place + 1)
			((EntitySandwormBody)head.parts.get(this.place + 1)).correctPlace();
	}
}
