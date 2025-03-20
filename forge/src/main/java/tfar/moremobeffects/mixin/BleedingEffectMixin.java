package tfar.moremobeffects.mixin;

import dev.shadowsoffire.attributeslib.mobfx.BleedingEffect;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import tfar.moremobeffects.MoreMobEffects;

@Mixin(BleedingEffect.class)
public class BleedingEffectMixin {
    @ModifyConstant(method = "applyEffectTick",constant = @Constant(floatValue = 1f,ordinal = 0))
    private float modifyBleed(float damage, LivingEntity entity) {
        return MoreMobEffects.getBleedDamage(damage, entity);
    }
}
