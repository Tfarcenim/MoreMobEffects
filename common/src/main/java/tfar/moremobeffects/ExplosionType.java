package tfar.moremobeffects;

import com.mojang.datafixers.util.Function10;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import tfar.moremobeffects.platform.Services;

public record ExplosionType<E extends Explosion>(Function10<Level,Entity,DamageSource,ExplosionDamageCalculator, Double, Double, Double, Float, Boolean, Explosion.BlockInteraction,E> function10) {
    public static final ExplosionType<MagicalFuseExplosion> MAGICAL_FUSE = new ExplosionType<>(MagicalFuseExplosion::new);


    public static <E extends Explosion> E customExplode(Level level,@Nullable Entity pSource, double pX, double pY, double pZ, float pRadius, Level.ExplosionInteraction pExplosionInteraction, ExplosionType<E> type) {
        return customExplode(level,pSource, null, null, pX, pY, pZ, pRadius, false, pExplosionInteraction,type);
    }

    public static <E extends Explosion> E customExplode(Level level, @Nullable Entity pSource, @Nullable DamageSource pDamageSource,
                                                        @Nullable ExplosionDamageCalculator pDamageCalculator, double pX, double pY, double pZ,
                                                        float pRadius, boolean pFire, Level.ExplosionInteraction pExplosionInteraction, ExplosionType<E> type) {
        return customExplode(level,pSource,pDamageSource,pDamageCalculator, pX, pY, pZ, pRadius,pFire, pExplosionInteraction,true,type);
    }

    public static <E extends Explosion> E customExplode(Level level, @Nullable Entity pSource, @Nullable DamageSource pDamageSource,
                                                           @Nullable ExplosionDamageCalculator pDamageCalculator, double pX, double pY, double pZ,
                                                           float pRadius, boolean pFire, Level.ExplosionInteraction pExplosionInteraction, boolean pSpawnParticles, ExplosionType<E> type) {
        Explosion.BlockInteraction explosion$blockinteraction = switch (pExplosionInteraction) {
            case NONE -> Explosion.BlockInteraction.KEEP;
            case BLOCK -> getDestroyType(level, GameRules.RULE_BLOCK_EXPLOSION_DROP_DECAY);
            case MOB -> Services.PLATFORM.getMobGriefingEvent(level, pSource) ? getDestroyType(level, GameRules.RULE_MOB_EXPLOSION_DROP_DECAY) : Explosion.BlockInteraction.KEEP;
            case TNT -> getDestroyType(level, GameRules.RULE_TNT_EXPLOSION_DROP_DECAY);
        };
        E explosion = type.function10.apply(level, pSource, pDamageSource, pDamageCalculator, pX, pY, pZ, pRadius, pFire, explosion$blockinteraction);
        if (Services.PLATFORM.onExplosionStart(level, explosion)) return explosion;
        explosion.explode();
        explosion.finalizeExplosion(pSpawnParticles);


        if (level instanceof ServerLevel serverLevel) {
            if (!explosion.interactsWithBlocks()) {
                explosion.clearToBlow();
            }

            for (ServerPlayer serverplayer : serverLevel.getPlayers(serverPlayer -> true)) {
                if (serverplayer.distanceToSqr(pX, pY, pZ) < 4096.0D) {
                    serverplayer.connection.send(new ClientboundExplodePacket(pX, pY, pZ, pRadius, explosion.getToBlow(), explosion.getHitPlayers().get(serverplayer)));
                }
            }
        }
        return explosion;
    }

    private static Explosion.BlockInteraction getDestroyType(Level level, GameRules.Key<GameRules.BooleanValue> pGameRule) {
        return level.getGameRules().getBoolean(pGameRule) ? Explosion.BlockInteraction.DESTROY_WITH_DECAY : Explosion.BlockInteraction.DESTROY;
    }
}
