package tfar.moremobeffects.platform;

import tfar.moremobeffects.TomlConfig;

public class ForgeConfig implements Config {
    @Override
    public double exposed() {
        return TomlConfig.exposed.get();
    }

    @Override
    public double vulnerable() {
        return TomlConfig.vulnerable.get();
    }
}
