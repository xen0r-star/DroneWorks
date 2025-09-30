package io.github.xen0r_star.droneworks.network;

import io.github.xen0r_star.droneworks.Main;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record PlayButtonPayload(BlockPos stationPos, boolean play) implements CustomPayload {
    public static final Id<PlayButtonPayload> ID = new Id<>(Identifier.of(Main.MOD_ID, "play_button"));
    public static final PacketCodec<RegistryByteBuf, PlayButtonPayload> CODEC =
        PacketCodec.tuple(
            BlockPos.PACKET_CODEC, PlayButtonPayload::stationPos,
            PacketCodecs.BOOLEAN, PlayButtonPayload::play,
            PlayButtonPayload::new
        );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}