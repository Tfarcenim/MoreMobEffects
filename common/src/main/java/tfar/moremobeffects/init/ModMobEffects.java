package tfar.moremobeffects.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import tfar.moremobeffects.ModConfig;
import tfar.moremobeffects.effect.*;
import tfar.moremobeffects.platform.Services;

public class ModMobEffects {

    public static final MobEffect VULNERABLE = new DamageMultiplierEffect(MobEffectCategory.HARMFUL,0xff0000);
    public static final MobEffect EXPOSED = new DamageMultiplierEffect(MobEffectCategory.HARMFUL,0xff0000);
    public static final boolean ATTRIBUTESLIB = Services.PLATFORM.isModLoaded("attributeslib");
    public static final boolean IRONS_SPELLBOOKS = Services.PLATFORM.isModLoaded("irons_spellbooks");

    public static final MobEffect ARCHERY = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(ModAttributes.PROJECTILE_ATTACK_DAMAGE,"c43c24a2-ef12-4670-9801-ebaba2dab2c8",ModConfig.Server.archery, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final MobEffect AIMING = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(ModAttributes.PROJECTILE_ATTACK_DAMAGE,"38a9e050-71a9-4547-8359-57bea880d036", ModConfig.Server.aiming, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final MobEffect MARKSMAN = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(ModAttributes.PROJECTILE_ATTACK_DAMAGE,"949b61bc-5f3d-42da-af06-2af1e3ce5a71",ModConfig.Server.marksman, AttributeModifier.Operation.MULTIPLY_TOTAL);

    public static final MobEffect MIGHT = new ConfigurableStrengthEffect(MobEffectCategory.BENEFICIAL,0xff0000,ModConfig.Server.might)
            .addAttributeModifier(Attributes.ATTACK_DAMAGE,"c2989b6e-1394-470c-ae75-c81367c3f9b9", 0, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final MobEffect STRENGTHENED = new ConfigurableStrengthEffect(MobEffectCategory.BENEFICIAL,0xff0000,ModConfig.Server.strengthened)
            .addAttributeModifier(Attributes.ATTACK_DAMAGE,"623eb27b-ce58-4672-9539-32b2f3e06c8d", 0, AttributeModifier.Operation.MULTIPLY_TOTAL);

    public static final MobEffect INSPIRED = ATTRIBUTESLIB ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getCriticalHitRate,"576c3243-94d3-4a86-bddf-78b57b0a2c4c",ModConfig.Server.inspired, AttributeModifier.Operation.ADDITION)
            : null;
    public static final MobEffect PRECISE = ATTRIBUTESLIB ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getCriticalHitRate,"b3f05015-d905-4e6f-a2b9-340559e7fd2a",ModConfig.Server.precise, AttributeModifier.Operation.ADDITION)
            : null;

    public static final MobEffect BRUTALITY = ATTRIBUTESLIB ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getCriticalHitDamage,"fb1fa74d-2925-4423-8c37-76aa2b3234a6", ModConfig.Server.brutality, AttributeModifier.Operation.MULTIPLY_TOTAL)
            : null;
    public static final MobEffect SAVAGE = ATTRIBUTESLIB ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getCriticalHitDamage,"15d3734f-8dab-485f-8b42-417f89794f9d",ModConfig.Server.savage, AttributeModifier.Operation.MULTIPLY_TOTAL)
            : null;

    public static final MobEffect ARCHERS_FRENZY = ATTRIBUTESLIB ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getArrowDrawSpeed,"2eb65dfd-145e-4263-bc34-c38b82eac5dc", ModConfig.Server.archers_frenzy, AttributeModifier.Operation.MULTIPLY_TOTAL)
            : null;
    public static final MobEffect HASTY = ATTRIBUTESLIB ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getArrowDrawSpeed,"12ffc09f-db1d-46b7-b263-f63fe4177c5b", ModConfig.Server.hasty, AttributeModifier.Operation.MULTIPLY_TOTAL)
            : null;

    public static final MobEffect EMPOWERED = IRONS_SPELLBOOKS ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getSpellPower,"cafda9bf-0ebf-41e5-85fa-065340e66763",ModConfig.Server.empowered, AttributeModifier.Operation.MULTIPLY_TOTAL)
            : null;
    public static final MobEffect MAGIC_UP = IRONS_SPELLBOOKS ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getSpellPower,"b2877f0b-8def-42f7-be7d-de069edc079d",ModConfig.Server.magic_up, AttributeModifier.Operation.MULTIPLY_TOTAL)
            : null;
    public static final MobEffect ARCANE_BOOST = IRONS_SPELLBOOKS ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getSpellPower,"e47ab303-40f0-4c8f-a88c-8b1aa450dfc9",ModConfig.Server.arcane_boost, AttributeModifier.Operation.MULTIPLY_TOTAL)
            : null;


    public static final MobEffect SPELL_HASTE = IRONS_SPELLBOOKS ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getSpellCooldownReduction,"651d6520-a30c-415e-b2b7-0388c4c017be",ModConfig.Server.spell_haste, AttributeModifier.Operation.MULTIPLY_TOTAL)
            : null;

    public static final MobEffect FOCUSED = IRONS_SPELLBOOKS ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getSpellCastTimeReduction,"e5ad4abb-d4f7-4b26-adac-64f2944c3af8",ModConfig.Server.focused, AttributeModifier.Operation.MULTIPLY_TOTAL)
            : null;

    public static final MobEffect ARCANIC_CONVERSION = IRONS_SPELLBOOKS ? new ArcanicConversionMobEffect(MobEffectCategory.BENEFICIAL,0xff0000, ArcanicConversionMobEffect.Variant.physical) : null;
    public static final MobEffect ARCANIC_OVERLOAD = new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000);
    public static final MobEffect RETRIBUTION = IRONS_SPELLBOOKS ? new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000) : null;
    public static final MobEffect DOMINEERING = new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000);
    public static final MobEffect MARKED = new CustomMobEffect(MobEffectCategory.HARMFUL,0xff0000);
    public static final MobEffect MANA_FLARE = IRONS_SPELLBOOKS ? new ManaFlareMobEffect(MobEffectCategory.HARMFUL,0xff0000) : null;
    public static final MobEffect ENDERGIZED = IRONS_SPELLBOOKS ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getEnderSpellPower,"5bb0fc9f-b714-4fe5-94c5-a1810596bc14",ModConfig.Server.endergized, AttributeModifier.Operation.MULTIPLY_TOTAL)
            :null;
    public static final MobEffect ENDERPHINS = IRONS_SPELLBOOKS ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getEnderSpellPower,"fcec827c-e5d8-4119-8758-5a20aa8127d4",ModConfig.Server.enderphins, AttributeModifier.Operation.MULTIPLY_TOTAL)
            :null;

    public static final MobEffect LIFE_STEAL = ATTRIBUTESLIB ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getLifeSteal,"3b5e19e5-b4d6-4f0e-a07b-95f6e9b11002",ModConfig.Server.life_steal, AttributeModifier.Operation.ADDITION)
            :null;

    public static final MobEffect ARMOR_PIERCE = ATTRIBUTESLIB ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getArmorPiercing,"77b66b2b-016d-4834-af75-2fc5171568e0",ModConfig.Server.armor_pierce, AttributeModifier.Operation.ADDITION)
            :null;

    public static final MobEffect OVERHEAL = ATTRIBUTESLIB ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getOverheal,"20a3054e-b56e-4f65-a7ab-09f8e61de5bf",ModConfig.Server.overheal, AttributeModifier.Operation.ADDITION)
            :null;

    public static final MobEffect SUMMON_BOOST = IRONS_SPELLBOOKS ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getSummonDamage,"af4d0fe7-9e0d-4673-8594-df665049b2f8",ModConfig.Server.summon_boost, AttributeModifier.Operation.MULTIPLY_TOTAL)
            :null;

    public static final MobEffect SPELL_EXHAUSTION = IRONS_SPELLBOOKS ? new ConfigurableMobEffect(MobEffectCategory.HARMFUL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getSpellPower,"37789c2c-6034-4e8c-985f-647ecca0ea6d",ModConfig.Server.spell_exhaustion, AttributeModifier.Operation.MULTIPLY_TOTAL,true)
            :null;

    public static final MobEffect DISARMING = new ConfigurableMobEffect(MobEffectCategory.HARMFUL,0xff0000)
            .addConfigurableAttributeModifier(ModAttributes.PROJECTILE_ATTACK_DAMAGE,"d5d5a1fe-fb0c-493a-8b2d-8f33b35e6b80",ModConfig.Server.disarming, AttributeModifier.Operation.MULTIPLY_TOTAL,true);


    public static final MobEffect ENDERS_GAME = IRONS_SPELLBOOKS ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getSpellPower,"c547c1d9-de47-451e-968a-6155c2eefe14",ModConfig.Server.enders_game_spell_power_decrease, AttributeModifier.Operation.MULTIPLY_TOTAL,true)
            .addConfigurableAttributeModifier(Services.PLATFORM::getSpellCooldownReduction,"48f19c4f-47da-4e37-8574-61e4804c5f29",ModConfig.Server.enders_game_spell_cooldown_decrease, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addConfigurableAttributeModifier(Services.PLATFORM::getSpellCastTimeReduction,"8eff1bf0-a7ba-44b6-8479-e78f64a2aa94",ModConfig.Server.enders_game_spell_cast_time_decrease, AttributeModifier.Operation.MULTIPLY_TOTAL)
            :null;

    public static final MobEffect WILL_OF_THE_SUMMONER = IRONS_SPELLBOOKS ? new ArcanicConversionMobEffect(MobEffectCategory.BENEFICIAL,0xff0000, ArcanicConversionMobEffect.Variant.summoner) : null;
    public static final MobEffect BATTLE_MAGE = IRONS_SPELLBOOKS ? new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000) : null;

    public static final MobEffect PEAK_HEALTH = ATTRIBUTESLIB ? new PeakHealthEffect(MobEffectCategory.BENEFICIAL,0xff0000) : null;
    public static final MobEffect BERSERK = IRONS_SPELLBOOKS ? new BerserkEffect(MobEffectCategory.NEUTRAL,0xff0000) : null;
    public static final MobEffect REVIVE = new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000);
    public static final MobEffect MARTYR = new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000);
    public static final MobEffect WARDEN = IRONS_SPELLBOOKS ? new WardenEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            :null;

    public static final MobEffect LOOTING = new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000);
    public static final MobEffect SOUL_SCORCHED = new CustomMobEffect(MobEffectCategory.HARMFUL,0xff0000);

    public static final MobEffect COMMANDING_ASPECT = IRONS_SPELLBOOKS && ATTRIBUTESLIB ? new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000) :null;
    public static final MobEffect WOLF_ASPECT = ATTRIBUTESLIB ? new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000) :null;
    public static final MobEffect WITHERING_ASPECT = ATTRIBUTESLIB ? new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000) : null;
    public static final MobEffect SIGIL_OF_MANA = IRONS_SPELLBOOKS ? new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000) : null;
    public static final MobEffect SORCEROUS_TRANSFERENCE = IRONS_SPELLBOOKS ? new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000) : null;

    public static final MobEffect BLOCK_ED = new Block_edEffect(MobEffectCategory.HARMFUL,0xff0000);
    public static final MobEffect TIME_FOR_A_BREAK = new CustomMobEffect(MobEffectCategory.HARMFUL,0xff0000);
    public static final MobEffect DISPELLED = new DispelledEffect(MobEffectCategory.HARMFUL,0xff0000);
    public static final MobEffect REJUVENATED = new RejuvenatedEffect(MobEffectCategory.BENEFICIAL,0xff0000);
    public static final MobEffect BEEFY = new ConfigurableHealthBoostMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Attributes.MAX_HEALTH, "d3fa24af-1450-4fec-852f-3af1499cb86a", ModConfig.Server.beefy, AttributeModifier.Operation.ADDITION);

    public static final MobEffect ROBUST = new ConfigurableHealthBoostMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Attributes.MAX_HEALTH, "549923b8-28bb-4b03-97ab-2750fd3d7972", ModConfig.Server.robust, AttributeModifier.Operation.MULTIPLY_TOTAL);


    public static final MobEffect HARDY = new ConfigurableHealthBoostMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Attributes.MAX_HEALTH, "cb35a893-3ace-4359-864b-0a43014fa75c", ModConfig.Server.hardy, AttributeModifier.Operation.MULTIPLY_TOTAL);

    public static final MobEffect SWINGY = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Attributes.ATTACK_SPEED, "8feb35da-06fd-4a81-bc2d-5c1ffbee5360", ModConfig.Server.swingy, AttributeModifier.Operation.ADDITION);

    public static final MobEffect MANA_REGENERATION = IRONS_SPELLBOOKS ? new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getManaRegen,"fa312d45-b803-40cb-abea-930f27cfeeea",ModConfig.Server.mana_regeneration, AttributeModifier.Operation.ADDITION): null;

    public static final MobEffect INJURY = new ConfigurableMobEffect(MobEffectCategory.HARMFUL,0xff0000)
            .addConfigurableAttributeModifier(Attributes.MAX_HEALTH,"e13e086f-a412-4b10-b31f-eb9b1e62f8ff",ModConfig.Server.injury, AttributeModifier.Operation.ADDITION,true);

    public static final MobEffect FRACTURED = new ConfigurableMobEffect(MobEffectCategory.HARMFUL,0xff0000)
            .addConfigurableAttributeModifier(Attributes.MAX_HEALTH,"5eb6dd61-eaf4-44b7-aac6-e1cc979e9980",ModConfig.Server.fractured, AttributeModifier.Operation.MULTIPLY_TOTAL,true);



    public static final MobEffect PLEDGE_OF_UNITY = new PledgeOfUnityEffect(MobEffectCategory.BENEFICIAL,0xff0000);

    public static final MobEffect PLEDGE_OF_SOLITUDE = new PledgeOfSolitudeEffect(MobEffectCategory.BENEFICIAL,0xff0000);

}
