package com.peffern.ieplants;


import com.peffern.crops.BaseCrop;
import com.peffern.crops.CropsRegistry;
import com.peffern.crops.ICrop;
import com.peffern.crops.RenderCustomCrop;

import blusunrize.immersiveengineering.common.IEContent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;




/**
 * Mod to make IE Hemp a TFC crop
 * @author peffern
 *
 */
@Mod(modid = IEPlants.MODID, name = IEPlants.MODNAME, version = IEPlants.VERSION, dependencies = "after:terrafirmacraft;after:ImmersiveEngineering")
public class IEPlants 
{
	/** We need our own hemp seed item since tfccrops will generate one */
	static Item hempSeeds;
	
	/** Mod ID String */
	public static final String MODID = "ieplants";
	
	/** Mod Name */
	public static final String MODNAME = "IE Plants";
	
	/** Mod Version */
	public static final String VERSION = "1.1";
	
	/**
	 * Do all the main mod setup
	 */
	@EventHandler
	public void init(FMLInitializationEvent e)
	{		
		//we use our own icons since the IE ones are too small (16x16), need 32x32
		String[] iconNames = new String[6];
		for(int i = 1; i < iconNames.length+1; i++)
		{
			iconNames[i-1] = IEPlants.MODID + ":" + "Hemp (" + i + ")";
		}
		//TFC Crops crop generation
		ICrop p = new BaseCrop("hemp", 1, 28, iconNames, 10, 5, 1.0f, new ItemStack(IEContent.itemMaterial,4,3), "seedsHemp", com.bioxx.tfc.Reference.MOD_ID + ":" + "food/unused/img141", "Seeds IE_Hemp")
		{
			@Override
			public boolean render(Block block, int x, int y, int z, RenderBlocks renderblocks)
			{
				//use the crop impl renderer with some parameters I learned from TFC's code. I guess those are height and width or something.
				RenderCustomCrop.renderBlockCropsImpl(block,x,y,z,renderblocks,1.0,2.0);
				return true;
			}
		};
		//register
		hempSeeds = CropsRegistry.addCrop(p);
	}
}
