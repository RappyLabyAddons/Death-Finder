package com.rappytv.deathfinder.core.config;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.ModRequirement;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingRequires;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
public class DeathFinderConfig extends AddonConfig {

    @SwitchSetting(hotkey = true)
    private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

    @ModRequirement(namespace = "labyswaypoints")
    private final WaypointSubconfig waypoints = new WaypointSubconfig();

    @SettingSection(value = "autoRespawn", center = true)
    @SwitchSetting
    private final ConfigProperty<Boolean> autoRespawn = new ConfigProperty<>(false);

    @SettingRequires("autoRespawn")
    @SwitchSetting
    private final ConfigProperty<Boolean> autoHardcoreRespawn = new ConfigProperty<>(true);

    @SettingRequires("autoRespawn")
    @SliderSetting(min = 0, max = 30)
    private final ConfigProperty<Integer> autoRespawnDelay = new ConfigProperty<>(5);

    @Override
    public ConfigProperty<Boolean> enabled() {
        return this.enabled;
    }

    public WaypointSubconfig waypoints() {
        return this.waypoints;
    }

    public ConfigProperty<Boolean> autoHardcoreRespawn() {
        return this.autoHardcoreRespawn;
    }

    public ConfigProperty<Boolean> autoRespawn() {
        return this.autoRespawn;
    }

    public ConfigProperty<Integer> autoRespawnDelay() {
        return this.autoRespawnDelay;
    }
}
