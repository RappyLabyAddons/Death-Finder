package com.rappytv.back.commands;

import com.rappytv.back.BackAddon;
import com.rappytv.back.BackConfig;
import com.rappytv.back.util.Location;
import com.rappytv.back.util.Util;
import net.labymod.api.client.chat.command.Command;

public class CoordsCommand extends Command {

    private final BackConfig config;

    public CoordsCommand(BackAddon addon) {
        super("coords");
        this.config = addon.configuration();
    }

    @Override
    public boolean execute(String prefix, String[] arguments) {
        if(!config.coordsCommand().get())
            return false;

        if(BackAddon.death == null) {
            Util.msg("§c" + Util.getTranslation("back.messages.noSavedPoint"));
            return true;
        }
        Location death = BackAddon.death;
        Util.msg(
            "§e" + Util.getTranslation("back.messages.deathPoint",
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
