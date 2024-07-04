package tfar.moremobeffects.network;

import net.minecraft.network.FriendlyByteBuf;

public interface IModPacket {
    void write(FriendlyByteBuf to);

}
