package com.rappytv.deathfinder.core.config;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.ModRequirement;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@ConfigName("settings")
public class DeathFinderConfig extends AddonConfig {

    @SwitchSetting(hotkey = true)
    private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

    @ModRequirement(namespace = "labyswaypoints")
    private final WaypointSubconfig waypoints = new WaypointSubconfig();

    @Override
    public ConfigProperty<Boolean> enabled() {
        return this.enabled;
    }

    public WaypointSubconfig waypoints() {
        return this.waypoints;
    }
}
