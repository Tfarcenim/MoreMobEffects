package tfar.moremobeffects;

import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import tfar.moremobeffects.init.ModMobEffects;
import tfar.moremobeffects.platform.Services;

public class IronSpellbooksEvents {

    public static void spellDamage(SpellDamageEvent event) {
        LivingEntity target = event.getEntity();
        SpellDamageSource spellDamageSource = event.getSpellDamageSource();
        Entity attacker = spellDamageSource.getEntity();

        if (target.hasEffect(ModMobEffects.MANA_FLARE) && attacker instanceof LivingEntity livingAttacker) {
            MobEffectInstance mobEffectInstance = livingAttacker.getEffect(ModMobEffects.SIGIL_OF_MANA);
            if (mobEffectInstance != null) {
                event.setAmount((float) (event.getOriginalAmount() * (1 + Services.PLATFORM.getConfig().sigil_of_mana() * (mobEffectInstance.getAmplifier() + 1) )));
            }
        }
    }
}
