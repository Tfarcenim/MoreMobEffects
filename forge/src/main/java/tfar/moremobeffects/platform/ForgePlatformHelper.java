package tfar.moremobeffects.platform;

import dev.shadowsoffire.attributeslib.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.commons.lang3.tuple.Pair;
import org.w3c.dom.Attr;
import tfar.moremobeffects.MoreMobEffects;
import tfar.moremobeffects.MoreMobEffectsForge;
import tfar.moremobeffects.platform.services.IPlatformHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class ForgePlatformHelper implements IPlatformHelper {

    final Config config = new ForgeConfig();
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
        List<Pair<ResourceLocation, Supplier<?>>> list = MoreMobEffectsForge.registerLater.computeIfAbsent(registry, k -> new ArrayList<>());
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
    public Config getConfig() {
        return config;
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
    public Attribute getSpellCooldown() {
        return AttributeRegistry.COOLDOWN_REDUCTION.get();
    }

    public Attribute getSpellCastTimeReduction() {
        return AttributeRegistry.CAST_TIME_REDUCTION.get();
    }
}