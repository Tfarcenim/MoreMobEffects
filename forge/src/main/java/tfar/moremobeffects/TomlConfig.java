package tfar.moremobeffects;

import net.minecraftforge.common.ForgeConfigSpec;

public class TomlConfig {
    public static ForgeConfigSpec.DoubleValue vulnerable;
    public static ForgeConfigSpec.DoubleValue exposed;
    public static ForgeConfigSpec.DoubleValue archery;
    public static ForgeConfigSpec.DoubleValue aiming;
    public static ForgeConfigSpec.DoubleValue marksman;
    public static ForgeConfigSpec.DoubleValue might;
    public static ForgeConfigSpec.DoubleValue strengthened;

    public static ForgeConfigSpec.DoubleValue inspired;
    public static ForgeConfigSpec.DoubleValue precise;

    public static ForgeConfigSpec.DoubleValue brutality;
    public static ForgeConfigSpec.DoubleValue savage;

    public static ForgeConfigSpec.DoubleValue archers_frenzy;
    public static ForgeConfigSpec.DoubleValue hasty;

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

        might = builder.
                comment("Damage multiplier of might")
                .defineInRange("might", .05, 0, Double.MAX_VALUE);
        strengthened = builder.
                comment("Damage multiplier of strength")
                .defineInRange("strength", .05, 0, Double.MAX_VALUE);

        inspired = builder.
                comment("Critical hit chance boost of inspired")
                .defineInRange("might", .05, 0, 100);
        precise = builder.
                comment("Critical hit chance boost of precise")
                .defineInRange("precise", .05, 0, 100);

        brutality = builder.
                comment("Critical hit damage boost of brutality")
                .defineInRange("brutality", .05, 0, Double.MAX_VALUE);
        savage = builder.
                comment("Critical hit damage boost of savage")
                .defineInRange("savage", .05, 0, Double.MAX_VALUE);

        archers_frenzy = builder.
                comment("Draw speed boost of archer's frenzy")
                .defineInRange("archers_frenzy", .05, 0, Double.MAX_VALUE);
        hasty = builder.
                comment("Draw speed boost of hasty")
                .defineInRange("hasty", .05, 0, Double.MAX_VALUE);

        builder.pop();
    }
}
