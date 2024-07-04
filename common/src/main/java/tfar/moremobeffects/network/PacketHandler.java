package tfar.moremobeffects.network;

import tfar.moremobeffects.platform.Services;

public class PacketHandler {

    public static void registerPackets() {

        registerClientPackets();
    }

    public static void registerClientPackets() {
        Services.PLATFORM.registerClientPacket(S2CPlayerAbilitiesPacket.class,S2CPlayerAbilitiesPacket::new);
    }

}
