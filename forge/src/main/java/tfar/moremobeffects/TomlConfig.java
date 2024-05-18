package tfar.moremobeffects;

import net.minecraftforge.common.ForgeConfigSpec;

public class TomlConfig {
    public static ForgeConfigSpec.DoubleValue vulnerable;
    public static ForgeConfigSpec.DoubleValue exposed;
    public static ForgeConfigSpec.DoubleValue archery;
    public static ForgeConfigSpec.DoubleValue aiming;
    public static ForgeConfigSpec.DoubleValue marksman;

    public TomlConfig(ForgeConfigSpec.Builder builder) {
        builder.push("server");
        vulnerable = builder.
                comment("Damage multiplier of vulnerable")
                .defineInRange("vulnerable", .05, 0, Double.MAX_VALUE);
        exposed = builder.
                comment("Damage multiplier of exposed")
                .defineInRange("exposed", .05, 0, Double.MAX_VALUE);

        archery = builder.
                comment("Damage multiplier of archery")
                .defineInRange("archery", .05, 0, Double.MAX_VALUE);
        aiming = builder.
                comment("Damage multiplier of aiming")
                .defineInRange("aiming", .05, 0, Double.MAX_VALUE);

        marksman = builder.
                comment("Damage multiplier of marksman")
                .defineInRange("marksman", .05, 0, Double.MAX_VALUE);
        builder.pop();
    }
}
