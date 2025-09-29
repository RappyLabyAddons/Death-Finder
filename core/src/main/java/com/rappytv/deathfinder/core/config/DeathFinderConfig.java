package com.rappytv.deathfinder.core.config;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ModRequirement;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingRequires;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@SpriteTexture("settings")
public class DeathFinderConfig extends AddonConfig {

    @SpriteSlot
    @SwitchSetting(hotkey = true)
    private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

    @SpriteSlot(size = 64, y = 1, page = 1)
    @ModRequirement(namespace = "labyswaypoints")
    private final WaypointSubconfig waypoints = new WaypointSubconfig();

    @SpriteSlot(size = 64, page = 1)
    @SettingSection(value = "autoRespawn", center = true)
    @SwitchSetting
    private final ConfigProperty<Boolean> autoRespawn = new ConfigProperty<>(false);

    @SpriteSlot(x = 4)
    @SettingRequires("autoRespawn")
    @SwitchSetting
    private final ConfigProperty<Boolean> autoHardcoreRespawn = new ConfigProperty<>(true);

    @SpriteSlot(size = 64, x = 1, page = 1)
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

    public ConfigProperty<Boolean> autoRespawn() {
        return this.autoRespawn;
    }

    public ConfigProperty<Boolean> autoHardcoreRespawn() {
        return this.autoHardcoreRespawn;
    }

    public ConfigProperty<Integer> autoRespawnDelay() {
        return this.autoRespawnDelay;
    }
}
