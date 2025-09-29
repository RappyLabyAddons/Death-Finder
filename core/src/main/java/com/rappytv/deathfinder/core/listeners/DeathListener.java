package com.rappytv.deathfinder.core.listeners;

import com.rappytv.deathfinder.core.DeathFinderAddon;
import com.rappytv.deathfinder.api.events.DeathEvent;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.event.HoverEvent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextDecoration;
import net.labymod.api.event.Subscribe;

public class DeathListener {

    private final DeathFinderAddon addon;

    public DeathListener(DeathFinderAddon addon) {
        this.addon = addon;
    }

    @Subscribe
    public void onDeath(DeathEvent event) {
        if(!this.addon.configuration().enabled().get()) return;

        DeathFinderAddon.setDeathLocation(event.location());
        Laby.references().chatExecutor().displayClientMessage(
            Component.empty()
                .append(DeathFinderAddon.prefix)
                .append(Component.translatable("deathfinder.onDeath", NamedTextColor.GREEN))
                .append(Component.newline())
                .append(DeathFinderAddon.prefix)
                .append(
                    Component
                        .text("[", NamedTextColor.DARK_GRAY)
                        .append(Component.text("TP").color(NamedTextColor.RED).decorate(TextDecoration.BOLD))
                        .append(Component.text("]", NamedTextColor.DARK_GRAY))
                        .hoverEvent(HoverEvent.showText(Component.translatable("deathfinder.hover.teleport", NamedTextColor.GREEN)))
                        .clickEvent(ClickEvent.runCommand("/df back"))
                )
                .append(Component.text(" | ", NamedTextColor.DARK_GRAY))
                .append(
                    Component
                        .text("[", NamedTextColor.DARK_GRAY)
                        .append(Component.text("PING").color(NamedTextColor.YELLOW).decorate(TextDecoration.BOLD))
                        .append(Component.text("]", NamedTextColor.DARK_GRAY))
                        .hoverEvent(HoverEvent.showText(Component.translatable("deathfinder.hover.waypoint", NamedTextColor.GREEN)))
                        .clickEvent(ClickEvent.runCommand("/df waypoint"))
                )
                .append(Component.text(" | ", NamedTextColor.DARK_GRAY))
                .append(
                    Component
                        .text("[", NamedTextColor.DARK_GRAY)
                        .append(Component.text("INFOS").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD))
                        .append(Component.text("]", NamedTextColor.DARK_GRAY))
                        .hoverEvent(HoverEvent.showText(Component.translatable("deathfinder.hover.show", NamedTextColor.GREEN)))
                        .clickEvent(ClickEvent.runCommand("/df coords"))
                )
        );
    }

}
