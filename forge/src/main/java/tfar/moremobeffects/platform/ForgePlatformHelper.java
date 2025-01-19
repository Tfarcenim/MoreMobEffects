package tfar.moremobeffects.platform;

import dev.shadowsoffire.attributeslib.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.commons.lang3.tuple.Pair;
import tfar.moremobeffects.MoreMobEffects;
import tfar.moremobeffects.MoreMobEffectsForge;
import tfar.moremobeffects.network.PacketHandlerForge;
import tfar.moremobeffects.network.S2CModPacket;
import tfar.moremobeffects.platform.services.IPlatformHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public <T extends Registry<? extends F>,F> void registerAll(Class<?> clazz, T registry, Class<? extends F> filter) {
        List<Pair<ResourceLocation, Supplier<Object>>> list = MoreMobEffectsForge.registerLater.computeIfAbsent(registry, k -> new ArrayList<>());
        for (Field field : clazz.getFields()) {
            MappedRegistry<? extends F> forgeRegistry = (MappedRegistry<? extends F>) registry;
            forgeRegistry.unfreeze();
            try {
                Object o = field.get(null);
                if (filter.isInstance(o)) {
                    list.add(Pair.of(new ResourceLocation(MoreMobEffects.MOD_ID,field.getName().toLowerCase(Locale.ROOT)),() -> o));
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }

    @Override
    public Attribute getCriticalHitRate() {
        return ALObjects.Attributes.CRIT_CHANCE.get();
    }

    @Override
    public Attribute getCriticalHitDamage() {
        return ALObjects.Attributes.CRIT_DAMAGE.get();
    }

    @Override
    public Attribute getArrowDrawSpeed() {
        return ALObjects.Attributes.DRAW_SPEED.get();
    }

    @Override
    public Attribute getSpellPower() {
        return AttributeRegistry.SPELL_POWER.get();
    }

    @Override
    public Attribute getSpellCooldownReduction() {
        return AttributeRegistry.COOLDOWN_REDUCTION.get();
    }

    public Attribute getSpellCastTimeReduction() {
        return AttributeRegistry.CAST_TIME_REDUCTION.get();
    }

    @Override
    public Attribute getEnderSpellPower() {
        return AttributeRegistry.ENDER_SPELL_POWER.get();
    }

    @Override
    public Attribute getLifeSteal() {
        return ALObjects.Attributes.LIFE_STEAL.get();
    }

    @Override
    public Attribute getArmorPiercing() {
        return ALObjects.Attributes.ARMOR_PIERCE.get();
    }

    @Override
    public Attribute getOverheal() {
        return ALObjects.Attributes.OVERHEAL.get();
    }

    @Override
    public Attribute getSummonDamage() {
        return AttributeRegistry.SUMMON_DAMAGE.get();
    }

    @Override
    public MobEffect getBleeding() {
        return ALObjects.MobEffects.BLEEDING.get();
    }

    @Override
    public MobEffect getBlight() {
        return MobEffectRegistry.BLIGHT.get();
    }

    @Override
    public Attribute getMaxMana() {
        return AttributeRegistry.MAX_MANA.get();
    }

    @Override
    public Attribute getManaRegen() {
        return AttributeRegistry.MANA_REGEN.get();
    }

    int i;

    @Override
    public <MSG extends S2CModPacket> void registerClientPacket(Class<MSG> packetLocation, Function<FriendlyByteBuf, MSG> reader) {
        PacketHandlerForge.INSTANCE.registerMessage(i++, packetLocation, MSG::write, reader, PacketHandlerForge.wrapS2C());
    }

    @Override
    public void sendToClient(S2CModPacket packet, ServerPlayer player) {
        PacketHandlerForge.sendToClient(packet,player);
    }
}