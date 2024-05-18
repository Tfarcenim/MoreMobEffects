package tfar.moremobeffects.init;

import net.minecraft.world.effect.AttackDamageMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import tfar.moremobeffects.effect.ConfigurableMobEffect;
import tfar.moremobeffects.effect.DamageMultiplierEffect;
import tfar.moremobeffects.platform.Services;

public class ModMobEffects {

    public static final MobEffect VULNERABLE = new DamageMultiplierEffect(MobEffectCategory.HARMFUL,0xff0000);
    public static final MobEffect EXPOSED = new DamageMultiplierEffect(MobEffectCategory.HARMFUL,0xff0000);

    public static final MobEffect ARCHERY = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(ModAttributes.PROJECTILE_ATTACK_DAMAGE,"c43c24a2-ef12-4670-9801-ebaba2dab2c8",() -> Services.PLATFORM.getConfig().getConfigEntry("archery"), AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final MobEffect AIMING = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(ModAttributes.PROJECTILE_ATTACK_DAMAGE,"38a9e050-71a9-4547-8359-57bea880d036", () -> Services.PLATFORM.getConfig().getConfigEntry("aiming"), AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final MobEffect MARKSMAN = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(ModAttributes.PROJECTILE_ATTACK_DAMAGE,"949b61bc-5f3d-42da-af06-2af1e3ce5a71", () -> Services.PLATFORM.getConfig().getConfigEntry("marksman"), AttributeModifier.Operation.MULTIPLY_TOTAL);

    public static final MobEffect MIGHT = new AttackDamageMobEffect(MobEffectCategory.BENEFICIAL,0xff0000,3){}
            .addAttributeModifier(Attributes.ATTACK_DAMAGE,"c2989b6e-1394-470c-ae75-c81367c3f9b9", 0, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final MobEffect STRENGTHENED = new AttackDamageMobEffect(MobEffectCategory.BENEFICIAL,0xff0000,3){}
            .addAttributeModifier(Attributes.ATTACK_DAMAGE,"623eb27b-ce58-4672-9539-32b2f3e06c8d", 0, AttributeModifier.Operation.MULTIPLY_TOTAL);

    public static final MobEffect INSPIRED = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getCriticalHitRate,"576c3243-94d3-4a86-bddf-78b57b0a2c4c", () -> Services.PLATFORM.getConfig().getConfigEntry("inspired"), AttributeModifier.Operation.ADDITION);

    public static final MobEffect PRECISE = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getCriticalHitRate,"b3f05015-d905-4e6f-a2b9-340559e7fd2a",() -> Services.PLATFORM.getConfig().getConfigEntry("precise"), AttributeModifier.Operation.ADDITION);

    public static final MobEffect BRUTALITY = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getCriticalHitDamage,"fb1fa74d-2925-4423-8c37-76aa2b3234a6", () -> Services.PLATFORM.getConfig().getConfigEntry("brutality"), AttributeModifier.Operation.ADDITION);

    public static final MobEffect SAVAGE = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getCriticalHitDamage,"15d3734f-8dab-485f-8b42-417f89794f9d",() -> Services.PLATFORM.getConfig().getConfigEntry("savage"), AttributeModifier.Operation.ADDITION);

    public static final MobEffect ARCHERS_FRENZY = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getArrowDrawSpeed,"2eb65dfd-145e-4263-bc34-c38b82eac5dc", () -> Services.PLATFORM.getConfig().getConfigEntry("archers_frenzy"), AttributeModifier.Operation.ADDITION);

    public static final MobEffect HASTY = new ConfigurableMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addConfigurableAttributeModifier(Services.PLATFORM::getArrowDrawSpeed,"12ffc09f-db1d-46b7-b263-f63fe4177c5b",() -> Services.PLATFORM.getConfig().getConfigEntry("hasty"), AttributeModifier.Operation.ADDITION);


}
