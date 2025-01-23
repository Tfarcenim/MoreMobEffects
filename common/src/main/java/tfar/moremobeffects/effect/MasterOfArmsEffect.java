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

import java.util.Set;
import java.util.function.Supplier;

public class MasterOfArmsEffect extends TickingMobEffect {
    public MasterOfArmsEffect(MobEffectCategory category, int color, Supplier<Set<Attribute>> supplier) {
        super(category, color, supplier);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        AttributeInstance attackDamageInstance = living.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamageInstance != null) {
            double attackDamage = attackDamageInstance.getValue();
            AttributeInstance projectileAttackInstance = living.getAttribute(ModAttributes.PROJECTILE_ATTACK_DAMAGE);
            if (projectileAttackInstance != null) {
                double multiplier = ModConfig.Server.master_of_arms.get() * attackDamage * (amplifier + 1);
                MoreMobEffects.addModifierAvoidUpdates(new AttributeModifier(getUuid(),"master of arms",multiplier, AttributeModifier.Operation.ADDITION),projectileAttackInstance);
            }
        }
    }
}
//X% of your generic.attack damage is added to your moremobeffects:projectile_attack_ damage.
//My main need for this effect is that it must dynamically adjust.
// So if a person has berserk and gets their attack damage boosted, this effect should immediately convert a portion of that into projectile damage