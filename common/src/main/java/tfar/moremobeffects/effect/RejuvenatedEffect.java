package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class RejuvenatedEffect extends MobEffect {
    public RejuvenatedEffect(MobEffectCategory $$0, int amplifier) {
        super($$0, amplifier);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        living.heal(amplifier + 1);
    }

    @Override
    public boolean isDurationEffectTick(int tick, int amplifier) {
        return tick % 100 == 0;
    }
}
