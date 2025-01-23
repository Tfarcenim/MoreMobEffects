package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import tfar.moremobeffects.ModConfig;
import tfar.moremobeffects.MoreMobEffects;
import tfar.moremobeffects.init.ModAttributes;
import tfar.moremobeffects.platform.Services;

import java.util.Set;
import java.util.function.Supplier;

public class BattleBornEffect extends TickingMobEffect{
    public BattleBornEffect(MobEffectCategory $$0, int $$1, Supplier<Set<Attribute>> attributes) {
        super($$0, $$1, attributes);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        double fraction = living.getHealth() / living.getMaxHealth();
        if (fraction >=.5) {

            AttributeInstance resistanceInstance = living.getAttribute(ModAttributes.RESISTANCE);
            resistanceInstance.removeModifier(getUuid());

            AttributeInstance attackDamageInstance = living.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attackDamageInstance != null) {
                MoreMobEffects.addModifierAvoidUpdates(new AttributeModifier(getUuid(),"battleborn", ModConfig.Server.battleborn_attack_damage.get() * (amplifier + 1), AttributeModifier.Operation.ADDITION),
                        attackDamageInstance);
            }

            AttributeInstance enderSpellInstance = living.getAttribute(Services.PLATFORM.getEnderSpellPower());
            MoreMobEffects.addModifierAvoidUpdates(new AttributeModifier(getUuid(),"battleborn", ModConfig.Server.battleborn_ender_spell_power.get() * (amplifier + 1),
                    AttributeModifier.Operation.ADDITION),enderSpellInstance);

        } else {
            AttributeInstance attackDamageInstance = living.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attackDamageInstance != null) {
                attackDamageInstance.removeModifier(getUuid());
            }
            AttributeInstance enderSpellInstance = living.getAttribute(Services.PLATFORM.getEnderSpellPower());
            enderSpellInstance.removeModifier(getUuid());

            AttributeInstance resistanceInstance = living.getAttribute(ModAttributes.RESISTANCE);
            MoreMobEffects.addModifierAvoidUpdates(new AttributeModifier(getUuid(),"battleborn", ModConfig.Server.battleborn_resistance.get() * (amplifier + 1),
                    AttributeModifier.Operation.ADDITION),resistanceInstance);
        }
    }
}
//Battleborn
//Gain x% generic.attack_damage and irons_spellbooks:ender_spell_damage when at OR above 50% of max HP.
//Instead, gain x% moremobeffects:resistance when below 50% of max HP.