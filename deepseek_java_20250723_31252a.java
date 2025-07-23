package com.example.friendcosmetics;

import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PlayerHatRenderer {
    public static final Identifier CHINESE_HAT_TEXTURE = new Identifier("friendcosmetics", "textures/chinese_hat.png");
    
    public static void init() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityRenderer instanceof PlayerEntityRenderer playerEntityRenderer) {
                registrationHelper.register(new ChineseHatFeatureRenderer(playerEntityRenderer));
            }
        });
    }
    
    static class ChineseHatFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
        public ChineseHatFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
            super(context);
        }
        
        @Override
        public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
            if (FriendManager.isFriend(player.getGameProfile().getName())) {
                VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(CHINESE_HAT_TEXTURE));
                
                matrices.push();
                matrices.translate(0.0, -0.25, 0.0);
                matrices.scale(1.1f, 1.1f, 1.1f);
                
                this.getContextModel().head.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
                matrices.pop();
            }
        }
    }
}