package com.rappytv.deathfinder.commands;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.DeathFinderConfig;
import com.rappytv.deathfinder.util.Location;
import com.rappytv.deathfinder.util.Util;
import net.labymod.api.client.chat.command.Command;

public class CoordsCommand extends Command {

    private final DeathFinderConfig config;

    public CoordsCommand(DeathFinderAddon addon) {
        super("coords");
        this.config = addon.configuration();
    }

    @Override
    public boolean execute(String prefix, String[] arguments) {
        if(!config.coordsCommand().get())
            return false;

        if(DeathFinderAddon.getDeathLocation() == null) {
            Util.msg("§c" + Util.getTranslation("deathfinder.messages.noSavedPoint"));
            return true;
        }
        Location death = DeathFinderAddon.getDeathLocation();
        Util.msg(
            "§e" + Util.getTranslation("deathfinder.messages.deathPoint",
                "§aX: §b" + String.format(java.util.Locale.US,"%.2f", death.getX()),
                    "§aY: §b" + String.format(java.util.Locale.US,"%.2f", death.getY()),
                    "§aZ: §b" + String.format(java.util.Locale.US,"%.2f", death.getZ()),
                    "§aYaw: §b" + String.format(java.util.Locale.US,"%.2f", death.getYaw()),
                    "§aPitch: §b" + String.format(java.util.Locale.US,"%.2f", death.getPitch())
            )
        );
        return true;
    }
}
