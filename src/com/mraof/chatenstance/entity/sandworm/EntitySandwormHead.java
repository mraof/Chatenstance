package com.mraof.chatenstance.entity.sandworm;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.mraof.chatenstance.Chatenstance;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySandwormHead extends EntityCreature implements IMob, IEntityAdditionalSpawnData
{
	public ArrayList<EntityLiving> parts = new ArrayList<EntityLiving>();
	public EntityAIAttackOnCollide entityAIAttackOnCollide = new EntityAIAttackOnCollide(this, 0.8F, false);
	int size;
	Random rand = new Random();

	public EntitySandwormHead(World world)
	{
		super(world);
		float sizeSingle = rand.nextFloat() * 4 + 1;
		parts.add(this);
		size = rand.nextInt(Chatenstance.maxSandwormLength) + 7;
		this.stepHeight = 1.0F;
		
		this.setWidth(sizeSingle);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEnderman.class, 0, true));
		this.tasks.addTask(5, new EntityAIWander(this, 0.6F));
				
		if (world != null && !world.isRemote)
		{
			this.setCombatTask();
		};
	}


	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(26F);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(80);
	}
	
	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		this.setLength(size);
		return super.onSpawnWithEgg(data);
	}

	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		this.updatePartPositions();
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 6);
	}

	protected void setCombatTask()
	{
		if(entityAIAttackOnCollide == null)
			entityAIAttackOnCollide = new EntityAIAttackOnCollide(this, 0.8F, false);
		this.tasks.removeTask(this.entityAIAttackOnCollide);
		this.tasks.addTask(4, entityAIAttackOnCollide);
	}

	@Override 
	public Entity[] getParts()
	{
		return parts.toArray(new Entity[parts.size()]);
	}

	@Override
	public void collideWithEntity(Entity entity)
	{
		if(!parts.get(1).equals(entity))
			super.collideWithEntity(entity);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		for(int i = 1; i < parts.size(); i++)
			parts.get(i).attackEntityFrom(source, damage);
		return super.attackEntityFrom(source, damage);
	}

	@Override
	public void setPositionAndRotation(double x, double y, double z, float yaw, float pitch)
	{
		super.setPositionAndRotation(x, y, z, yaw, pitch);
		this.updatePartPositions();
	}

	public void updatePartPositions()
	{
		EntityLiving previousPart = this;
		for(int i = 1; i < parts.size(); i++)
		{
			EntityLiving part = parts.get(i);
			double diffX = part.posX - previousPart.posX;
			double diffY = part.posY - previousPart.posY;
			double diffZ = part.posZ - previousPart.posZ;
			double ratio = (part.width * .75) / Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
			
			double destX = previousPart.posX + diffX * ratio;
			double destY = previousPart.posY + diffY * ratio; 
			double destZ = previousPart.posZ + diffZ * ratio;
			
			if(diffY < this.width && this.worldObj.getBlock((int) destX, (int) destY, (int) destZ).getMaterial().isSolid())
				destY = Math.floor(destY + 1);

			part.setPositionAndRotation(destX, destY, destZ, (float) Math.atan2(diffX, diffZ), (float) Math.asin(diffY / Math.sqrt(diffX * diffX + diffZ * diffZ)));
			previousPart = part;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.setWidth(tagCompound.getFloat("Width"));
		this.setLength(tagCompound.getInteger("Length"));
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setFloat("Width", this.width);
		tagCompound.setInteger("Length", this.size);
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && super.getCanSpawnHere() && rand.nextInt(16) == 0;
	}
	
	public void setWidth(float width)
	{
		this.setSize(width, width);
		this.stepHeight = width / 2;
		for(int i = 1; i < parts.size(); i++)
			((EntitySandwormBody)parts.get(i)).setWidth(width);
	}

	public void setLength(int size)
	{
		this.size = size;
		for(int i = 1; i < parts.size(); i++)
			parts.get(i).setDead();
		for(int i = 1; i < size; i++)
		{
			EntitySandwormBody body = new EntitySandwormBody(this, i);
			body.setPosition(this.posX, this.posY, this.posZ);
			worldObj.spawnEntityInWorld(body);
			parts.add(body);
		}
		this.setWidth(this.width);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(this.width * this.size);
		this.setHealth(this.getMaxHealth());
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
		buffer.writeFloat(this.width);
		buffer.writeInt(this.size);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) 
	{
		setWidth(additionalData.readFloat());
		this.setLength(additionalData.readInt());
	}
	@Override
	public void onDeathUpdate()
	{
		super.onDeathUpdate();
		if(deathTime == 1)
			for(int i = 1; i < parts.size(); i++)
				parts.get(i).setHealth(0);
	}
}
