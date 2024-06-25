package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import tfar.moremobeffects.platform.Services;

public class WardenEffect extends MobEffect {
    public WardenEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1);
    }
//Formula: Absorption Shield = Max Health  * (1 + (Ender Spell + Spell Power))%

    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {

        double absorptionBoost = Services.PLATFORM.getConfig().warden_absorption() * pLivingEntity.getMaxHealth();

        if (pLivingEntity instanceof Player player) {
            absorptionBoost *= -1 + player.getAttributeValue(Services.PLATFORM.getEnderSpellPower()) + player.getAttributeValue(Services.PLATFORM.getSpellPower());
        }
        pLivingEntity.setAbsorptionAmount(pLivingEntity.getAbsorptionAmount() - (float) absorptionBoost * (pAmplifier + 1));

        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }

    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {

        double absorptionBoost = Services.PLATFORM.getConfig().warden_absorption() * pLivingEntity.getMaxHealth();

        if (pLivingEntity instanceof Player player) {
            absorptionBoost *= -1 + player.getAttributeValue(Services.PLATFORM.getEnderSpellPower()) + player.getAttributeValue(Services.PLATFORM.getSpellPower());
        }

        pLivingEntity.setAbsorptionAmount(pLivingEntity.getAbsorptionAmount() + (float) absorptionBoost * (pAmplifier + 1));
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }

}
