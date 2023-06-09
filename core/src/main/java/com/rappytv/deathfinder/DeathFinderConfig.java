package com.rappytv.deathfinder;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
public class DeathFinderConfig extends AddonConfig {

    @SettingSection("general")
    @SwitchSetting
    private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);
    @SwitchSetting
    private final ConfigProperty<Boolean> saveRotation = new ConfigProperty<>(false);

    @SettingSection("commands")
    @SwitchSetting
    private final ConfigProperty<Boolean> backCommand = new ConfigProperty<>(true);
    @SwitchSetting
    private final ConfigProperty<Boolean> coordsCommand = new ConfigProperty<>(true);

    @Override
    public ConfigProperty<Boolean> enabled() {
        return enabled;
    }
    public ConfigProperty<Boolean> saveRotation() {
        return saveRotation;
    }

    public ConfigProperty<Boolean> backCommand() {
        return backCommand;
    }
    public ConfigProperty<Boolean> coordsCommand() {
        return coordsCommand;
    }
}
