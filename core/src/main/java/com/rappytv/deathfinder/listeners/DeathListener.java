package com.rappytv.deathfinder.listeners;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.events.DeathEvent;
import com.rappytv.deathfinder.util.Util;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TextComponent;
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

        Component builder = Component
            .text("»\n", NamedTextColor.DARK_GRAY)
            .append(DeathFinderAddon.prefix)
            .append(Component.translatable("deathfinder.messages.savedPoint", NamedTextColor.GREEN))
            .append(Component.text("\n"));

        if(backCommand || coordsCommand) builder.append(DeathFinderAddon.prefix);
        if(backCommand) builder.append(
            Component
                .text("[", NamedTextColor.DARK_GRAY)
                .append(Component.text("TP", Style.builder().color(NamedTextColor.RED).decorate(
                    TextDecoration.BOLD).build()))
                .append(Component.text("]", NamedTextColor.DARK_GRAY))
                .hoverEvent(HoverEvent.showText(TextComponent.builder().text("§a" + Util.getTranslation("deathfinder.messages.clickToTeleport")).build()))
                .clickEvent(ClickEvent.runCommand("/back"))
        );
        if(backCommand && coordsCommand) builder.append(Component.text(" §7| "));
        if(coordsCommand) builder.append(
            Component
                .text("§8[§b§lINFO§8]")
                .hoverEvent(HoverEvent.showText(TextComponent.builder().text("§a" + Util.getTranslation("deathfinder.messages.clickToShow")).build()))
                .clickEvent(ClickEvent.runCommand("/coords"))
        );
        builder.append(Component.text((backCommand || coordsCommand ? "\n" : "") + "§8»", NamedTextColor.DARK_GRAY));
        Laby.references().chatExecutor().displayClientMessage(builder);
    }

}
