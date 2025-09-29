package com.rappytv.deathfinder.core.config;

import com.rappytv.deathfinder.api.util.WaypointType;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.IntroducedIn;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.util.Color;

@SpriteTexture("settings")
public class WaypointSubconfig extends Config {

    @IntroducedIn(namespace = "deathfinder", value = "1.1.1")
    @SpriteSlot(x = 1)
    @TextFieldSetting
    private final ConfigProperty<String> title = new ConfigProperty<>("Death {date}");

    @IntroducedIn(namespace = "deathfinder", value = "1.1.1")
    @SpriteSlot(x = 2)
    @DropdownSetting
    private final ConfigProperty<WaypointType> type = new ConfigProperty<>(WaypointType.PERMANENT);

    @IntroducedIn(namespace = "deathfinder", value = "1.1.1")
    @SpriteSlot(x = 3)
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
