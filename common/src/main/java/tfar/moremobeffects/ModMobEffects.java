package tfar.moremobeffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModMobEffects {

    public static final MobEffect VULNERABLE = new DamageMultiplierEffect(MobEffectCategory.HARMFUL,0xff0000);
    public static final MobEffect EXPOSED = new DamageMultiplierEffect(MobEffectCategory.HARMFUL,0xff0000);

    public static final MobEffect ARCHERY = new DamageMultiplierEffect(MobEffectCategory.BENEFICIAL,0xff0000);
    public static final MobEffect AIMING = new DamageMultiplierEffect(MobEffectCategory.BENEFICIAL,0xff0000);
    public static final MobEffect MARKSMAN = new DamageMultiplierEffect(MobEffectCategory.BENEFICIAL,0xff0000);


}
