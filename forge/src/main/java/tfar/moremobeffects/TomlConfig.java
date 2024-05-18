package tfar.moremobeffects;

import net.minecraftforge.common.ForgeConfigSpec;

public class TomlConfig {
    public static ForgeConfigSpec.DoubleValue vulnerable;
    public static ForgeConfigSpec.DoubleValue exposed;

    public TomlConfig(ForgeConfigSpec.Builder builder) {
        builder.push("server");
        vulnerable = builder.
                comment("Damage multiplier of vulnerable")
                .defineInRange("vulnerable", .05, 0, Double.MAX_VALUE);
        exposed = builder.
                comment("Damage multiplier of exposed")
                .defineInRange("exposed", .05, 0, Double.MAX_VALUE);
        builder.pop();
    }
}
