package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.phys.AABB;
import tfar.moremobeffects.ModConfig;
import tfar.moremobeffects.init.ModAttributes;
import tfar.moremobeffects.platform.Services;

import java.util.Set;
import java.util.UUID;


public class BerserkEffect extends MobEffect {
    public BerserkEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1);
    }

    private static final UUID uuid = UUID.fromString("366d5366-58a3-4964-8838-e861b549246a");


    public void applyEffectTick(LivingEntity entity, int level) {

        Set<Attribute> applicable = getApplicable();
        for (Attribute attribute : applicable) {
            AttributeInstance attributeinstance = entity.getAttribute(attribute);
            if (attributeinstance != null) {
                float levelScale = (float) ((1 + level) * getBoost(attribute));
                float f = (1 - entity.getHealth() / entity.getMaxHealth()) * levelScale;
                removeRageModifier(entity,attribute);
                attributeinstance.addTransientModifier(new AttributeModifier(uuid, "Berserk attack boost", f, AttributeModifier.Operation.ADDITION));
            }
        }
        if (!entity.level().isClientSide && entity instanceof Mob mob && mob.getTarget() == null && entity.tickCount % 10 == 0 && entity.getRandom().nextInt(2) == 0) {
            AABB aabb = mob.getBoundingBox().inflate(80);
            LivingEntity randomTarget = null;
            for (LivingEntity living : mob.level().getEntitiesOfClass(LivingEntity.class, aabb, EntitySelector.LIVING_ENTITY_STILL_ALIVE)) {
                if ((randomTarget == null || randomTarget.distanceTo(mob) > living.distanceTo(mob) && mob.getRandom().nextInt(2) == 0) && !mob.is(living)) {
                    if (!mob.isAlliedTo(living) && !living.isAlliedTo(mob) && mob.canAttack(living)) {
                        randomTarget = living;
                    }
                }
            }
            if (randomTarget != null && !randomTarget.is(mob)) {
                mob.setLastHurtByMob(randomTarget);
                mob.setTarget(randomTarget);
                for (int i = 0; i < 3 + mob.getRandom().nextInt(3); i++) {
                    ((ServerLevel) entity.level()).sendParticles(ParticleTypes.ANGRY_VILLAGER, mob.getRandomX(0.5F), mob.getEyeY() + mob.getRandom().nextFloat() * 0.2F, mob.getRandomZ(0.5F), 0, 0, 0, 0, 1.0D);
                }
            }
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }

    protected void removeRageModifier(LivingEntity living,Attribute attribute) {
        AttributeInstance attributeinstance = living.getAttribute(attribute);
        if (attributeinstance != null) {
            if (attributeinstance.getModifier(uuid) != null) {
                attributeinstance.removeModifier(uuid);
            }

        }
    }

    Set<Attribute> getApplicable() {
        return Set.of(Attributes.ATTACK_DAMAGE, Services.PLATFORM.getEnderSpellPower(), ModAttributes.PROJECTILE_ATTACK_DAMAGE);
    }

    double getBoost(Attribute attribute) {
        if (attribute ==  Attributes.ATTACK_DAMAGE) {
            return ModConfig.Server.berserk_attack_damage.get();
        } else if (attribute ==  Services.PLATFORM.getEnderSpellPower()) {
            return ModConfig.Server.berserk_ender_spell_power.get();
        } else if (attribute == ModAttributes.PROJECTILE_ATTACK_DAMAGE) {
            return ModConfig.Server.berserk_projectile_damage.get();
        }
        return 0;
    }

    public void addAttributeModifiers(LivingEntity entity, AttributeMap map, int i) {
        super.addAttributeModifiers(entity, map, i);
    }

    public void removeAttributeModifiers(LivingEntity entity, AttributeMap map, int i) {
        super.removeAttributeModifiers(entity, map, i);
        Set<Attribute> applicable = getApplicable();
        for (Attribute attribute : applicable) {
            removeRageModifier(entity,attribute);
        }
    }

}
