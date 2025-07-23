package com.example.friendcosmetics;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class FriendCosmeticsClient implements ClientModInitializer {
    private static KeyBinding openMenuKey;
    
    @Override
    public void onInitializeClient() {
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.friendcosmetics.openmenu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_Z,
            "category.friendcosmetics.main"
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                if (client.player != null) {
                    client.setScreen(new FriendCosmeticsMenu());
                }
            }
        });
        
        PlayerCapeRenderer.init();
        PlayerHatRenderer.init();
    }
}