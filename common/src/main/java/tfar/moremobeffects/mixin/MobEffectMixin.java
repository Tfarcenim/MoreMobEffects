package tfar.moremobeffects.mixin;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import tfar.moremobeffects.MoreMobEffects;

@Mixin(MobEffect.class)
//@Debug(export = true)
public class MobEffectMixin {
    @ModifyConstant(method = "applyEffectTick",constant = @Constant(floatValue = 1f,ordinal = 2))
    private float modifyPoison(float damage, LivingEntity entity) {
        return MoreMobEffects.getPoisonDamage(damage, entity);
    }

    @ModifyConstant(method = "applyEffectTick",constant = @Constant(floatValue = 1f,ordinal = 3))
    private float modifyWither(float damage, LivingEntity entity) {
        return MoreMobEffects.getWitherDamage(damage, entity);
    }
}
