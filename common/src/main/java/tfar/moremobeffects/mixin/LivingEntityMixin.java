package tfar.moremobeffects.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import tfar.moremobeffects.MoreMobEffects;
import tfar.moremobeffects.init.ModAttributes;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    
    @Inject(at = @At("TAIL"), method = "createLivingAttributes")
    private static void init(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue().add(ModAttributes.PROJECTILE_ATTACK_DAMAGE).add(ModAttributes.RESISTANCE);
    }

    @ModifyVariable(method = "getDamageAfterMagicAbsorb", at = @At("HEAD"), argsOnly = true)
    private float modifyDamage(float pDamageAmount,DamageSource pDamageSource) {
        return MoreMobEffects.modifyDamageAfterMagicAbsorb((LivingEntity) (Object)this, pDamageSource, pDamageAmount);
    }
}