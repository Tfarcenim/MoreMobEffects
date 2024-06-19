package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import tfar.moremobeffects.init.ModAttributes;
import tfar.moremobeffects.platform.Services;

import java.util.UUID;

public class ArcanicConversionMobEffect extends MobEffect {
    public ArcanicConversionMobEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1);
    }

    static final UUID uuid = UUID.fromString("7953ce22-069d-4d14-964c-88b2bef556b6");

    @Override
    public boolean isDurationEffectTick(int $$0, int $$1) {
        return true;
    }



    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        double spellboost = Services.PLATFORM.getConfig().arcanic_conversion() * (amplifier + 1);

        double attack_damage = living.getAttributeValue(Attributes.ATTACK_DAMAGE);
        double projectile_damage = living.getAttributeValue(ModAttributes.PROJECTILE_ATTACK_DAMAGE);
        double physical_damage = attack_damage - 1 + (projectile_damage - 1) * 100;

        double total_spell_boost = spellboost * physical_damage + 1;

        AttributeInstance attributeInstance = living.getAttribute(Services.PLATFORM.getEnderSpellPower());
        if (attributeInstance != null) {
            AttributeModifier currentModifier = attributeInstance.getModifier(uuid);
            if (currentModifier != null) {
                if (currentModifier.getAmount() != total_spell_boost) {
                    attributeInstance.removeModifier(uuid);
                    attributeInstance.addPermanentModifier(new AttributeModifier(uuid,"arcanic_conversion",total_spell_boost, AttributeModifier.Operation.MULTIPLY_TOTAL));
                }
            } else {
                attributeInstance.addPermanentModifier(new AttributeModifier(uuid,"arcanic_conversion",total_spell_boost, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity living, AttributeMap map, int amplifier) {
        super.removeAttributeModifiers(living, map, amplifier);
        AttributeInstance attributeInstance = map.getInstance(Services.PLATFORM.getSpellPower());
        if (attributeInstance != null) {
            attributeInstance.removeModifier(uuid);
        }
    }
}
