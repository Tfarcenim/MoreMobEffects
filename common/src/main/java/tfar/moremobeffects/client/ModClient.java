package tfar.moremobeffects.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Abilities;
import tfar.moremobeffects.network.S2CPlayerAbilitiesPacket;

public class ModClient {

    public static void handleAbilities(S2CPlayerAbilitiesPacket packet) {
        Abilities abilities = Minecraft.getInstance().player.getAbilities();
        abilities.invulnerable = packet.invulnerable;
        abilities.flying = packet.isFlying;
        abilities.mayfly = packet.canFly;
        abilities.instabuild = packet.instabuild;
        abilities.mayBuild = packet.canBuild;
        abilities.setFlyingSpeed(packet.flyingSpeed);
        abilities.setWalkingSpeed(packet.walkingSpeed);
    }
}
