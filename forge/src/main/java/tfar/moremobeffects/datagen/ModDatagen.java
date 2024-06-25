package tfar.moremobeffects.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import tfar.moremobeffects.datagen.assets.MobEffectTagsProvider;
import tfar.moremobeffects.datagen.assets.ModDamageTypeTagsProvider;
import tfar.moremobeffects.datagen.assets.ModLangProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatagen {

    public static void start(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper helper = e.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = e.getLookupProvider();


        if (e.includeClient()) {
            generator.addProvider(true,new ModLangProvider(output));
        }
        if (e.includeServer()) {
            generator.addProvider(true,new MobEffectTagsProvider(output,lookupProvider,helper));
            generator.addProvider(true,new ModDamageTypeTagsProvider(output,lookupProvider,helper));
        }
    }

}
