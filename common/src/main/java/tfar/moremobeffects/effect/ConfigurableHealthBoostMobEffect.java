package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class ConfigurableHealthBoostMobEffect extends ConfigurableMobEffect {
    public ConfigurableHealthBoostMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public void removeAttributeModifiers(LivingEntity $$0, AttributeMap $$1, int $$2) {
        super.removeAttributeModifiers($$0, $$1, $$2);
        if ($$0.getHealth() > $$0.getMaxHealth()) {
            $$0.setHealth($$0.getMaxHealth());
        }
    }
}
