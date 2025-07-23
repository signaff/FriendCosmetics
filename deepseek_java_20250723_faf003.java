package com.example.friendcosmetics;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class FriendCosmeticsMenu extends Screen {
    private TextFieldWidget usernameField;
    
    protected FriendCosmeticsMenu() {
        super(Text.literal("Friend Cosmetics"));
    }
    
    @Override
    protected void init() {
        super.init();
        
        this.usernameField = new TextFieldWidget(
            this.textRenderer,
            this.width / 2 - 100,
            this.height / 2 - 30,
            200, 20,
            Text.literal("Enter friend's username")
        );
        this.addSelectableChild(this.usernameField);
        
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Add Friend"),
            button -> {
                String username = usernameField.getText().trim();
                if (!username.isEmpty()) {
                    FriendManager.addFriend(username);
                    usernameField.setText("");
                }
            })
            .dimensions(this.width / 2 - 100, this.height / 2, 200, 20)
            .build());
        
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Close"),
            button -> this.close())
            .dimensions(this.width / 2 - 100, this.height / 2 + 30, 200, 20)
            .build());
    }
    
    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        this.usernameField.render(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
    }
}