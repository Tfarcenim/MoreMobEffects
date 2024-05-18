package tfar.moremobeffects.platform;

import net.minecraftforge.common.ForgeConfigSpec;

public abstract class ForgeConfigEntry<T> implements ConfigEntry {

    protected final ForgeConfigSpec.ConfigValue<T> configValue;

    public ForgeConfigEntry(ForgeConfigSpec.ConfigValue<T> configValue) {

        this.configValue = configValue;
    }

}
