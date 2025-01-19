package tfar.moremobeffects.platform;

import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import tfar.moremobeffects.network.S2CModPacket;
import tfar.moremobeffects.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

import java.util.function.Function;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public <T extends Registry<? extends F>, F> void registerAll(Class<?> clazz, T registry, Class<? extends F> filter) {

    }

    @Override
    public MultiloaderConfig getConfig() {
        return null;
    }

    @Override
    public Attribute getCriticalHitRate() {
        return null;
    }

    @Override
    public Attribute getCriticalHitDamage() {
        return null;
    }

    @Override
    public Attribute getArrowDrawSpeed() {
        return null;
    }

    @Override
    public Attribute getSpellPower() {
        return null;
    }

    @Override
    public Attribute getSpellCooldownReduction() {
        return null;
    }

    @Override
    public Attribute getSpellCastTimeReduction() {
        return null;
    }

    @Override
    public Attribute getEnderSpellPower() {
        return null;
    }

    @Override
    public Attribute getLifeSteal() {
        return null;
    }

    @Override
    public Attribute getArmorPiercing() {
        return null;
    }

    @Override
    public Attribute getOverheal() {
        return null;
    }

    @Override
    public Attribute getSummonDamage() {
        return null;
    }

    @Override
    public MobEffect getBleeding() {
        return null;
    }

    @Override
    public MobEffect getBlight() {
        return null;
    }

    @Override
    public Attribute getMaxMana() {
        return null;
    }

    @Override
    public Attribute getManaRegen() {
        return null;
    }

    @Override
    public <MSG extends S2CModPacket> void registerClientPacket(Class<MSG> packetLocation, Function<FriendlyByteBuf, MSG> reader) {

    }

    @Override
    public void sendToClient(S2CModPacket packet, ServerPlayer player) {

    }
}
