package tfar.moremobeffects.platform.services;

import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import tfar.moremobeffects.network.S2CModPacket;
import tfar.moremobeffects.platform.MultiloaderConfig;

import java.util.function.Function;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    <T extends Registry<? extends F>,F> void registerAll(Class<?> clazz, T registry, Class<? extends F> filter);

    MultiloaderConfig getConfig();

    Attribute getCriticalHitRate();
    Attribute getCriticalHitDamage();
    Attribute getArrowDrawSpeed();
    Attribute getSpellPower();
    Attribute getSpellCooldownReduction();
    Attribute getSpellCastTimeReduction();
    Attribute getEnderSpellPower();


    Attribute getLifeSteal();

    Attribute getArmorPiercing();

    Attribute getOverheal();

    Attribute getSummonDamage();
    MobEffect getBleeding();

    MobEffect getBlight();

    Attribute getMaxMana();
    Attribute getManaRegen();

    <MSG extends S2CModPacket> void registerClientPacket(Class<MSG> packetLocation, Function<FriendlyByteBuf,MSG> reader);
    void sendToClient(S2CModPacket packet, ServerPlayer player);
}