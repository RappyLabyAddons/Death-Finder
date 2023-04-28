package com.rappytv.deathfinder;

import com.rappytv.deathfinder.commands.BackCommand;
import com.rappytv.deathfinder.commands.CoordsCommand;
import com.rappytv.deathfinder.util.Location;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class DeathFinderAddon extends LabyAddon<DeathFinderConfig> {

    public final static String prefix = "§5§lDF §8» §7";
    public static Location death;
    private static DeathFinderAddon instance;

    @Override
    protected void enable() {
        instance = this; // some static abuse lol

        registerSettingCategory();
        registerCommand(new BackCommand(this));
        registerCommand(new CoordsCommand(this));
    }

    @Override
    protected Class<? extends DeathFinderConfig> configurationClass() {
        return DeathFinderConfig.class;
    }

    public static DeathFinderAddon get() {
        return instance;
    }
}
