package com.rappytv.deathfinder.commands;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.DeathFinderConfig;
import com.rappytv.deathfinder.util.Location;
import com.rappytv.deathfinder.util.Util;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;

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
            Util.msg(Component.translatable("deathfinder.messages.noSavedPoint", NamedTextColor.RED));
            return true;
        }
        Location death = DeathFinderAddon.getDeathLocation();
        Util.msg(
            Component.translatable("deathfinder.messages.deathPoint", NamedTextColor.YELLOW),
            Component.text("X: ", NamedTextColor.GREEN).append(Component.text(String.format(java.util.Locale.US,"%.2f", death.getX()), NamedTextColor.AQUA)),
            Component.text("Y: ", NamedTextColor.GREEN).append(Component.text(String.format(java.util.Locale.US,"%.2f", death.getY()), NamedTextColor.AQUA)),
            Component.text("Z: ", NamedTextColor.GREEN).append(Component.text(String.format(java.util.Locale.US,"%.2f", death.getZ()), NamedTextColor.AQUA)),
            Component.text("Yaw: ", NamedTextColor.GREEN).append(Component.text(String.format(java.util.Locale.US,"%.2f", death.getYaw()), NamedTextColor.AQUA)),
            Component.text("Pitch: ", NamedTextColor.GREEN).append(Component.text(String.format(java.util.Locale.US,"%.2f", death.getPitch()), NamedTextColor.AQUA))
        );
        return true;
    }
}
