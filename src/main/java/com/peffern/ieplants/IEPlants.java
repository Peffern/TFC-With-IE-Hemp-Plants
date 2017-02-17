package com.peffern.ieplants;


import java.lang.reflect.Field;

import javax.swing.JOptionPane;

import com.bioxx.tfc.Blocks.BlockCrop;
import com.bioxx.tfc.Food.CropIndexJute;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.peffern.crops.BaseCrop;
import com.peffern.crops.CropsRegistry;
import com.peffern.crops.ICrop;
import com.peffern.crops.RenderCustomCrop;

import blusunrize.immersiveengineering.ImmersiveEngineering;
import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.IERecipes;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import blusunrize.immersiveengineering.common.blocks.stone.*;




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
	public static final String VERSION = "1.0";
	
	/**
	 * Do all the main mod setup
	 */
	@EventHandler
	public void init(FMLInitializationEvent e)
	{		
		String[] iconNames = new String[6];
		for(int i = 1; i < iconNames.length+1; i++)
		{
			iconNames[i-1] = IEPlants.MODID + ":" + "Hemp (" + i + ")";
		}
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
		hempSeeds = CropsRegistry.addCrop(p);
	
		//I just realized that IE 0.7.7 doesn't use hempFiber in the oreDict
		/*IERecipes.addShapelessOredictRecipe(new ItemStack(Items.string), "fiberHemp","fiberHemp","fiberHemp");
		IERecipes.addOredictRecipe(new ItemStack(IEContent.itemMaterial,1,4), "HHH","HSH","HHH", 'H',"fiberHemp", 'S',"stickWood");
		IERecipes.addOredictRecipe(new ItemStack(IEContent.itemWireCoil,4,3), " I ","ISI"," I ", 'I',"fiberHemp", 'S',"stickWood");
		IERecipes.addOredictRecipe(new ItemStack(IEContent.blockStoneDecoration, 6, 0), "CCC", "HHH", "CCC", 'C', "lumpClay", 'H', "fiberHemp");
		*/
	}
}
