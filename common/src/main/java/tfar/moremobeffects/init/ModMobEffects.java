package tfar.moremobeffects.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import tfar.moremobeffects.effect.CustomMobEffect;
import tfar.moremobeffects.effect.DamageMultiplierEffect;

public class ModMobEffects {

    public static final MobEffect VULNERABLE = new DamageMultiplierEffect(MobEffectCategory.HARMFUL,0xff0000);
    public static final MobEffect EXPOSED = new DamageMultiplierEffect(MobEffectCategory.HARMFUL,0xff0000);

    public static final MobEffect ARCHERY = new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addAttributeModifier(ModAttributes.PROJECTILE_ATTACK_DAMAGE,"c43c24a2-ef12-4670-9801-ebaba2dab2c8",.05, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final MobEffect AIMING = new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addAttributeModifier(ModAttributes.PROJECTILE_ATTACK_DAMAGE,"38a9e050-71a9-4547-8359-57bea880d036",.05, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final MobEffect MARKSMAN = new CustomMobEffect(MobEffectCategory.BENEFICIAL,0xff0000)
            .addAttributeModifier(ModAttributes.PROJECTILE_ATTACK_DAMAGE,"949b61bc-5f3d-42da-af06-2af1e3ce5a71",.05, AttributeModifier.Operation.MULTIPLY_TOTAL);


}
