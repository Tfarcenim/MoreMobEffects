package tfar.moremobeffects.effect;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import tfar.moremobeffects.ModConfig;
import tfar.moremobeffects.init.ModDamageTypes;
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
            double multi = spellPower.getValue();
            Registry<DamageType> damageTypes = living.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);
            DamageSource source = new DamageSource(damageTypes.getHolderOrThrow(ModDamageTypes.MANA_FLARE));
            living.hurt(source, (float) ((amplifier + 1) * multi * ModConfig.Server.mana_flare.get()));
        }
    }
}
