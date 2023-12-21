package com.rappytv.deathfinder.listeners;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.events.DeathEvent;
import com.rappytv.deathfinder.util.Util;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.event.HoverEvent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.Style;
import net.labymod.api.client.component.format.TextDecoration;
import net.labymod.api.event.Subscribe;

public class DeathListener {

    private final DeathFinderAddon addon;

    public DeathListener(DeathFinderAddon addon) {
        this.addon = addon;
    }

    @Subscribe
    public void onDeath(DeathEvent event) {
        if(!addon.configuration().enabled().get()) return;
        boolean backCommand = addon.configuration().backCommand().get();
        boolean coordsCommand = addon.configuration().coordsCommand().get();

        DeathFinderAddon.setDeathLocation(event.location());

        Component info = Component.translatable("deathfinder.messages.savedPoint", NamedTextColor.GREEN);
        if(!backCommand && !coordsCommand) {
            Util.msg(info);
            return;
        }

        Component interactable = Component.empty();
        if(backCommand) interactable.append(
            Component
                .text("[", NamedTextColor.DARK_GRAY)
                .append(Component.text("TP", Style.builder().color(NamedTextColor.RED).decorate(TextDecoration.BOLD).build()))
                .append(Component.text("]", NamedTextColor.DARK_GRAY))
                .hoverEvent(HoverEvent.showText(Component.translatable("deathfinder.hover.teleport", NamedTextColor.GREEN)))
                .clickEvent(ClickEvent.runCommand("/back"))
        );
        if(backCommand && coordsCommand) interactable.append(Component.text(" | ", NamedTextColor.DARK_GRAY));
        if(coordsCommand) interactable.append(
            Component
                .text("[", NamedTextColor.DARK_GRAY)
                .append(Component.text("INFOS", Style.builder().color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD).build()))
                .append(Component.text("]", NamedTextColor.DARK_GRAY))
                .hoverEvent(HoverEvent.showText(Component.translatable("deathfinder.hover.show", NamedTextColor.GREEN)))
                .clickEvent(ClickEvent.runCommand("/coords"))
        );
        Util.msg(info, interactable);
    }

}
