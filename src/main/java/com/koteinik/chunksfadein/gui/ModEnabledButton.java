package com.koteinik.chunksfadein.gui;

import com.koteinik.chunksfadein.config.Config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ModEnabledButton extends ButtonWidget {
    private static final int buttonW = 150;
    private static final int buttonH = 20;

    public ModEnabledButton(GameOptionsScreen parent, int parentW, int parentH) {
        super(parentW / 2 - buttonW - 4, parentH / 2 - buttonH / 2 - 28 * 3,
                buttonW, buttonH, createText(),
                new PressAction() {
                    @Override
                    public void onPress(ButtonWidget button) {
                        Config.setBoolean(Config.MOD_ENABLED_KEY, !Config.isModEnabled);
                        button.setMessage(createText());
                    }
                }, DEFAULT_NARRATION_SUPPLIER);
        if (!needToDisable())
            this.setTooltip(Tooltip.of(Text.translatable("chunkfadein.gui.mod-enabled.tooltip")));
        this.active = needToDisable();
    }

    private static Text createText() {
        Boolean isModEnabled = Config.isModEnabled;

        String color = isModEnabled ? "§2" : "§c";
        String enabledText = isModEnabled ? (Text.translatable("chunksfadein.gui.true").getString()) : (Text.translatable("chunksfadein.gui.false").getString());

        return Text.of((Text.translatable("chunksfadein.gui.mod-enabled").getString()) + ": " + color + enabledText);
    }

    private static boolean needToDisable() {
        return MinecraftClient.getInstance().getGame().getCurrentSession() == null;
    }
}