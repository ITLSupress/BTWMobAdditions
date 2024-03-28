package com.itlesports.mobadditions.entity.mob.fox;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class ArcticFoxRenderer extends RenderArcticFox
{
    public ArcticFoxRenderer(ModelBase model, ModelBase modelOverlay, float fShadowSize )
    {
        super( model, modelOverlay, fShadowSize );
    }

    @Override
    protected int shouldRenderPass(EntityLiving entity, int iRenderPass, float par3 )
    {
        if ( renderGlowingEyes((ArcticFoxEntity)entity, iRenderPass) )
        {
            return 1;
        }

        return super.shouldRenderPass( entity, iRenderPass, par3 );
    }

    //------------- Class Specific Methods ------------//

    private boolean renderGlowingEyes(ArcticFoxEntity arcticfox, int iRenderPass)
    {
        if ( iRenderPass == 2 && arcticfox.areEyesGlowing() )
        {
            this.loadTexture("/btwmodtex/fcWolfNothingToWorryAbout.png");
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_LIGHTING);

            if ( arcticfox.isInvisible() )
            {
                GL11.glDepthMask( false );
            }
            else
            {
                GL11.glDepthMask( true );
            }

            char var5 = 61680;
            int var6 = var5 % 65536;
            int var7 = var5 / 65536;

            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var6 / 1.0F, (float)var7 / 1.0F);

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            return true;
        }

        return false;
    }

}

