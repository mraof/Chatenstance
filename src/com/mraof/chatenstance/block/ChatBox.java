package com.mraof.chatenstance.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mraof.chatenstance.tileentity.TileEntityChatBox;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChatBox extends Block
{
	public ChatBox()
	{
		super(Material.rock);
		setHardness(1.0F);
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		iconRegister.registerIcon("chatenstance:chatbox");
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) 
	{
		return new TileEntityChatBox();
	}
}

