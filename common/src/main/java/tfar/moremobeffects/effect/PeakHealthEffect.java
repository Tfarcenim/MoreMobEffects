package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import tfar.moremobeffects.ModConfig;
import tfar.moremobeffects.platform.Services;

import java.util.UUID;

public class PeakHealthEffect extends MobEffect {


    public PeakHealthEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1);
    }

    static final UUID uuid = UUID.fromString("7953ce22-069d-4d14-964c-88b2bef556b6");

    @Override
    public boolean isDurationEffectTick(int $$0, int $$1) {
        return true;
    }


    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        double boost = ModConfig.Server.peak_health.get() * (amplifier + 1);

        double max_health = living.getAttributeValue(Attributes.MAX_HEALTH);

        double total_boost = Math.max(0,boost * (max_health - ModConfig.Server.peak_health_min.get()));

        AttributeInstance attributeInstance = living.getAttribute(Services.PLATFORM.getCriticalHitDamage());
        if (attributeInstance != null) {
            AttributeModifier currentModifier = attributeInstance.getModifier(uuid);
            if (currentModifier != null) {
                if (currentModifier.getAmount() != total_boost) {
                    attributeInstance.removeModifier(uuid);
                    attributeInstance.addPermanentModifier(new AttributeModifier(uuid, "peak_health", total_boost, AttributeModifier.Operation.MULTIPLY_TOTAL));
                }
            } else {
                attributeInstance.addPermanentModifier(new AttributeModifier(uuid, "peak_health", total_boost, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity living, AttributeMap map, int amplifier) {
        super.removeAttributeModifiers(living, map, amplifier);
        AttributeInstance attributeInstance = map.getInstance(Services.PLATFORM.getCriticalHitDamage());
        if (attributeInstance != null) {
            attributeInstance.removeModifier(uuid);
        }
    }
}
