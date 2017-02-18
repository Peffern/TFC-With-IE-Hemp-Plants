package com.peffern.ieplants;

import org.lwjgl.opengl.GL11;

import com.bioxx.tfc.TileEntities.TECrop;
import com.peffern.crops.BaseCrop;

import blusunrize.immersiveengineering.ImmersiveEngineering;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Custom ICrop instance to allow complicated IE rendering.
 * IE fully grown sprite won't render in TFC because it's in two pieces
 * have to draw the two icons separately.
 * @author peffern
 *
 */
public class HempCrop extends BaseCrop
{
	//special icon to draw on top of the plant when it's fully grown
	private IIcon hempTop;
	
	/**
	 * I only need this one constructor since it's the only one I'm using in IEPlants
	 */
	public HempCrop(String name, int type, int time, String[] iconNames, int gTemp, int aTemp, float nutrient, ItemStack crop, String seedOreName, String seedIcon, String seedUnlocalizedName)
	{
		super(name,type,time,iconNames,gTemp,aTemp,nutrient,crop,seedOreName,seedIcon,seedUnlocalizedName);
	}
	
	/**
	 * Register the custom icon as well
	 */
	@Override
	public void registerIcons(IIconRegister register)
	{
		super.registerIcons(register);
		hempTop = register.registerIcon(ImmersiveEngineering.MODID + ":" + "hemp_0T");
	}
	
