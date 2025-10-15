package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import tfar.moremobeffects.ModConfig;

public class AegisEffect extends MobEffect {
    public AegisEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1);
    }
//Formula: Absorption Shield = Max Health  * (1 + (Ender Spell + Spell Power))%
    //New Formula: Base + (HP% * y)
    //Configurations Needed:
    //% Resistance gained (fixed)
    //Base per level (flat amount)
    //HP% per level ✓ (Completed)
    //Scaling factor (y); fixed (does not level)

    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {

        double absorptionBoost = calculateAbsorption(pAmplifier,pLivingEntity);

        pLivingEntity.setAbsorptionAmount(pLivingEntity.getAbsorptionAmount() - (float) absorptionBoost);

        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }

    double calculateAbsorption(int amplifier,LivingEntity livingEntity) {
       return ModConfig.SERVER.aegis_base_absorption.get() * (amplifier+1) +
                ModConfig.SERVER.aegis_absorption_hp_multiplier.get() * livingEntity.getMaxHealth();
    }

    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {

        double absorptionBoost = calculateAbsorption(pAmplifier,pLivingEntity);

        pLivingEntity.setAbsorptionAmount(pLivingEntity.getAbsorptionAmount() + (float) absorptionBoost);
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }

}
