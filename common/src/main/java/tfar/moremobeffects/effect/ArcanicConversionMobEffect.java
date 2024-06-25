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
    private final Variant variant;

    public ArcanicConversionMobEffect(MobEffectCategory $$0, int $$1, Variant variant) {
        super($$0, $$1);
        this.variant = variant;
    }

    public enum Variant {
        physical,summoner;
    }

    static final UUID uuid = UUID.fromString("7953ce22-069d-4d14-964c-88b2bef556b6");

    @Override
    public boolean isDurationEffectTick(int $$0, int $$1) {
        return true;
    }



    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        switch (variant) {
            case physical -> {
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

            case summoner -> {
                double spellboost = Services.PLATFORM.getConfig().will_of_the_summoner() * (amplifier + 1);

                double spell_power = living.getAttributeValue(Services.PLATFORM.getSpellPower());
                double ender_spell_power = living.getAttributeValue(Services.PLATFORM.getEnderSpellPower());

                double total_spell_boost = spellboost * Math.max(spell_power + ender_spell_power - 1,-1);

                AttributeInstance attributeInstance = living.getAttribute(Services.PLATFORM.getSummonDamage());
                if (attributeInstance != null) {
                    AttributeModifier currentModifier = attributeInstance.getModifier(uuid);
                    if (currentModifier != null) {
                        if (currentModifier.getAmount() != total_spell_boost) {
                            attributeInstance.removeModifier(uuid);
                            attributeInstance.addPermanentModifier(new AttributeModifier(uuid,"will_of_the_summoner",total_spell_boost, AttributeModifier.Operation.MULTIPLY_TOTAL));
                        }
                    } else {
                        attributeInstance.addPermanentModifier(new AttributeModifier(uuid,"will_of_the_summoner",total_spell_boost, AttributeModifier.Operation.MULTIPLY_TOTAL));
                    }
                }
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
