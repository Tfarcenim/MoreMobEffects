package tfar.moremobeffects.effect;

import com.google.common.collect.Maps;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import tfar.moremobeffects.platform.ConfigEntry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class ConfigurableMobEffect extends MobEffect {
    protected final Map<Supplier<Attribute>, Supplier<AttributeModifier>> configurableAttributeModifiers = Maps.newHashMap();

    public ConfigurableMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public ConfigurableMobEffect addConfigurableAttributeModifier(Attribute attribute, String uuid, Supplier<ConfigEntry> value, AttributeModifier.Operation operation) {
        return this.addConfigurableAttributeModifier(() -> attribute,uuid,value,operation);
    }

    public ConfigurableMobEffect addConfigurableAttributeModifier(Supplier<Attribute> attributeSupplier, String uuid, Supplier<ConfigEntry> value, AttributeModifier.Operation operation) {
        Supplier<AttributeModifier> modifier = () -> new AttributeModifier(UUID.fromString(uuid), this::getDescriptionId,value.get().getAsDouble(), operation);
        this.configurableAttributeModifiers.put(attributeSupplier, modifier);
        return this;
    }

    @Override
    public Map<Attribute, AttributeModifier> getAttributeModifiers() {
        Map<Attribute,AttributeModifier> allModifiers = new HashMap<>(super.getAttributeModifiers());
        configurableAttributeModifiers.forEach((attributeSupplier, attributeModifierSupplier) -> allModifiers.put(attributeSupplier.get(),attributeModifierSupplier.get()));
        return allModifiers;
    }

    @Override
    public void removeAttributeModifiers(LivingEntity living, AttributeMap map, int level) {
        for(Map.Entry<Attribute, AttributeModifier> entry : this.getAttributeModifiers().entrySet()) {
            AttributeInstance attributeInstance = map.getInstance(entry.getKey());
            if (attributeInstance != null) {
                attributeInstance.removeModifier(entry.getValue());
            }
        }
    }

    @Override
    public void addAttributeModifiers(LivingEntity living, AttributeMap map, int level) {
        for(Map.Entry<Attribute, AttributeModifier> entry : this.getAttributeModifiers().entrySet()) {
            AttributeInstance attributeInstance = map.getInstance(entry.getKey());
            if (attributeInstance != null) {
                AttributeModifier attributeModifier = entry.getValue();
                attributeInstance.removeModifier(attributeModifier);
                attributeInstance.addPermanentModifier(
                        new AttributeModifier(attributeModifier.getId(), this.getDescriptionId() + " " + level, this.getAttributeModifierValue(level, attributeModifier), attributeModifier.getOperation())
                );
            }
        }
    }
}
