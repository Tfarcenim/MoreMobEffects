package tfar.moremobeffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class DamageMultiplierEffect extends MobEffect {
    protected DamageMultiplierEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int $$0, int $$1) {
        return false;
    }
}
