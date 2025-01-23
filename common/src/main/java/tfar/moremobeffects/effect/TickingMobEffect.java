package tfar.moremobeffects.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

public abstract class TickingMobEffect extends MobEffect {
    private final UUID uuid;
    private final Supplier<Set<Attribute>> attributes;

    protected TickingMobEffect(MobEffectCategory $$0, int $$1, UUID uuid, Supplier<Set<Attribute>> attributes) {
        super($$0, $$1);
        this.uuid = uuid;
        this.attributes = attributes;
    }

    @Override
    public abstract void applyEffectTick(LivingEntity living, int amplifier);

    @Override
    public boolean isDurationEffectTick(int $$0, int $$1) {
        return true;
    }

    @Override
    public void removeAttributeModifiers(LivingEntity $$0, AttributeMap map, int $$2) {
        super.removeAttributeModifiers($$0, map, $$2);
        attributes.get().forEach(attribute -> {
            AttributeInstance attributeInstance = map.getInstance(attribute);
            if (attributeInstance != null) {
                attributeInstance.removeModifier(uuid);
            }
        });
    }
}
