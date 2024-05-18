package tfar.moremobeffects;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
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


    public static float livingAttack(LivingEntity living, DamageSource source,float baseDamage) {

        Entity attacker = source.getEntity();

        if (attacker instanceof LivingEntity livingAttacker && source.is(DamageTypeTags.IS_PROJECTILE)) {
            double projectile_damage = livingAttacker.getAttributeValue(ModAttributes.PROJECTILE_ATTACK_DAMAGE);
            baseDamage *= projectile_damage;
        }

        MobEffectInstance mobEffectInstance = living.getEffect(ModMobEffects.EXPOSED);
        if (mobEffectInstance != null) {
            baseDamage *= (1 + Services.PLATFORM.getConfig().exposed() * (mobEffectInstance.getAmplifier()+1));
        }

        MobEffectInstance mobEffectInstance2 = living.getEffect(ModMobEffects.VULNERABLE);
        if (mobEffectInstance2 != null) {
            baseDamage *= (1 + Services.PLATFORM.getConfig().vulnerable()  * (mobEffectInstance2.getAmplifier()+1));
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