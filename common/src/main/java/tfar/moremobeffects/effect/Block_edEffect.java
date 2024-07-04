package tfar.moremobeffects.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import tfar.moremobeffects.network.S2CPlayerAbilitiesPacket;
import tfar.moremobeffects.platform.Services;

public class Block_edEffect extends MobEffect {
    public Block_edEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1);
    }

    @Override
    public void addAttributeModifiers(LivingEntity living, AttributeMap $$1, int $$2) {
        super.addAttributeModifiers(living, $$1, $$2);
        if (living instanceof ServerPlayer player) {
            player.getAbilities().mayBuild = false;
            Services.PLATFORM.sendToClient(new S2CPlayerAbilitiesPacket(player.getAbilities()),player);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity living, AttributeMap $$1, int $$2) {
        super.removeAttributeModifiers(living, $$1, $$2);
        if (living instanceof ServerPlayer player) {
            player.getAbilities().mayBuild = !player.server.getDefaultGameType().isBlockPlacingRestricted();
            Services.PLATFORM.sendToClient(new S2CPlayerAbilitiesPacket(player.getAbilities()),player);
        }
    }
}
