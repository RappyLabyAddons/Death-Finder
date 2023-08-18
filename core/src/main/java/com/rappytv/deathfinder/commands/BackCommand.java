package com.rappytv.deathfinder.commands;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.DeathFinderConfig;
import com.rappytv.deathfinder.util.Location;
import com.rappytv.deathfinder.util.Util;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;

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

        if(DeathFinderAddon.getDeathLocation() == null) {
            Util.msg(Component.translatable("deathfinder.messages.noSavedPoint", NamedTextColor.RED));
            return true;
        }
        Location death = DeathFinderAddon.getDeathLocation();
        Laby.references().chatExecutor().chat(
            "/tp @p " + death.getX() + " " + death.getY() + " " + death.getZ() + " " + death.getYaw() + " " + death.getPitch(),
            false
        );
        return true;
    }
}