	@Override
	public boolean render(Block block, double i, double j, double k, RenderBlocks renderblocks)
	{
		
		double width = 0.45;
		
		double height = 1.0;
		
		//renderer setup
		Tessellator tess = Tessellator.instance;
		GL11.glColor3f(1, 1, 1);
		int brightness = block.getMixedBrightnessForBlock(renderblocks.blockAccess, (int)i, (int)j, (int)k);
		tess.setBrightness(brightness);
		tess.setColorOpaque_F(1, 1, 1);

		//crop bottom icon
		IIcon icon = block.getIcon(renderblocks.blockAccess, (int)i, (int)j, (int)k, renderblocks.blockAccess.getBlockMetadata((int)i, (int)j, (int)k));
		if (renderblocks.hasOverrideBlockTexture())
			icon = renderblocks.overrideBlockTexture;
		
		//get the crop tile entity for growth check
		TECrop cropTE = (TECrop)renderblocks.blockAccess.getTileEntity((int)i, (int)j, (int)k);
		
		IIcon iconT = hempTop;
		
		if(icon != null && iconT != null)
		{
			if(((int)i & 1) > 0)
			{
				k+=0.0001;
			}
			if(((int)k & 1) > 0)
			{
				i+=0.0001;
			}

			//normal icon texture params
			double minU = icon.getMinU();
			double maxU = icon.getMaxU();
			double minV = icon.getMinV();
			double maxV = icon.getMaxV();
			double minX = i + 0.25D;
			double maxX = i + 0.75D;
			double minZ = k + 0.5D - width;
			double maxZ = k + 0.5D + width;
			double y = j;
			
			//top icon texture params
			double minUT = iconT.getMinU();
			double maxUT = iconT.getMaxU();
			double minVT = iconT.getMinV();
			double maxVT = iconT.getMaxV();
			double yT = y+1;

			//draw front and back
			tess.addVertexWithUV(minX, y+height, minZ, minU, minV);
			tess.addVertexWithUV(minX, y, minZ, minU, maxV);
			tess.addVertexWithUV(minX, y, maxZ, maxU, maxV);
			tess.addVertexWithUV(minX, y+height, maxZ, maxU, minV);
			tess.addVertexWithUV(minX, y+height, maxZ, minU, minV);
			tess.addVertexWithUV(minX, y, maxZ, minU, maxV);
			tess.addVertexWithUV(minX, y, minZ, maxU, maxV);
			tess.addVertexWithUV(minX, y+height, minZ, maxU, minV);
			tess.addVertexWithUV(maxX, y+height, maxZ, minU, minV);
			tess.addVertexWithUV(maxX, y, maxZ, minU, maxV);
			tess.addVertexWithUV(maxX, y, minZ, maxU, maxV);
			tess.addVertexWithUV(maxX, y+height, minZ, maxU, minV);
			tess.addVertexWithUV(maxX, y+height, minZ, minU, minV);
			tess.addVertexWithUV(maxX, y, minZ, minU, maxV);
			tess.addVertexWithUV(maxX, y, maxZ, maxU, maxV);
			tess.addVertexWithUV(maxX, y+height, maxZ, maxU, minV);
			
			if(cropTE.growth >= 5)
			{
				//draw custom top icon front and back
			tess.addVertexWithUV(minX, yT+height, minZ, minUT, minVT);
			tess.addVertexWithUV(minX, yT, minZ, minUT, maxVT);
			tess.addVertexWithUV(minX, yT, maxZ, maxUT, maxVT);
			tess.addVertexWithUV(minX, yT+height, maxZ, maxUT, minVT);
			tess.addVertexWithUV(minX, yT+height, maxZ, minUT, minVT);
			tess.addVertexWithUV(minX, yT, maxZ, minUT, maxVT);
			tess.addVertexWithUV(minX, yT, minZ, maxUT, maxVT);
			tess.addVertexWithUV(minX, yT+height, minZ, maxUT, minVT);
			tess.addVertexWithUV(maxX, yT+height, maxZ, minUT, minVT);
			tess.addVertexWithUV(maxX, yT, maxZ, minUT, maxVT);
			tess.addVertexWithUV(maxX, yT, minZ, maxUT, maxVT);
			tess.addVertexWithUV(maxX, yT+height, minZ, maxUT, minVT);
			tess.addVertexWithUV(maxX, yT+height, minZ, minUT, minVT);
			tess.addVertexWithUV(maxX, yT, minZ, minUT, maxVT);
			tess.addVertexWithUV(maxX, yT, maxZ, maxUT, maxVT);
			tess.addVertexWithUV(maxX, yT+height, maxZ, maxUT, minVT);
			}
			//rotate
			minX = i + 0.5D - width;
			maxX = i + 0.5D + width;
			minZ = k + 0.5D - 0.25D;
			maxZ = k + 0.5D + 0.25D;
			//draw sides
			tess.addVertexWithUV(minX, y+height, minZ, minU, minV);
			tess.addVertexWithUV(minX, y, minZ, minU, maxV);
			tess.addVertexWithUV(maxX, y, minZ, maxU, maxV);
			tess.addVertexWithUV(maxX, y+height, minZ, maxU, minV);
			tess.addVertexWithUV(maxX, y+height, minZ, minU, minV);
			tess.addVertexWithUV(maxX, y, minZ, minU, maxV);
			tess.addVertexWithUV(minX, y, minZ, maxU, maxV);
			tess.addVertexWithUV(minX, y+height, minZ, maxU, minV);
			tess.addVertexWithUV(maxX, y+height, maxZ, minU, minV);
			tess.addVertexWithUV(maxX, y, maxZ, minU, maxV);
			tess.addVertexWithUV(minX, y, maxZ, maxU, maxV);
			tess.addVertexWithUV(minX, y+height, maxZ, maxU, minV);
			tess.addVertexWithUV(minX, y+height, maxZ, minU, minV);
			tess.addVertexWithUV(minX, y, maxZ, minU, maxV);
			tess.addVertexWithUV(maxX, y, maxZ, maxU, maxV);
			tess.addVertexWithUV(maxX, y+height, maxZ, maxU, minV);
			if(cropTE.growth >= 5)
			{
				//draw custom sides
			tess.addVertexWithUV(minX, yT+height, minZ, minUT, minVT);
			tess.addVertexWithUV(minX, yT, minZ, minUT, maxVT);
			tess.addVertexWithUV(maxX, yT, minZ, maxUT, maxVT);
			tess.addVertexWithUV(maxX, yT+height, minZ, maxUT, minVT);
			tess.addVertexWithUV(maxX, yT+height, minZ, minUT, minVT);
			tess.addVertexWithUV(maxX, yT, minZ, minUT, maxVT);
			tess.addVertexWithUV(minX, yT, minZ, maxUT, maxVT);
			tess.addVertexWithUV(minX, yT+height, minZ, maxUT, minVT);
			tess.addVertexWithUV(maxX, yT+height, maxZ, minUT, minVT);
			tess.addVertexWithUV(maxX, yT, maxZ, minUT, maxVT);
			tess.addVertexWithUV(minX, yT, maxZ, maxUT, maxVT);
			tess.addVertexWithUV(minX, yT+height, maxZ, maxUT, minVT);
			tess.addVertexWithUV(minX, yT+height, maxZ, minUT, minVT);
			tess.addVertexWithUV(minX, yT, maxZ, minUT, maxVT);
			tess.addVertexWithUV(maxX, yT, maxZ, maxUT, maxVT);
			tess.addVertexWithUV(maxX, yT+height, maxZ, maxUT, minVT);
			}
		}
		
		
		return true;
	}
}
