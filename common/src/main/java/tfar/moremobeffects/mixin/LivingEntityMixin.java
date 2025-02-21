package tfar.moremobeffects.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import tfar.moremobeffects.LivingEntityDuck;
import tfar.moremobeffects.MoreMobEffects;
import tfar.moremobeffects.init.ModAttributes;
import tfar.moremobeffects.init.ModMobEffects;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements LivingEntityDuck {
    
    @Inject(at = @At("TAIL"), method = "createLivingAttributes")
    private static void init(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue().add(ModAttributes.PROJECTILE_ATTACK_DAMAGE).add(ModAttributes.RESISTANCE);
    }

    @ModifyVariable(method = "getDamageAfterMagicAbsorb", at = @At("HEAD"), argsOnly = true)
    private float modifyDamage(float pDamageAmount,DamageSource pDamageSource) {
        return MoreMobEffects.modifyDamageAfterMagicAbsorb((LivingEntity) (Object)this, pDamageSource, pDamageAmount);
    }

    @ModifyVariable(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z", at = @At("HEAD"), argsOnly = true)
    private MobEffectInstance modifyEffect(MobEffectInstance old) {
        return MoreMobEffects.onIncomingEffect(old,(LivingEntity) (Object)this);
    }

    @Unique
    int guardedTimer;
    @Unique
    double absorptionToRemove;
    @Override
    public int getGuardedTimer() {
        return guardedTimer;
    }

    @Override
    public void setGuardedTimer(int timer) {
        guardedTimer = timer;
    }

    @Override
    public double getAbsorptionToRemove() {
        return absorptionToRemove;
    }

    @Override
    public void setAbsorptionToRemove(double absorption) {
        absorptionToRemove = absorption;
    }
}