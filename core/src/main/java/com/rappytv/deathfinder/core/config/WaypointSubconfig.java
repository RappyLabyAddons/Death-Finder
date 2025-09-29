package com.rappytv.deathfinder.core.config;

import com.rappytv.deathfinder.api.util.WaypointType;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.util.Color;

public class WaypointSubconfig extends Config {

    @TextFieldSetting
    private final ConfigProperty<String> title = new ConfigProperty<>("Death {date}");

    @DropdownSetting
    private final ConfigProperty<WaypointType> type = new ConfigProperty<>(WaypointType.PERMANENT);

    @ColorPickerSetting(alpha = true, chroma = true, chromaSpeed = false)
    private final ConfigProperty<Color> color = new ConfigProperty<>(Color.of("#5e17eb"));

    public ConfigProperty<String> title() {
        return this.title;
    }

    public ConfigProperty<WaypointType> type() {
        return this.type;
    }

    public ConfigProperty<Color> color() {
        return this.color;
    }

}
