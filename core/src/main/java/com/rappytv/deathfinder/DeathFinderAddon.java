package com.rappytv.deathfinder;

import com.rappytv.deathfinder.commands.BackCommand;
import com.rappytv.deathfinder.commands.CoordsCommand;
import com.rappytv.deathfinder.listeners.DeathListener;
import com.rappytv.deathfinder.util.Location;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.Style;
import net.labymod.api.client.component.format.TextDecoration;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class DeathFinderAddon extends LabyAddon<DeathFinderConfig> {

    public final static Component prefix = Component
        .text("DF ", Style.builder().color(NamedTextColor.DARK_PURPLE).decorate(TextDecoration.BOLD).build())
        .append(Component.text("» ", NamedTextColor.DARK_GRAY));

    private static Location deathLocation;
    private static DeathFinderAddon instance;

    @Override
    protected void enable() {
        instance = this; // some static abuse lol

        registerSettingCategory();
        registerCommand(new BackCommand(this));
        registerCommand(new CoordsCommand(this));
        registerListener(new DeathListener(this));
    }

    @Override
    protected Class<? extends DeathFinderConfig> configurationClass() {
        return DeathFinderConfig.class;
    }

    public static DeathFinderAddon get() {
        return instance;
    }

    public static void setDeathLocation(Location value) {
        deathLocation = value;
    }

    public static Location getDeathLocation() {
        return deathLocation;
    }
}
