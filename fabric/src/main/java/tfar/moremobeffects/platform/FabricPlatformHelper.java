package tfar.moremobeffects.platform;

import net.minecraft.core.Registry;
import tfar.moremobeffects.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

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
    public Config getConfig() {
        return null;
    }
}
