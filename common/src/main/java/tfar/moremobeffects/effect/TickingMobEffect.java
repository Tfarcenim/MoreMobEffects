package tfar.moremobeffects.effect;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import tfar.moremobeffects.MoreMobEffects;

import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

public abstract class TickingMobEffect extends MobEffect {
    private UUID uuid;
    private final Supplier<Set<Attribute>> attributes;

    protected TickingMobEffect(MobEffectCategory $$0, int $$1, Supplier<Set<Attribute>> attributes) {
        super($$0, $$1);
        this.attributes = attributes;
    }

    @Override
    public abstract void applyEffectTick(LivingEntity living, int amplifier);

    @Override
    public boolean isDurationEffectTick(int $$0, int $$1) {
        return true;
    }

    public UUID getUuid() {
        if (uuid == null) {
            uuid = MoreMobEffects.make(BuiltInRegistries.MOB_EFFECT.getKey(this));
        }
        return uuid;
    }

    @Override
    public void removeAttributeModifiers(LivingEntity $$0, AttributeMap map, int $$2) {
        super.removeAttributeModifiers($$0, map, $$2);
        attributes.get().forEach(attribute -> {
            AttributeInstance attributeInstance = map.getInstance(attribute);
            if (attributeInstance != null) {
                attributeInstance.removeModifier(getUuid());
            }
        });
    }
}
