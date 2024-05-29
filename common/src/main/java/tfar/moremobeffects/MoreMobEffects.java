package tfar.moremobeffects;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
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

        MobEffectInstance exposed = living.getEffect(ModMobEffects.EXPOSED);
        if (exposed != null) {
            baseDamage *= (1 + Services.PLATFORM.getConfig().exposed() * (exposed.getAmplifier()+1));
        }

        MobEffectInstance vulnerable = living.getEffect(ModMobEffects.VULNERABLE);
        if (vulnerable != null) {
            baseDamage *= (1 + Services.PLATFORM.getConfig().vulnerable()  * (vulnerable.getAmplifier()+1));
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
                attacker.hurt(living.damageSources().thorns(living), (float)
                        (Services.PLATFORM.getConfig().retribution() * (retribution.getAmplifier()+1)));
            }

            MobEffectInstance domineering = livingAttacker.getEffect(ModMobEffects.DOMINEERING);

            if (domineering != null) {
                long count = living.getActiveEffects()
                        .stream().filter(mobEffectInstance -> mobEffectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL).count();
                baseDamage *= (1 + (Services.PLATFORM.getConfig().domineering() * domineering.getAmplifier() + 1) * count);
            }

        }

        MobEffectInstance marked = living.getEffect(ModMobEffects.MARKED);
        if (marked != null && !source.is(DamageTypes.MAGIC)) {
            baseDamage *= (1 + (Services.PLATFORM.getConfig().marked() * marked.getAmplifier() + 1));
            living.removeEffect(ModMobEffects.MARKED);
        }

        return baseDamage;
    }
}

//**__Description__**:
//- I'm creating a modpack based around stacking buffs and debuffs in combat.
//- I'd like potion effects to have "duplicates". Same effect, but different name and can stack on top of each other. Most of the effects I requested have an attribute available.
//- A config file to adjust per-level  would be appreciated but it is not mandatory. If not, I'd like the % indicated in the descriptions.
//
//> **General**
//***__1.) Projectile Damage Boost (3 total)__***
//- If it is possible for this effect to boost the damage of all ranged sources as opposed to just arrows, that would be great.
//- Names: (**Archery**, **Aiming**,  & **Marksman**)
//- 5% increase per level
//
//***__2.) Strength (2 total)__***
//- 5% Mult. increase per level
//- Names: **Might **& **Strengthened**
//
//> **Apothic Lib: **
//Reference:
//https://github.com/Shadows-of-Fire/Apothic-Attributes/blob/1.20/src/main/java/dev/shadowsoffire/attributeslib/api/ALObjects.java#L26
//
//**__*3.) Critical Rate (2 total)*__**
//- 5% Total crit rate per level
//- Names: **Inspired **& **Precise**
//
//**__*4.) Critical Damage (3 total)*__**
//- 5% Mult. increase  per level
//- Names: **Brutality**, & **Savage**
//
//**__*5.) Draw Speed (2 total)*__**
//- 5% Mult. speed increase per level
//- Names: **Archer's Frenzy**, **Hasty**
//
//> **Iron's Spell Books**
//Reference:
//https://github.com/iron431/Irons-Spells-n-Spellbooks
//
//**__*6.) Spell Power (3 total)*__**
//- 5% Mult. increase per level
//- Names: **Empowered**, **Magic UP**, **Arcane Boost**
//
//**__*7.) Spell Cooldown  (1 total)*__**
//- 5% Mult. decrease per level
//- Name: **Spell Haste**
//
//**__*8.) Spell Cast Time  (1 total)*__**
//- 5% Mult. decrease per level
//- Name: **Focused**