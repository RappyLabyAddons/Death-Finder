package com.rappytv.deathfinder.commands;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.DeathFinderConfig;
import com.rappytv.deathfinder.util.Location;
import com.rappytv.deathfinder.util.Util;
import net.labymod.api.client.chat.command.Command;

public class BackCommand extends Command {

    private final DeathFinderConfig config;

    public BackCommand(DeathFinderAddon addon) {
        super("back");
        this.config = addon.configuration();
    }

    @Override
    public boolean execute(String prefix, String[] arguments) {
        if(!config.backCommand().get())
            return false;

        if(DeathFinderAddon.death == null) {
            Util.msg("§c" + Util.getTranslation("back.messages.noSavedPoint"));
            return true;
        }
        Location death = DeathFinderAddon.death;
        sendMessage("/tp @p " + death.getX() + " " + death.getY() + " " + death.getZ() + " " + death.getYaw() + " " + death.getPitch());
        return true;
    }
}
