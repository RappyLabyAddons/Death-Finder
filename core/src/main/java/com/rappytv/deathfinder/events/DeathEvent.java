package com.rappytv.deathfinder.events;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.util.Location;
import com.rappytv.deathfinder.util.Util;
import net.labymod.api.Laby;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.event.HoverEvent;

public class DeathEvent {

    public DeathEvent(Location location) {
        if(!DeathFinderAddon.get().configuration().enabled().get()) return;
        boolean backCommand = DeathFinderAddon.get().configuration().backCommand().get();
        boolean coordsCommand = DeathFinderAddon.get().configuration().coordsCommand().get();

        DeathFinderAddon.setDeathLocation(location);

        // Message

        TextComponent.Builder builder = TextComponent.builder();
        builder.append("§8»\n");
        builder.append(
            DeathFinderAddon.prefix + "§a" + Util.getTranslation("deathfinder.messages.savedPoint") + "\n");
        if(backCommand || coordsCommand) builder.append(DeathFinderAddon.prefix);
        if(backCommand) builder.append(
            TextComponent.builder()
                .text("§8[§c§lTP§8]")
                .hoverEvent(HoverEvent.showText(TextComponent.builder().text("§a" + Util.getTranslation("deathfinder.messages.clickToTeleport")).build()))
                .clickEvent(ClickEvent.runCommand("/back"))
                .build()
        );
        if(backCommand && coordsCommand) builder.append(TextComponent.builder().text(" §7| ").build());
        if(coordsCommand) builder.append(
            TextComponent.builder()
                .text("§8[§b§lINFO§8]")
                .hoverEvent(HoverEvent.showText(TextComponent.builder().text("§a" + Util.getTranslation("deathfinder.messages.clickToShow")).build()))
                .clickEvent(ClickEvent.runCommand("/coords"))
                .build()
        );
        builder.append((backCommand || coordsCommand ? "\n" : "") + "§8»");
        Laby.references().chatExecutor().displayClientMessage(builder.build());
    }
}
