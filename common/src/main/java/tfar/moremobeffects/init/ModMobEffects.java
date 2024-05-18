package tfar.moremobeffects.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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


}
