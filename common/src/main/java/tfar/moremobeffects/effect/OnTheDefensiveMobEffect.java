package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class OnTheDefensiveMobEffect extends MobEffect {
    public OnTheDefensiveMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
}
//Your next basic/ranged attack will grant you absorption hearts based on x% of your max health and mana.
//Absorption gained = x(HP + MP)
//Should stack with other absorption sources such as The Warden effect, golden apples, fortify from iron’s spells, etc.
