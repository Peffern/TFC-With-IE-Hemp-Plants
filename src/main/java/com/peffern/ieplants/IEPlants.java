package com.peffern.ieplants;


import javax.swing.JOptionPane;

import com.peffern.crops.CropsRegistry;
import com.peffern.crops.ICrop;
import blusunrize.immersiveengineering.ImmersiveEngineering;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameData;
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
		Item mat = (Item)GameData.getItemRegistry().getObject("ImmersiveEngineering:material");
		JOptionPane.showMessageDialog(null, mat);
		//we use our own icons since the IE ones are too small (16x16), need 32x32
		String[] iconNames = new String[6];
		for(int i = 0; i < iconNames.length-1; i++)
		{
			iconNames[i] = ImmersiveEngineering.MODID + ":" + "hemp_" + i + "B";
		}
		iconNames[iconNames.length-1] = iconNames[iconNames.length-2];
		//TFC Crops crop generation - use custom crop implementation to handle special rendering
		ICrop p = new HempCrop("hemp", 1, 28, iconNames, 10, 5, 1.0f, new ItemStack(mat,4,3), "seedsHemp", com.bioxx.tfc.Reference.MOD_ID + ":" + "food/unused/img141", "Seeds IE_Hemp");
		//register
		hempSeeds = CropsRegistry.addCrop(p);
	}
}
