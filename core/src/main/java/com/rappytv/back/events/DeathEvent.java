package com.rappytv.back.events;

import com.rappytv.back.BackAddon;
import com.rappytv.back.util.Location;
import com.rappytv.back.util.Util;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.event.HoverEvent;

public class DeathEvent {

    public DeathEvent(Location location) {
        boolean backCommand = BackAddon.get().configuration().backCommand().get();
        boolean coordsCommand = BackAddon.get().configuration().coordsCommand().get();

        BackAddon.death = location;

        // Message

        TextComponent.Builder builder = TextComponent.builder();
        builder.append("§8»\n");
        builder.append(BackAddon.prefix + "§a" + Util.getTranslation("back.messages.savedPoint") + "\n");
        if(backCommand || coordsCommand) builder.append(BackAddon.prefix);
        if(backCommand) builder.append(
            TextComponent.builder()
                .text("§8[§c§lTP§8]")
                .hoverEvent(HoverEvent.showText(TextComponent.builder().text("§a" + Util.getTranslation("back.messages.clickToTeleport")).build()))
                .clickEvent(ClickEvent.runCommand("/back"))
                .build()
        );
        if(backCommand && coordsCommand) builder.append(TextComponent.builder().text(" §7| ").build());
        if(coordsCommand) builder.append(
            TextComponent.builder()
                .text("§8[§b§lINFO§8]")
                .hoverEvent(HoverEvent.showText(TextComponent.builder().text("§a" + Util.getTranslation("back.messages.clickToShow")).build()))
                .clickEvent(ClickEvent.runCommand("/coords"))
                .build()
        );
        builder.append((backCommand || coordsCommand ? "\n" : "") + "§8»");
        BackAddon.get().displayMessage(builder.build());
    }
}
