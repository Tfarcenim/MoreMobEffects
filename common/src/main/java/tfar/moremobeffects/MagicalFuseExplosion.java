package tfar.moremobeffects;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagicalFuseExplosion extends Explosion {
    public MagicalFuseExplosion(Level $$0, @Nullable Entity $$1, double $$2, double $$3, double $$4, float $$5, List<BlockPos> $$6) {
        super($$0, $$1, $$2, $$3, $$4, $$5, $$6);
    }

    public MagicalFuseExplosion(Level $$0, @Nullable Entity $$1, double $$2, double $$3, double $$4, float $$5, boolean $$6, BlockInteraction $$7, List<BlockPos> $$8) {
        super($$0, $$1, $$2, $$3, $$4, $$5, $$6, $$7, $$8);
    }

    public MagicalFuseExplosion(Level $$0, @Nullable Entity $$1, double $$2, double $$3, double $$4, float $$5, boolean $$6, BlockInteraction $$7) {
        super($$0, $$1, $$2, $$3, $$4, $$5, $$6, $$7);
    }

    public MagicalFuseExplosion(Level $$0, @Nullable Entity $$1, @Nullable DamageSource $$2, @Nullable ExplosionDamageCalculator $$3, double $$4, double $$5, double $$6, float $$7, boolean $$8, BlockInteraction $$9) {
        super($$0, $$1, $$2, $$3, $$4, $$5, $$6, $$7, $$8, $$9);
    }
}
