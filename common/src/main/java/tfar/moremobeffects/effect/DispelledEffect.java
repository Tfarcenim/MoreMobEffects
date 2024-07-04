package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DispelledEffect extends MobEffect {
    public DispelledEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1);
    }

    @Override
    public boolean isDurationEffectTick(int ticks, int $$1) {
        return ticks > 1;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        removeAllPositiveEffects(living);
    }

    protected void removeAllPositiveEffects(LivingEntity living) {
        Map<MobEffect, MobEffectInstance> activeEffectsMap = living.getActiveEffectsMap();
        Set<MobEffect> toRemove = new HashSet<>();
        for (MobEffect mobEffect : activeEffectsMap.keySet()) {
            if (mobEffect.isBeneficial()) {
                toRemove.add(mobEffect);
            }
        }
        for (MobEffect effect : toRemove) {
            living.removeEffect(effect);
        }
    }
}
