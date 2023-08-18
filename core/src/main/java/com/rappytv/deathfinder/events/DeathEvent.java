package com.rappytv.deathfinder.events;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.util.Location;
import com.rappytv.deathfinder.util.Util;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.event.HoverEvent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.Style;
import net.labymod.api.client.component.format.TextDecoration;

public class DeathEvent {

    public DeathEvent(Location location) {
        if(!DeathFinderAddon.get().configuration().enabled().get()) return;
        boolean backCommand = DeathFinderAddon.get().configuration().backCommand().get();
        boolean coordsCommand = DeathFinderAddon.get().configuration().coordsCommand().get();

        DeathFinderAddon.setDeathLocation(location);

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
                .append(Component.text("TP", Style.builder().color(NamedTextColor.RED).decorate(TextDecoration.BOLD).build()))
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
