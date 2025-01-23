package tfar.moremobeffects.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.scores.Team;
import tfar.moremobeffects.ModConfig;
import tfar.moremobeffects.MoreMobEffects;
import tfar.moremobeffects.init.ModAttributes;
import tfar.moremobeffects.platform.Services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PledgeOfUnityEffect extends TickingMobEffect {
    public PledgeOfUnityEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1, () -> Set.of(ModAttributes.RESISTANCE,Services.PLATFORM.getHealingReceived()));
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living.level() instanceof ServerLevel level) {
            int nearbyPlayers = 0;
            Team team = living.getTeam();
            if (team != null) {
                double maxDistSqr = ModConfig.Server.pledge_of_unity_max_dist.get() * ModConfig.Server.pledge_of_unity_max_dist.get();
                List<ServerPlayer> nearby = level.getPlayers(player1 -> {
                    Team team1 = player1.getTeam();
                    return living != player1 && team.isAlliedTo(team1) && living.distanceToSqr(player1) < maxDistSqr;
                });
                nearbyPlayers += nearby.size();
            }
            AttributeInstance instance = living.getAttribute(ModAttributes.RESISTANCE);
            if (nearbyPlayers > 0) {

                double rBoost = (amplifier + 1) * ModConfig.Server.pledge_of_unity_resistance.get();

                if (instance != null) {
                    MoreMobEffects.addModifierAvoidUpdates(new AttributeModifier(getUuid(), "pledge of unity", rBoost,
                            AttributeModifier.Operation.ADDITION), instance);
                }

                AttributeInstance instanceHeal = living.getAttribute(Services.PLATFORM.getHealingReceived());
                if (instanceHeal != null) {
                    MoreMobEffects.addModifierAvoidUpdates(new AttributeModifier(getUuid(), "pledge of unity", (amplifier + 1) * ModConfig.Server.pledge_of_unity_healing_received.get(),
                            AttributeModifier.Operation.ADDITION), instanceHeal);
                }
            } else {
                if (instance != null) {
                    MoreMobEffects.addModifierAvoidUpdates(new AttributeModifier(getUuid(), "pledge of unity", -ModConfig.Server.pledge_of_unity_lonely.get(),
                            AttributeModifier.Operation.ADDITION), instance);
                }
                AttributeInstance instanceHeal = living.getAttribute(Services.PLATFORM.getHealingReceived());
                if (instanceHeal != null) {
                    instanceHeal.removeModifier(getUuid());
                }
            }
        }
    }
}
//Gain a flat x attributeslib:healing_received and  moremobeffects:resistance per level when a team member is within x blocks of you.
//“Team Member” must be a player. Minions/mobs should not count.
//Otherwise, lose a flat 0.1 moremobeffects:resistance.
//Debuff should not increase per level.