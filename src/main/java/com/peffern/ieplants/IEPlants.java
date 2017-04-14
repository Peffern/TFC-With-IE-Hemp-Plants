package com.peffern.ieplants;


import javax.swing.JOptionPane;

import com.bioxx.tfc.TerraFirmaCraft;
import com.peffern.crops.CropsRegistry;
import com.peffern.crops.ICrop;
import blusunrize.immersiveengineering.ImmersiveEngineering;
import blusunrize.immersiveengineering.api.energy.DieselHandler;
import blusunrize.immersiveengineering.common.IEContent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;




/**
 * Mod to make IE Hemp a TFC crop
 * @author peffern
 *
 */
@Mod(modid = IEPlants.MODID, name = IEPlants.MODNAME, version = IEPlants.VERSION, dependencies = "required-after:terrafirmacraft;required-before:ImmersiveEngineering")
public class IEPlants 
{
	/** We need our own hemp seed item since tfccrops will generate one */
	static Item hempSeeds;
	
	/** Mod ID String */
	public static final String MODID = "ieplants";
	
	/** Mod Name */
	public static final String MODNAME = "IE Plants";
	
	/** Mod Version */
	public static final String VERSION = "1.4";
	
	static boolean initComplete = false;
	
	/**
	 * Do all the main mod setup
	 */
	@EventHandler
	public void init(FMLInitializationEvent e)
	{		
		final Item mat = (Item)GameData.getItemRegistry().getObject("ImmersiveEngineering:material");
		//we use our own icons since the IE ones are too small (16x16), need 32x32
		String[] iconNames = new String[6];
		for(int i = 0; i < iconNames.length-1; i++)
		{
			iconNames[i] = ImmersiveEngineering.MODID + ":" + "hemp_" + i + "B";
		}
		iconNames[iconNames.length-1] = iconNames[iconNames.length-2];
		//TFC Crops crop generation - use custom crop implementation to handle special rendering
		ICrop p = new HempCrop("hemp", 1, 28, iconNames, 10, 5, 1.0f, null, "seedsHemp", com.bioxx.tfc.Reference.MOD_ID + ":" + "food/unused/img141", "Seeds IE_Hemp")
		{
			@Override
			public ItemStack getOutput1()
			{
				//yield 4 to 6 hemp fibers
				int stack = 4;
				if(IEPlants.isInitComplete()) //prevent server from calling the rand during setup before the world exists
					stack += TerraFirmaCraft.proxy.getCurrentWorld().rand.nextInt(3);
				return new ItemStack(mat,stack,3);
			}
			
			@Override
			public ItemStack getOutput2()
			{
				//yield 1 to 3 seeds as bonus drops
				int stack = 1;
				if(IEPlants.isInitComplete()) //prevent server from calling the rand during setup before the world exists
					stack += TerraFirmaCraft.proxy.getCurrentWorld().rand.nextInt(3);
				return new ItemStack(hempSeeds, stack);
			}
			
		};
		
		//register
		hempSeeds = CropsRegistry.addCrop(p);
		Item ie_hemp_seeds = IEContent.itemSeeds;
		DieselHandler.SqueezerRecipe oldRecipe = DieselHandler.findSqueezerRecipe(new ItemStack(ie_hemp_seeds));
		DieselHandler.addSqueezerRecipe(hempSeeds, oldRecipe.time, oldRecipe.fluid, oldRecipe.output);
	
		initComplete = true;
	}
	
	public static boolean isInitComplete()
	{
		return initComplete;
	}
}
