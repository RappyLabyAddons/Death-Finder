package com.rappytv.deathfinder.core;

import com.rappytv.deathfinder.core.commands.DeathFinderCommand;
import com.rappytv.deathfinder.core.config.DeathFinderConfig;
import com.rappytv.deathfinder.core.listeners.DeathListener;
import com.rappytv.deathfinder.api.util.DeathLocation;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextDecoration;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class DeathFinderAddon extends LabyAddon<DeathFinderConfig> {

    public final static Component prefix = Component.empty()
        .append(Component.text("DF ").color(NamedTextColor.DARK_PURPLE).decorate(TextDecoration.BOLD))
        .append(Component.text("» ", NamedTextColor.DARK_GRAY));

    private static DeathLocation deathLocation;

    @Override
    protected void enable() {
        this.registerSettingCategory();
        this.registerCommand(new DeathFinderCommand(this));
        this.registerListener(new DeathListener(this));
    }

    @Override
    protected Class<? extends DeathFinderConfig> configurationClass() {
        return DeathFinderConfig.class;
    }

    public static void setDeathLocation(DeathLocation value) {
        deathLocation = value;
    }

    public static DeathLocation getDeathLocation() {
        return deathLocation;
    }
}
