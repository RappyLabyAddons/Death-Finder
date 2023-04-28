package com.rappytv.back;

import com.rappytv.back.commands.BackCommand;
import com.rappytv.back.commands.CoordsCommand;
import com.rappytv.back.util.Location;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class BackAddon extends LabyAddon<BackConfig> {

    public final static String prefix = "§eBack §8» §7";
    public static Location death;
    private static BackAddon instance;

    @Override
    protected void enable() {
        instance = this; // some static abuse lol

        registerSettingCategory();
        registerCommand(new BackCommand(this));
        registerCommand(new CoordsCommand(this));
    }

    @Override
    protected Class<? extends BackConfig> configurationClass() {
        return BackConfig.class;
    }

    public static BackAddon get() {
        return instance;
    }
}
