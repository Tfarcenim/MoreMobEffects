package tfar.moremobeffects.platform;

import tfar.moremobeffects.TomlConfig;

import java.util.HashMap;
import java.util.Map;

public class ForgeConfig implements Config {

    public final Map<String,ForgeConfigEntry<?>> configEntryMap = new HashMap<>();


    @Override
    public void init() {
        configEntryMap.put("exposed",new ForgeDoubleConfigEntry(TomlConfig.exposed));
        configEntryMap.put("vulnerable",new ForgeDoubleConfigEntry(TomlConfig.vulnerable));

        configEntryMap.put("archery",new ForgeDoubleConfigEntry(TomlConfig.exposed));
        configEntryMap.put("aiming",new ForgeDoubleConfigEntry(TomlConfig.vulnerable));
        configEntryMap.put("marksman",new ForgeDoubleConfigEntry(TomlConfig.marksman));
    }

    @Override
    public ConfigEntry getConfigEntry(String id) {
        return configEntryMap.get(id);
    }
}
