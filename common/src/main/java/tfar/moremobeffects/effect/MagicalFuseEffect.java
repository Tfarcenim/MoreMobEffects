package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.Level;
import tfar.moremobeffects.ExplosionType;
import tfar.moremobeffects.ModConfig;
import tfar.moremobeffects.platform.Services;

public class MagicalFuseEffect extends MobEffect {
    public MagicalFuseEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity living, AttributeMap $$1, int amplifier) {
        super.removeAttributeModifiers(living, $$1, amplifier);
        double power = ModConfig.Server.magical_fuse_power.get() * (amplifier+1);
        AttributeInstance enderSpellPower = living.getAttribute(Services.PLATFORM.getEnderSpellPower());
        AttributeInstance spellPower = living.getAttribute(Services.PLATFORM.getSpellPower());
        if (enderSpellPower != null && spellPower != null) {
            power *= enderSpellPower.getValue() - spellPower.getValue() -1;
        }
        ExplosionType.customExplode(living.level(),living,living.getX(), living.getY(0.0625D), living.getZ(), (float) power, Level.ExplosionInteraction.NONE, ExplosionType.MAGICAL_FUSE);
    }
}
//When the effect duration ends, you explode, dealing (x * amplifier damage) * (ender spell - total spell power - 1) damage.
//Can the effect animation be the vanilla explosion for now?
//Explosion radius is at a fixed 5 block radius.
//Does not deal damage to:
//The player and team
//Blocks
//Minions/Summons or anything tagged as an ally
