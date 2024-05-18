package tfar.moremobeffects.platform;

import net.minecraftforge.common.ForgeConfigSpec;

public class ForgeDoubleConfigEntry extends ForgeConfigEntry<Double> {
    public ForgeDoubleConfigEntry(ForgeConfigSpec.DoubleValue config) {
        super(config);
    }

    @Override
    public double getAsDouble() {
        return configValue.get();
    }

    @Override
    public int getAsInt() {
        throw new UnsupportedOperationException();
    }
}
