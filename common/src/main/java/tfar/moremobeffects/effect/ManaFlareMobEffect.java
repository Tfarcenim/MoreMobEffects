package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import tfar.moremobeffects.platform.Services;

public class ManaFlareMobEffect extends MobEffect {
    public ManaFlareMobEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1);
    }

    @Override
    public boolean isDurationEffectTick(int ticks, int amplifier) {
        return ticks % 20 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        AttributeInstance spellPower = living.getAttribute(Services.PLATFORM.getSpellPower());
        if (spellPower != null) {
            double multi = spellPower.getBaseValue();
            living.hurt(living.damageSources().magic(), (float) ((amplifier + 1)*multi * Services.PLATFORM.getConfig().mana_flare()));
        }
    }
}
