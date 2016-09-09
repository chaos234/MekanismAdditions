package mekanism.additions.client.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mekanism.api.gas.GasRegistry;
import mekanism.client.render.MekanismRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by Marcel on 09.09.2016.
 */
public class AdditionsRender extends MekanismRenderer {

    private static String[] simpleSides = new String[] {"Bottom", "Top", "Front", "Back", "Left", "Right"};

    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new AdditionsRender());
    }

    @SubscribeEvent
    public void onStitch(TextureStitchEvent.Pre event) {
        GasRegistry.getGas("enrichedwater").setIcon(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedWater"));
        GasRegistry.getGas("enrichedwatersnd").setIcon(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedWaterSnd"));
        GasRegistry.getGas("enrichedwaterrd").setIcon(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedWaterRd"));

        GasRegistry.getGas("dihydrogensulfid").setIcon(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedDihydrogenSulfidGas"));
        
        GasRegistry.getGas("enricheddihydrogensulfid").setIcon(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedDihydrogenSulfidGas"));
        GasRegistry.getGas("enricheddihydrogensulfidsnd").setIcon(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedDihydrogenSulfidSnd"));
        GasRegistry.getGas("enricheddihydrogensulfidrd").setIcon(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedDihydrogenSulfidRd"));

        //secound way for heavy water
        FluidRegistry.getFluid("enricheddihydrogensulfid").setIcons(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedDihydrogenSulfidGas"));
        FluidRegistry.getFluid("enricheddihydrogensulfidsnd").setIcons(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedDihydrogenSulfidSnd"));
        FluidRegistry.getFluid("enricheddihydrogensulfidrd").setIcons(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedDihydrogenSulfidRd"));

        FluidRegistry.getFluid("enrichedwater").setIcons(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedWater"));
        FluidRegistry.getFluid("enrichedwatersnd").setIcons(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedWaterSnd"));
        FluidRegistry.getFluid("enrichedwaterrd").setIcons(event.map.registerIcon("mekanismadditions:liquid/LiquidEnrichedWaterRd"));
         //end-secound way for heavy water

    }

    public static void loadDynamicTextures(IIconRegister register, String name, IIcon[] textures, DefIcon... defaults) {
        for(ForgeDirection side : ForgeDirection.VALID_DIRECTIONS)
        {
            String tex = "mekanismadditions:" + name + simpleSides[side.ordinal()];
            String texOn = tex + "On";

            if(blockIconExists(tex)) {
                textures[side.ordinal()] = register.registerIcon(tex);

                if(blockIconExists(texOn)) {
                    textures[side.ordinal()+6] = register.registerIcon(texOn);
                } else {
                    boolean found = false;

                    for(DefIcon def : defaults) {
                        if(def.icons.contains(side.ordinal()+6) && def.overridesInactive) {
                            textures[side.ordinal()+6] = def.defIcon;
                            found = true;
                        }
                    }

                    if(!found) {
                        textures[side.ordinal()+6] = register.registerIcon(tex);
                    }
                }
            }
            else {
                for(DefIcon def : defaults) {
                    if(def.icons.contains(side.ordinal())) {
                        textures[side.ordinal()] = def.defIcon;
                    }

                    if(def.icons.contains(side.ordinal()+6)) {
                        textures[side.ordinal()+6] = def.defIcon;
                    }
                }
            }
        }
    }
}
