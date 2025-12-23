package com.xkingdark.networkProtocolError;

import com.mojang.logging.LogUtils;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;

import net.minecraft.network.protocol.Packet;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommonPacketListenerImpl.class)
public class NetworkProtocolError {
    @Unique
    private static final Logger LOGGER = LogUtils.getLogger();

    @Inject(method = "onPacketError", at = @At("HEAD"), cancellable = true)
    private void onPacketError(Packet<?> packet, Exception exception, CallbackInfo ci) {
        LOGGER.warn("Failed to handle packet {}, though disconnection was cancelled.", packet, exception);
        ci.cancel();
    };
};
