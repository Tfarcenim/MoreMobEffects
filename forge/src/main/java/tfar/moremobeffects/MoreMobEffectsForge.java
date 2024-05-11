package tfar.moremobeffects;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod(MoreMobEffects.MOD_ID)
public class MoreMobEffectsForge {
    
    public MoreMobEffectsForge() {
    
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::register);
        bus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.addListener(this::livingAttack);
        // Use Forge to bootstrap the Common mod.
        MoreMobEffects.init();
    }

    public static Map<Registry<?>, List<Pair<ResourceLocation, Supplier<?>>>> registerLater = new HashMap<>();
    private void register(RegisterEvent e) {
        for (Map.Entry<Registry<?>,List<Pair<ResourceLocation, Supplier<?>>>> entry : registerLater.entrySet()) {
            Registry<?> registry = entry.getKey();
            List<Pair<ResourceLocation, Supplier<?>>> toRegister = entry.getValue();
            for (Pair<ResourceLocation,Supplier<?>> pair : toRegister) {
                e.register((ResourceKey<? extends Registry<Object>>)registry.key(),pair.getLeft(),(Supplier<Object>)pair.getValue());
            }
        }
    }
    private void commonSetup(FMLCommonSetupEvent event) {
        registerLater.clear();
    }

    private void livingAttack(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        MobEffectInstance mobEffectInstance = living.getEffect(ModMobEffects.EXPOSED);
        if (mobEffectInstance != null) {
            event.setAmount(event.getAmount() *(1 + .25f * (mobEffectInstance.getAmplifier()+1)));
        }

        MobEffectInstance mobEffectInstance2 = living.getEffect(ModMobEffects.VULNERABLE);
        if (mobEffectInstance2 != null) {
            event.setAmount(event.getAmount() *(1 + .25f * (mobEffectInstance2.getAmplifier()+1)));
        }
    }

}