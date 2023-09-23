package com.rappytv.deathfinder.listeners;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.events.DeathEvent;
import net.labymod.api.Laby;
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

        // Message

        Component component = Component
            .text("»\n", NamedTextColor.DARK_GRAY)
            .append(DeathFinderAddon.prefix)
            .append(Component.translatable("deathfinder.messages.savedPoint", NamedTextColor.GREEN))
            .append(Component.text("\n"));

        if(backCommand || coordsCommand) component.append(DeathFinderAddon.prefix);
        if(backCommand) component.append(
            Component
                .text("[", NamedTextColor.DARK_GRAY)
                .append(Component.text("TP", Style.builder().color(NamedTextColor.RED).decorate(TextDecoration.BOLD).build()))
                .append(Component.text("]", NamedTextColor.DARK_GRAY))
                .hoverEvent(HoverEvent.showText(Component.translatable("deathfinder.messages.clickToTeleport", NamedTextColor.GREEN)))
                .clickEvent(ClickEvent.runCommand("/back"))
        );
        if(backCommand && coordsCommand) component.append(Component.text(" | ", NamedTextColor.DARK_GRAY));
        if(coordsCommand) component.append(
            Component
                .text("[", NamedTextColor.DARK_GRAY)
                .append(Component.text("INFOS", Style.builder().color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD).build()))
                .append(Component.text("]", NamedTextColor.DARK_GRAY))
                .hoverEvent(HoverEvent.showText(Component.translatable("deathfinder.messages.clickToShow", NamedTextColor.GREEN)))
                .clickEvent(ClickEvent.runCommand("/coords"))
        );
        component.append(Component.text((backCommand || coordsCommand ? "\n" : "") + "»", NamedTextColor.DARK_GRAY));
        Laby.references().chatExecutor().displayClientMessage(component);
        System.out.println(component);
    }

}
