package com.rappytv.back.commands;

import com.rappytv.back.BackAddon;
import com.rappytv.back.BackConfig;
import com.rappytv.back.Location;
import net.labymod.api.client.chat.command.Command;

public class BackCommand extends Command {

    private final BackConfig config;

    public BackCommand(BackAddon addon) {
        super("back");
        this.config = addon.configuration();
    }

    @Override
    public boolean execute(String prefix, String[] arguments) {
        if(!config.backCommand().get())
            return false;

        if(BackAddon.death == null) {
            displayMessage(BackAddon.prefix + "§c" + BackAddon.getTranslation("back.messages.noSavedPoint"));
            return true;
        }
        Location death = BackAddon.death;
        sendMessage("/tp @p " + death.getX() + " " + death.getY() + " " + death.getZ() + " " + death.getYaw() + " " + death.getPitch());
        return true;
    }
}
