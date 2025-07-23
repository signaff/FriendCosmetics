package com.example.friendcosmetics;

import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.util.Identifier;

public class PlayerCapeRenderer {
    public static final Identifier FRIEND_CAPE_TEXTURE = new Identifier("friendcosmetics", "textures/cape.png");
    
    public static void init() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityRenderer instanceof PlayerEntityRenderer playerEntityRenderer) {
                registrationHelper.register(new CapeFeatureRenderer<>(playerEntityRenderer, context.getModelLoader()) {
                    @Override
                    protected Identifier getCapeTexture() {
                        if (entityRenderer.getEntity() instanceof AbstractClientPlayerEntity player) {
                            if (FriendManager.isFriend(player.getGameProfile().getName())) {
                                return FRIEND_CAPE_TEXTURE;
                            }
                        }
                        return super.getCapeTexture();
                    }
                });
            }
        });
    }
}