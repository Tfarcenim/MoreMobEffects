package tfar.moremobeffects;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tfar.moremobeffects.init.ModAttributes;
import tfar.moremobeffects.init.ModMobEffects;
import tfar.moremobeffects.platform.Services;

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
    }

    public static void commonSetup() {
    }

    public static float livingAttack(LivingEntity living, DamageSource source,float baseDamage) {

        if (living instanceof Player player) {
            MobEffectInstance martyr = living.getEffect(ModMobEffects.MARTYR);

            if (martyr == null) {
                Team team = player.getTeam();
                if (team != null) {
                    double fraction = 0;
                    for (String teamPlayer : team.getPlayers()) {
                        MinecraftServer server = living.getServer();
                        ServerPlayer player1 = server.getPlayerList().getPlayerByName(teamPlayer);
                        if (player1 != null) {
                            MobEffectInstance martyr1 = player1.getEffect(ModMobEffects.MARTYR);
                            if (martyr1 != null) {
                                fraction +=  Services.PLATFORM.getConfig().martyr();
                                double transferred = baseDamage * Services.PLATFORM.getConfig().martyr();
                                player1.hurt(source, (float) transferred);
                                if (fraction >= 1) break;
                            }
                        }
                    }
                    baseDamage *= 1 - fraction;
                }
            }
        }


        MobEffectInstance exposed = living.getEffect(ModMobEffects.EXPOSED);
        if (exposed != null) {
            baseDamage *= 1 + Services.PLATFORM.getConfig().exposed() * (exposed.getAmplifier()+1);
        }

        MobEffectInstance vulnerable = living.getEffect(ModMobEffects.VULNERABLE);
        if (vulnerable != null) {
            baseDamage *= 1 + Services.PLATFORM.getConfig().vulnerable()  * (vulnerable.getAmplifier()+1);
        }

        Entity attacker = source.getEntity();

        if (attacker instanceof LivingEntity livingAttacker) {
            if (source.is(DamageTypeTags.IS_PROJECTILE)) {
                double projectile_damage = livingAttacker.getAttributeValue(ModAttributes.PROJECTILE_ATTACK_DAMAGE);
                baseDamage *= projectile_damage;
            }

            MobEffectInstance arcanic_overload = livingAttacker.getEffect(ModMobEffects.ARCANIC_OVERLOAD);
            if (arcanic_overload != null && !source.is(DamageTypes.MAGIC)) {
                living.hurt(living.damageSources().magic(), (float)
                        (Services.PLATFORM.getConfig().arcanic_overload() * (arcanic_overload.getAmplifier()+1) * baseDamage));
            }

            MobEffectInstance retribution = living.getEffect(ModMobEffects.RETRIBUTION);

            if (retribution != null) {
                AttributeInstance spellPower = living.getAttributes().hasAttribute(Services.PLATFORM.getEnderSpellPower())
                        ? living.getAttribute(Services.PLATFORM.getEnderSpellPower()) : null;

                if (spellPower != null) {
                    attacker.hurt(living.damageSources().thorns(living), (float)
                            (Services.PLATFORM.getConfig().retribution() * (retribution.getAmplifier() + 1) * spellPower.getValue()));
                }
            }

            MobEffectInstance domineering = livingAttacker.getEffect(ModMobEffects.DOMINEERING);

            if (domineering != null) {
                long count = living.getActiveEffects()
                        .stream().filter(mobEffectInstance -> mobEffectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL).count();
                baseDamage *= 1 + (Services.PLATFORM.getConfig().domineering() * domineering.getAmplifier() + 1) * count;
            }

            MobEffectInstance battle_mage = livingAttacker.getEffect(ModMobEffects.BATTLE_MAGE);

            if (battle_mage != null && !source.is(DamageTypes.MAGIC)) {

                double spell_power = living.getAttributeValue(Services.PLATFORM.getSpellPower());
                double ender_spell_power = living.getAttributeValue(Services.PLATFORM.getEnderSpellPower());

                double extraDamage = Services.PLATFORM.getConfig().battle_mage() * (battle_mage.getAmplifier() + 1) * (1 +ender_spell_power + spell_power) * baseDamage;
                living.hurt(living.level().damageSources().thorns(attacker), (float) extraDamage);
            }
        }

        MobEffectInstance marked = living.getEffect(ModMobEffects.MARKED);
        if (marked != null && !source.is(DamageTypes.MAGIC)) {
            baseDamage *= 1 + (Services.PLATFORM.getConfig().marked() * marked.getAmplifier() + 1);
            living.removeEffect(ModMobEffects.MARKED);
        }


        return baseDamage;
    }

    public static boolean livingDeath(LivingEntity living,DamageSource source) {
        if (living.hasEffect(ModMobEffects.REVIVE)) {
            living.setHealth((float) (living.getMaxHealth() * Services.PLATFORM.getConfig().revive()));
            living.removeEffect(ModMobEffects.REVIVE);
            return true;
        }
        return false;
    }

    public static float modifyDamageAfterMagicAbsorb(LivingEntity living,DamageSource source,float amount) {
        if (living.hasEffect(ModMobEffects.WARDEN) && !source.is(DamageTypeTags.BYPASSES_RESISTANCE)) {
            return amount * .85f;
        }
        return amount;
    }

    public static int getLootingLevel(LivingEntity entity, int lootingLevel) {
        MobEffectInstance mobEffect = entity.getEffect(ModMobEffects.LOOTING);
        if (mobEffect != null) {
            lootingLevel+= mobEffect.getAmplifier() +1;
        }

        return lootingLevel;
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID,path);
    }

}