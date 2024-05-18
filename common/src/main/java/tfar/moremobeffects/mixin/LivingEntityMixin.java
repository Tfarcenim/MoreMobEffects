package tfar.moremobeffects.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.moremobeffects.MoreMobEffects;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.moremobeffects.init.ModAttributes;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    
    @Inject(at = @At("TAIL"), method = "createLivingAttributes")
    private static void init(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue().add(ModAttributes.PROJECTILE_ATTACK_DAMAGE);
    }
}