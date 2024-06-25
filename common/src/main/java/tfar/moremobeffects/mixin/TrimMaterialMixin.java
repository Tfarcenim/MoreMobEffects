package tfar.moremobeffects.mixin;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TrimMaterials.class)
public class TrimMaterialMixin {

    @Inject(method = "bootstrap",at = @At("RETURN"))
    private static void registerDamageTypes(BootstapContext<TrimMaterial> $$0, CallbackInfo ci) {

    }

}
