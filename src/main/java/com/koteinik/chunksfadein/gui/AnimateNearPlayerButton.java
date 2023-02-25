package com.koteinik.chunksfadein.gui;

import com.koteinik.chunksfadein.config.Config;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class AnimateNearPlayerButton extends ButtonWidget {
    private static final int buttonW = 150;
    private static final int buttonH = 20;

    public AnimateNearPlayerButton(int parentW, int parentH) {
        super(parentW / 2 - buttonW - 4, parentH / 2 - buttonH / 2 + 28 * 2,
                buttonW, buttonH, createText(),
                new PressAction() {
                    @Override
                    public void onPress(ButtonWidget button) {
                        Config.setBoolean(Config.ANIMATE_NEAR_PLAYER_KEY, !Config.animateNearPlayer);
                        button.setMessage(createText());
                    }
                }, DEFAULT_NARRATION_SUPPLIER);
    }

    private static Text createText() {
        Boolean animateNearPlayer = Config.animateNearPlayer;

        String color = animateNearPlayer ? "§2" : "§c";
        String enabledText = animateNearPlayer ? (Text.translatable("chunksfadein.gui.true").getString()) : (Text.translatable("chunksfadein.gui.false").getString());

        return Text.of((Text.translatable("chunksfadein.gui.animate-near-player").getString()) + ": " + color + enabledText);
    }
}
