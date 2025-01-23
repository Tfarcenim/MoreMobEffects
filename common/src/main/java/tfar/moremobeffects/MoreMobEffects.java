package tfar.moremobeffects;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.Team;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tfar.moremobeffects.init.ModAttributes;
import tfar.moremobeffects.init.ModMobEffects;
import tfar.moremobeffects.network.PacketHandler;
import tfar.moremobeffects.platform.Services;

import java.util.UUID;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class MoreMobEffects {

    public static final String MOD_ID = "moremobeffects";
    public static final String MOD_NAME = "MoreMobEffects";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        Services.PLATFORM.registerAll(ModMobEffects.class, BuiltInRegistries.MOB_EFFECT, MobEffect.class);
        Services.PLATFORM.registerAll(ModAttributes.class, BuiltInRegistries.ATTRIBUTE, Attribute.class);
        PacketHandler.registerPackets();
    }

    public static void commonSetup() {
    }

    static final UUID blazing_aspect_id = UUID.fromString("93966058-6d05-4ad4-91c2-d97311b80fd7");
    static final UUID wolf_aspect_id = UUID.fromString("8d07a724-7e9a-4055-9fdf-b33fe05da5e1");
    static final UUID withering_aspect_id = UUID.fromString("2f0edfe8-a36f-4064-b2f1-a484704000f5");
    static final UUID sorcerous_transference_id = UUID.fromString("d0d86f0b-360e-46e2-8089-819a119eaccd");

    public static float livingHurt(LivingEntity target, DamageSource source, float baseDamage) {

        if (target instanceof Player player) {
            MobEffectInstance martyr = target.getEffect(ModMobEffects.MARTYR);

            if (martyr == null) {
                Team team = player.getTeam();
                if (team != null) {
                    double fraction = 0;
                    for (String teamPlayer : team.getPlayers()) {
                        MinecraftServer server = target.getServer();
                        ServerPlayer player1 = server.getPlayerList().getPlayerByName(teamPlayer);
                        if (player1 != null) {
                            MobEffectInstance martyr1 = player1.getEffect(ModMobEffects.MARTYR);
                            if (martyr1 != null) {
                                fraction += ModConfig.Server.martyr.get();
                                double transferred = baseDamage * ModConfig.Server.martyr.get();
                                player1.hurt(source, (float) transferred);
                                if (fraction >= 1) break;
                            }
                        }
                    }
                    baseDamage *= 1 - fraction;
                }
            }
        }


        MobEffectInstance exposed = target.getEffect(ModMobEffects.EXPOSED);
        if (exposed != null) {
            baseDamage *= 1 + ModConfig.Server.exposed.get() * (exposed.getAmplifier() + 1);
        }

        MobEffectInstance vulnerable = target.getEffect(ModMobEffects.VULNERABLE);
        if (vulnerable != null) {
            baseDamage *= 1 + ModConfig.Server.vulnerable.get() * (vulnerable.getAmplifier() + 1);
        }

        Entity attacker = source.getEntity();

        if (attacker instanceof LivingEntity livingAttacker) {

            if (ModMobEffects.ATTRIBUTESLIB) {
                AttributeInstance attributeInstance = livingAttacker.getAttribute(Services.PLATFORM.getCriticalHitDamage());

                if (attributeInstance != null) {
                    attributeInstance.removeModifier(blazing_aspect_id);
                    MobEffectInstance blazing_aspect = livingAttacker.getEffect(ModMobEffects.COMMANDING_ASPECT);
                    if (blazing_aspect != null && (target.hasEffect(MobEffects.WEAKNESS) || target.hasEffect(Services.PLATFORM.getBlight()))) {
                        attributeInstance.addTransientModifier(new AttributeModifier(blazing_aspect_id, "Blazing Aspect Bonus", ModConfig.Server.blazing_aspect.get() * (blazing_aspect.getAmplifier() + 1), AttributeModifier.Operation.ADDITION));
                    }
                }

                AttributeInstance attributeInstanceCritChance = livingAttacker.getAttribute(Services.PLATFORM.getCriticalHitRate());
                if (attributeInstanceCritChance != null) {
                    attributeInstanceCritChance.removeModifier(wolf_aspect_id);
                    if (target.hasEffect(Services.PLATFORM.getBleeding())) {
                        MobEffectInstance wolf_aspect = livingAttacker.getEffect(ModMobEffects.WOLF_ASPECT);
                        if (wolf_aspect != null) {
                            attributeInstanceCritChance.addTransientModifier(new AttributeModifier(wolf_aspect_id, "Wolf Aspect Bonus", ModConfig.Server.wolf_aspect.get() * (wolf_aspect.getAmplifier() + 1), AttributeModifier.Operation.ADDITION));
                        }
                    }
                }

                AttributeInstance attributeInstanceArmorShred = livingAttacker.getAttribute(Services.PLATFORM.getArmorPiercing());
                if (attributeInstanceArmorShred != null) {
                    attributeInstanceArmorShred.removeModifier(withering_aspect_id);
                    MobEffectInstance withering_aspect = livingAttacker.getEffect(ModMobEffects.WITHERING_ASPECT);
                    if (withering_aspect != null) {
                        attributeInstanceArmorShred.addTransientModifier(new AttributeModifier(withering_aspect_id, "Withering Aspect Bonus", ModConfig.Server.withering_aspect.get() * (withering_aspect.getAmplifier() + 1), AttributeModifier.Operation.ADDITION));
                    }
                }
            }

            if (source.is(DamageTypeTags.IS_PROJECTILE)) {
                double projectile_damage = livingAttacker.getAttributeValue(ModAttributes.PROJECTILE_ATTACK_DAMAGE);
                baseDamage *= projectile_damage;
            }

            MobEffectInstance arcanic_overload = livingAttacker.getEffect(ModMobEffects.ARCANIC_OVERLOAD);
            if (arcanic_overload != null && !source.is(DamageTypes.MAGIC)) {
                target.hurt(target.damageSources().magic(), (float)
                        (ModConfig.Server.arcanic_overload.get() * (arcanic_overload.getAmplifier() + 1) * baseDamage));
            }

            MobEffectInstance domineering = livingAttacker.getEffect(ModMobEffects.DOMINEERING);

            if (domineering != null) {
                long count = target.getActiveEffects()
                        .stream().filter(mobEffectInstance -> mobEffectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL).count();
                baseDamage *= 1 + (ModConfig.Server.domineering.get() * domineering.getAmplifier() + 1) * count;
            }


            if (!source.is(DamageTypes.MAGIC)) {

                MobEffectInstance sorcerous_transference = livingAttacker.getEffect(ModMobEffects.SORCEROUS_TRANSFERENCE);
                if (sorcerous_transference != null) {

                    AttributeInstance spell_power = livingAttacker.getAttribute(Services.PLATFORM.getSpellPower());
                    if (spell_power != null) {
                        double spell_power_boost = (spell_power.getValue()-1 +livingAttacker.getAttributeValue(Services.PLATFORM.getEnderSpellPower()) -1) * ModConfig.Server.sorcerous_transference.get() * (sorcerous_transference.getAmplifier()+1) +1;

                        AttributeInstance targetAttribute = target.getAttribute(Services.PLATFORM.getSpellPower());
                        if (targetAttribute != null) {
                            targetAttribute.removeModifier(sorcerous_transference_id);
                            targetAttribute.addTransientModifier(new AttributeModifier(sorcerous_transference_id,"Sorcerous Transference",spell_power_boost, AttributeModifier.Operation.ADDITION));
                        }
                    }
                }

                MobEffectInstance battle_mage = livingAttacker.getEffect(ModMobEffects.BATTLE_MAGE);
                if (battle_mage != null) {

                    double spell_power = livingAttacker.getAttributeValue(Services.PLATFORM.getSpellPower());
                    double ender_spell_power = livingAttacker.getAttributeValue(Services.PLATFORM.getEnderSpellPower());

                    double extraDamage = ModConfig.Server.battle_mage.get() * (battle_mage.getAmplifier() + 1)
                            * (1 + ender_spell_power + spell_power) * baseDamage;
                    baseDamage += extraDamage;
                }
            }
        }

        MobEffectInstance marked = target.getEffect(ModMobEffects.MARKED);
        if (marked != null && !source.is(DamageTypes.MAGIC)) {
            baseDamage *= 1 + (ModConfig.Server.marked.get() * marked.getAmplifier() + 1);
            target.removeEffect(ModMobEffects.MARKED);
        }


        return baseDamage;
    }

    public static boolean livingDeath(LivingEntity living, DamageSource source) {
        if (living.hasEffect(ModMobEffects.REVIVE)) {
            living.setHealth((float) (living.getMaxHealth() * ModConfig.Server.revive.get()));
            living.removeEffect(ModMobEffects.REVIVE);
            return true;
        }
        return false;
    }

    public static float modifyDamageAfterMagicAbsorb(LivingEntity living, DamageSource source, float amount) {

        if (living.getAttribute(ModAttributes.RESISTANCE) != null && !source.is(DamageTypeTags.BYPASSES_RESISTANCE)) {
            amount *= 2 - living.getAttributeValue(ModAttributes.RESISTANCE);
        }

        if (living.hasEffect(ModMobEffects.WARDEN) && !source.is(DamageTypeTags.BYPASSES_RESISTANCE)) {
            amount *=.85f;
        }
        return amount;
    }

    public static int getLootingLevel(LivingEntity entity, @Nullable DamageSource damageSource, int lootingLevel) {
        if (damageSource == null) return lootingLevel;
        Entity attacker = damageSource.getEntity();
        if (attacker instanceof LivingEntity livingAttacker) {
            MobEffectInstance mobEffect = livingAttacker.getEffect(ModMobEffects.LOOTING);
            if (mobEffect != null) {
                lootingLevel += mobEffect.getAmplifier() + 1;
            }
        }

        return lootingLevel;
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static UUID make(ResourceLocation location) {
        return new UUID(location.getNamespace().hashCode(),location.getPath().hashCode());
    }

    public static void safeAddModifier(AttributeModifier modifier,AttributeInstance instance) {
        if (instance.hasModifier(modifier)) {
            instance.removeModifier(modifier);
        }
        instance.addPermanentModifier(modifier);
    }

    public static void addModifierAvoidUpdates(AttributeModifier modifier,AttributeInstance instance) {
        AttributeModifier oldModifier = instance.getModifier(modifier.getId());
        if (oldModifier != null) {
            if (oldModifier.getAmount() != modifier.getAmount()) {
                instance.removeModifier(modifier);
            } else {
                return;
            }
        }
        instance.addPermanentModifier(modifier);
    }


    public static void livingDamage(LivingEntity target, DamageSource source, float amount) {
        Entity attacker = source.getEntity();
        if (attacker instanceof LivingEntity && amount > 0) {
            MobEffectInstance retribution = target.getEffect(ModMobEffects.RETRIBUTION);

            if (retribution != null) {

                AttributeInstance spellPower = target.getAttributes().hasAttribute(Services.PLATFORM.getSpellPower())
                        ? target.getAttribute(Services.PLATFORM.getSpellPower()) : null;

                AttributeInstance enderSpellPower = target.getAttributes().hasAttribute(Services.PLATFORM.getEnderSpellPower())
                        ? target.getAttribute(Services.PLATFORM.getEnderSpellPower()) : null;

                if (spellPower != null && enderSpellPower != null) {
                    attacker.hurt(target.damageSources().thorns(target), (float)
                            (ModConfig.Server.retribution.get() * (retribution.getAmplifier() + 1) * (1+ spellPower.getValue() + enderSpellPower.getValue())));
                    target.removeEffect(ModMobEffects.RETRIBUTION);

                    target.level().playSound(null,target.blockPosition(), SoundEvents.FIREWORK_ROCKET_BLAST, SoundSource.PLAYERS,1,1);
                }
            }
        }
    }
}