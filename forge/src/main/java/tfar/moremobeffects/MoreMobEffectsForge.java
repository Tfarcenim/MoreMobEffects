package tfar.moremobeffects;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Team;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
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
import java.util.UUID;
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
        MinecraftForge.EVENT_BUS.addListener(this::livingHurt);
        MinecraftForge.EVENT_BUS.addListener(this::livingDamage);
        MinecraftForge.EVENT_BUS.addListener(this::livingDeath);
        MinecraftForge.EVENT_BUS.addListener(this::looting);
        MinecraftForge.EVENT_BUS.addListener(this::applyEffects);
        MinecraftForge.EVENT_BUS.addListener(this::onBlockPlace);
        MinecraftForge.EVENT_BUS.addListener(this::onBlockBreak);
        MinecraftForge.EVENT_BUS.addListener(this::onBreakSpeed);
        MinecraftForge.EVENT_BUS.addListener(this::playerTick);
        // Use Forge to bootstrap the Common mod.
        MoreMobEffects.init();
    }

    public static final ModConfig.Server SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<ModConfig.Server, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(ModConfig.Server::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
    }

    public static Map<Registry<?>, List<Pair<ResourceLocation, Supplier<Object>>>> registerLater = new HashMap<>();
    private void register(RegisterEvent e) {
        List<Pair<ResourceLocation, Supplier<Object>>> list = registerLater.get(e.getVanillaRegistry());
        if (list != null) {
            for (Pair<ResourceLocation,Supplier<Object>> pair :  list) {
                e.register((ResourceKey<? extends Registry<Object>>)e.getRegistryKey(),pair.getLeft(),pair.getRight());
            }
        }
    }

    private void onBlockBreak(BlockEvent.BreakEvent event) {
       Player player = event.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            if (player.hasEffect(ModMobEffects.TIME_FOR_A_BREAK)) {
                event.setCanceled(true);
            }
        }
    }

    private void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (player != null && !player.getAbilities().instabuild) {
            if (player.hasEffect(ModMobEffects.TIME_FOR_A_BREAK)) {
                event.setCanceled(true);
            }
        }
    }

    private void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
    /*    if (entity instanceof LivingEntity living) {
            if (living.hasEffect(ModMobEffects.BLOCK_ED)) {
                event.setCanceled(true);
            }
        }*/
    }

    private void applyEffects(MobEffectEvent.Applicable event) {
        LivingEntity living = event.getEntity();
        MobEffect effect = event.getEffectInstance().getEffect();
        if (living.hasEffect(ModMobEffects.SOUL_SCORCHED)) {
            if (CommonTagUtil.isIn(ModTags.SOUL_SCORCHED_REMOVALS,effect)) {
                event.setResult(Event.Result.ALLOW);
            }
        }

        if (living.hasEffect(ModMobEffects.DISPELLED) && effect.isBeneficial()) {
            event.setResult(Event.Result.DENY);
        }

    }

    private void looting(LootingLevelEvent event) {
        event.setLootingLevel(MoreMobEffects.getLootingLevel(event.getEntity(),event.getDamageSource(),event.getLootingLevel()));
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        registerLater.clear();
        MoreMobEffects.commonSetup();
        if (ModMobEffects.IRONS_SPELLBOOKS) {
            MinecraftForge.EVENT_BUS.addListener(IronSpellbooksEvents::spellDamage);
        }
    }

    private void livingHurt(LivingHurtEvent event) {
        event.setAmount(MoreMobEffects.livingHurt(event.getEntity(),event.getSource(), event.getAmount()));
    }

    private void livingDamage(LivingDamageEvent event){
        MoreMobEffects.livingDamage(event.getEntity(),event.getSource(),event.getAmount());
    }

    private void livingDeath(LivingDeathEvent event) {
        if (MoreMobEffects.livingDeath(event.getEntity(),event.getSource())) {
            event.setCanceled(true);
        }
    }





    void playerTick(TickEvent.PlayerTickEvent event) {

    }
}