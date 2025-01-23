package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import tfar.moremobeffects.ModConfig;
import tfar.moremobeffects.MoreMobEffects;
import tfar.moremobeffects.platform.Services;

import java.util.Set;
import java.util.function.Supplier;

public class FlightyAndMightyEffect extends TickingMobEffect{
    public FlightyAndMightyEffect(MobEffectCategory category, int color, Supplier<Set<Attribute>> attributes) {
        super(category, color, attributes);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        double cooldownReduction = living.getAttributeValue(Services.PLATFORM.getSpellCooldownReduction());
        double boost = cooldownReduction * (amplifier+1);
        AttributeInstance attackSpeedInstance = living.getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeedInstance != null) {
            MoreMobEffects.addModifierAvoidUpdates(new AttributeModifier(
                    getUuid(),"flighty and mighty",boost* ModConfig.Server.flighty_and_mighty_attack_speed.get(), AttributeModifier.Operation.MULTIPLY_TOTAL),
                    attackSpeedInstance);
        }
        MoreMobEffects.addModifierAvoidUpdates(new AttributeModifier(
                        getUuid(),"flighty and mighty",boost* ModConfig.Server.flighty_and_mighty_movement_speed.get(), AttributeModifier.Operation.MULTIPLY_TOTAL),
                living.getAttribute(Attributes.MOVEMENT_SPEED));
    }
}
//Your generic.attack_speed and generic.movement_speed is boosted by x% of your irons_spellbooks:cooldown_reduction
//Can I have a separate config for attack speed and movement speed (similar to Ender’s Game)?