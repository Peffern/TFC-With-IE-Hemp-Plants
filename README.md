TFC With IE Hemp Plants
================

For TFC 0.79.29 and IE 0.7.7

This is a test of the TFC Crop Index API mod that implements IE Industrial Hemp as a TFC Crop using a custom renderer. 


A note on TechNodeFirmaCraft:

If you are using this mod with TechNodeFirmaCraft, you will need to make the following MineTweaker edits in order to allow the hemp seeds to be processed in the Industrial Squeezer:

Look in scripts/ImmersiveEngineering.zs for the line which reads "mods.immersiveengineering.Squeezer.addRecipe(null, &lt;liquid:plantoil&gt; * 40, &lt;ImmersiveEngineering:seed&gt;, 80);" which should be on line 551, in the Squeezer section.

Add below it another line which reads "mods.immersiveengineering.Squeezer.addRecipe(null, &lt;liquid:plantoil&gt; * 40, &lt;ImmersiveEngineering:seed&gt;, 80);"

That will allow you to use the Industrial Squeezer.
