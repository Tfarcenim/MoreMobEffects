package tfar.moremobeffects.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageType;
import tfar.moremobeffects.MoreMobEffects;

public class ModDamageTypes {

    public static ResourceKey<DamageType> MANA_FLARE = ResourceKey.create(Registries.DAMAGE_TYPE, MoreMobEffects.id("mana_flare"));
    static void bootstrap(BootstapContext<DamageType> pContext) {
        pContext.register(MANA_FLARE, new DamageType("manaFlare", 0.1F, DamageEffects.BURNING));
    }

}
