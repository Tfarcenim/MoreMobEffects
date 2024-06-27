package tfar.moremobeffects.effect;

import net.minecraft.world.effect.AttackDamageMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.function.Supplier;

public class ConfigurableStrengthEffect extends AttackDamageMobEffect {


    private final Supplier<Double> supplier;

    public ConfigurableStrengthEffect(MobEffectCategory $$0, int $$1, Supplier<Double> supplier) {
        super($$0, $$1, 0);
        this.supplier = supplier;
    }

    public double getAttributeModifierValue(int amplifier, AttributeModifier $$1) {
        return supplier.get() * (amplifier + 1);
    }
}
