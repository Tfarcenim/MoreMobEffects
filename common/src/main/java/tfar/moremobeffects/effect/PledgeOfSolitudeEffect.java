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
import tfar.moremobeffects.platform.Services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PledgeOfSolitudeEffect extends TickingMobEffect {
    public PledgeOfSolitudeEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1,uuid,() -> Set.of(Services.PLATFORM.getCriticalHitDamage(),Services.PLATFORM.getCriticalHitRate()));
    }

    public static final UUID uuid = MoreMobEffects.make(MoreMobEffects.id("pledge_of_solitude"));

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living.level() instanceof ServerLevel level) {
            int nearbyPlayers = 0;
            Team team = living.getTeam();
            if (team != null) {
                double maxDistSqr = ModConfig.Server.pledge_of_solitude_max_dist.get() * ModConfig.Server.pledge_of_solitude_max_dist.get();
                List<ServerPlayer> nearby = level.getPlayers(player1 -> {
                    Team team1 = player1.getTeam();
                    return living != player1 && team.isAlliedTo(team1) && living.distanceToSqr(player1) < maxDistSqr;
                });
                nearbyPlayers += nearby.size();
            }
            if (nearbyPlayers == 0) {
                AttributeInstance instance = living.getAttribute(Services.PLATFORM.getCriticalHitDamage());
                if (instance != null) {
                    double rBoost = (amplifier + 1) * ModConfig.Server.pledge_of_solitude_crit_damage.get();
                    MoreMobEffects.addModifierAvoidUpdates(new AttributeModifier(uuid, "pledge of solitude", rBoost,
                            AttributeModifier.Operation.MULTIPLY_TOTAL), instance);
                }

                AttributeInstance critDamageInstance = living.getAttribute(Services.PLATFORM.getCriticalHitRate());
                if (critDamageInstance != null) {
                    critDamageInstance.removeModifier(uuid);
                }

            } else {
                AttributeInstance instance = living.getAttribute(Services.PLATFORM.getCriticalHitRate());
                if (instance != null) {
                    MoreMobEffects.addModifierAvoidUpdates(new AttributeModifier(uuid, "pledge of solitude", -ModConfig.Server.pledge_of_solitude_crowded.get(),
                            AttributeModifier.Operation.ADDITION), instance);
                }

                AttributeInstance critDamageInstance = living.getAttribute(Services.PLATFORM.getCriticalHitDamage());
                if (critDamageInstance != null) {
                    critDamageInstance.removeModifier(uuid);
                }
            }
        }
    }
}
//Gain x% attributeslib:crit_damage when no team member is within x blocks of you. 
//“Team Member” must be a player. Minions/mobs should not count. 
//Otherwise, lose 0.2 attributeslib:crit_rate. 
//The debuff should not increase per level.