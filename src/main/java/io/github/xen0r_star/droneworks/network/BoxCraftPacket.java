package io.github.xen0r_star.droneworks.network;

import io.github.xen0r_star.droneworks.Main;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;


public record BoxCraftPacket() implements CustomPayload {
    public static final Identifier ID = Identifier.of(Main.MOD_ID, "box_block_packet");
    public static final CustomPayload.Id<BoxCraftPacket> PACKET_ID = new CustomPayload.Id<>(ID);
    public static final PacketCodec<RegistryByteBuf, BoxCraftPacket> CODEC = PacketCodec.unit(new BoxCraftPacket());

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}