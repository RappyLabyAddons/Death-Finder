package com.rappytv.back.commands;

import com.rappytv.back.BackAddon;
import com.rappytv.back.BackConfig;
import com.rappytv.back.Location;
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
            displayMessage(BackAddon.prefix + "§c" + BackAddon.getTranslation("back.messages.noSavedPoint"));
            return true;
        }
        Location death = BackAddon.death;
        displayMessage(
            BackAddon.prefix +
                BackAddon.getTranslation("back.messages.deathPoint",
                    "§aX: §b" + death.getX(),
                    "§aY: §b" + death.getY(),
                    "§aZ: §b" + death.getZ(),
                    "§aYaw: §b" + death.getYaw(),
                    "§aPitch: §b" + death.getPitch()
                )
        );
        return true;
    }
}
