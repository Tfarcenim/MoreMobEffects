package tfar.moremobeffects;

import net.minecraftforge.fml.common.Mod;

@Mod(MoreMobEffects.MOD_ID)
public class MoreMobEffectsForge {
    
    public MoreMobEffectsForge() {
    
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        MoreMobEffects.LOG.info("Hello Forge world!");
        MoreMobEffects.init();
        
    }
}