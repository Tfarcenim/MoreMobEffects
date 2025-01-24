package tfar.moremobeffects.init;

import tfar.moremobeffects.platform.Services;

public enum ModIntegration {
    alexcaves,
    attributeslib,
    irons_spellbooks;
    public final boolean loaded;
    ModIntegration() {
        loaded = Services.PLATFORM.isModLoaded(name());
    }
}
