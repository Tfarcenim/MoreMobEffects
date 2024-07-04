package tfar.moremobeffects.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Abilities;
import tfar.moremobeffects.client.ModClient;

public class S2CPlayerAbilitiesPacket implements S2CModPacket {
    private static final int FLAG_INVULNERABLE = 1;
    private static final int FLAG_FLYING = 1 << 1;
    private static final int FLAG_CAN_FLY = 1 << 2;
    private static final int FLAG_INSTABUILD = 1 << 3;
    private static final int FLAG_CAN_BUILD = 1 << 4;

    public final boolean invulnerable;
    public final boolean isFlying;
    public final boolean canFly;
    public final boolean instabuild;
    public final boolean canBuild;
    public final float flyingSpeed;
    public final float walkingSpeed;


    public S2CPlayerAbilitiesPacket(Abilities abilities) {
        this.invulnerable = abilities.invulnerable;
        this.isFlying = abilities.flying;
        this.canFly = abilities.mayfly;
        this.instabuild = abilities.instabuild;
        this.canBuild = abilities.mayBuild;
        this.flyingSpeed = abilities.getFlyingSpeed();
        this.walkingSpeed = abilities.getWalkingSpeed();
    }

    public S2CPlayerAbilitiesPacket(FriendlyByteBuf buf) {
        byte flags = buf.readByte();
        this.invulnerable = (flags & FLAG_INVULNERABLE) != 0;
        this.isFlying = (flags & FLAG_FLYING) != 0;
        this.canFly = (flags & FLAG_CAN_FLY) != 0;
        this.instabuild = (flags & FLAG_INSTABUILD) != 0;
        this.canBuild = (flags & FLAG_CAN_BUILD) != 0;
        this.flyingSpeed = buf.readFloat();
        this.walkingSpeed = buf.readFloat();
    }

    @Override
    public void handleClient() {
        ModClient.handleAbilities(this);
    }

    @Override
    public void write(FriendlyByteBuf to) {
        byte flags = 0;
        if (this.invulnerable) {
            flags = (byte)(flags | FLAG_INVULNERABLE);
        }

        if (this.isFlying) {
            flags = (byte)(flags | FLAG_FLYING);
        }

        if (this.canFly) {
            flags = (byte)(flags | FLAG_CAN_FLY);
        }

        if (this.instabuild) {
            flags = (byte)(flags | FLAG_INSTABUILD);
        }

        if (this.canBuild) {
            flags = (byte) (flags | FLAG_CAN_BUILD);
        }

        to.writeByte(flags);
        to.writeFloat(this.flyingSpeed);
        to.writeFloat(this.walkingSpeed);
    }
}
