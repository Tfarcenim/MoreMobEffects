package tfar.moremobeffects.datagen.assets;

import dev.shadowsoffire.attributeslib.api.ALObjects;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import tfar.moremobeffects.ModTags;
import tfar.moremobeffects.MoreMobEffects;
import tfar.moremobeffects.init.ModMobEffects;
import tfar.moremobeffects.platform.Services;

import java.util.concurrent.CompletableFuture;

public class MobEffectTagsProvider extends TagsProvider<MobEffect> {
    public MobEffectTagsProvider(PackOutput pOutput,
                                 CompletableFuture<HolderLookup.Provider> pLookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, Registries.MOB_EFFECT, pLookupProvider, MoreMobEffects.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.SOUL_SCORCHED_REMOVALS).add(from(MobEffects.POISON),from(MobEffects.WITHER),
                        from(MobEffects.MOVEMENT_SLOWDOWN),from(MobEffects.WEAKNESS),
                        from(ModMobEffects.INJURY),from(ModMobEffects.FRACTURED))
                .addOptional(ALObjects.MobEffects.BLEEDING.getId()).addOptional(MobEffectRegistry.BLIGHT.getId());
    }

    protected ResourceKey<MobEffect> from(MobEffect effect) {
        return BuiltInRegistries.MOB_EFFECT.getResourceKey(effect).orElse(null);
    }
}
