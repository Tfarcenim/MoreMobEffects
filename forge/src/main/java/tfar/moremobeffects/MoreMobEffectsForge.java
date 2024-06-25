package tfar.moremobeffects;

import com.google.common.collect.Lists;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.commons.lang3.tuple.Pair;
import tfar.moremobeffects.datagen.ModDatagen;
import tfar.moremobeffects.init.ModMobEffects;

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
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.SERVER, SERVER_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::register);
        bus.addListener(this::commonSetup);
        bus.addListener(ModDatagen::start);
        MinecraftForge.EVENT_BUS.addListener(this::livingAttack);
        MinecraftForge.EVENT_BUS.addListener(this::livingDeath);
        MinecraftForge.EVENT_BUS.addListener(this::looting);
        MinecraftForge.EVENT_BUS.addListener(this::applyEffects);
        // Use Forge to bootstrap the Common mod.
        MoreMobEffects.init();
    }

    public static final TomlConfig.Server SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<TomlConfig.Server, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(TomlConfig.Server::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
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

    private void applyEffects(MobEffectEvent.Applicable event) {
        LivingEntity living = event.getEntity();
        MobEffect effect = event.getEffectInstance().getEffect();
        if (living.hasEffect(ModMobEffects.SOUL_SCORCHED)) {
            if (CommonTagUtil.isIn(ModTags.SOUL_SCORCHED_REMOVALS,effect)) {
                event.setResult(Event.Result.ALLOW);
            }
        }
    }

    private void looting(LootingLevelEvent event) {
        event.setLootingLevel(MoreMobEffects.getLootingLevel(event.getEntity(),event.getLootingLevel()));
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        registerLater.clear();
        MoreMobEffects.commonSetup();
    }

    private void livingAttack(LivingHurtEvent event) {
        event.setAmount(MoreMobEffects.livingAttack(event.getEntity(),event.getSource(), event.getAmount()));
    }

    private void livingDeath(LivingDeathEvent event) {
        if (MoreMobEffects.livingDeath(event.getEntity(),event.getSource())) {
            event.setCanceled(true);
        }
    }
}