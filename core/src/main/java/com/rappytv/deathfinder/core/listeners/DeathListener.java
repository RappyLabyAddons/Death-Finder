package com.rappytv.deathfinder.core.listeners;

import com.rappytv.deathfinder.api.event.DeathEvent;
import com.rappytv.deathfinder.core.DeathFinderAddon;
import java.util.concurrent.TimeUnit;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.event.HoverEvent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextDecoration;
import net.labymod.api.event.Subscribe;
import net.labymod.api.notification.Notification;
import net.labymod.api.notification.Notification.NotificationButton;
import net.labymod.api.util.concurrent.task.Task;
import net.labymod.api.util.concurrent.task.TaskBuilder;

public class DeathListener {

    private final DeathFinderAddon addon;
    private final TaskBuilder respawnTask = Task.builder(DeathFinderAddon.references().deathManager()::respawn);

    public DeathListener(DeathFinderAddon addon) {
        this.addon = addon;
    }

    @Subscribe
    public void onDeath(DeathEvent event) {
        if(!this.addon.configuration().enabled().get()) return;

        DeathFinderAddon.references().deathManager().setLocation(event.location());
        Laby.references().chatExecutor().displayClientMessage(
            Component.empty()
                .append(DeathFinderAddon.prefix())
                .append(Component.translatable("deathfinder.onDeath.message", NamedTextColor.GREEN))
                .append(Component.newline())
                .append(DeathFinderAddon.prefix())
                .append(
                    Component
                        .text("[", NamedTextColor.DARK_GRAY)
                        .append(Component.text("TP").color(NamedTextColor.RED).decorate(TextDecoration.BOLD))
                        .append(Component.text("]", NamedTextColor.DARK_GRAY))
                        .hoverEvent(HoverEvent.showText(Component.translatable("deathfinder.onDeath.hover.teleport", NamedTextColor.GREEN)))
                        .clickEvent(ClickEvent.runCommand("/df back"))
                )
                .append(Component.text(" | ", NamedTextColor.DARK_GRAY))
                .append(
                    Component
                        .text("[", NamedTextColor.DARK_GRAY)
                        .append(Component.text("PING").color(NamedTextColor.YELLOW).decorate(TextDecoration.BOLD))
                        .append(Component.text("]", NamedTextColor.DARK_GRAY))
                        .hoverEvent(HoverEvent.showText(Component.translatable("deathfinder.onDeath.hover.waypoint", NamedTextColor.GREEN)))
                        .clickEvent(ClickEvent.runCommand("/df waypoint"))
                )
                .append(Component.text(" | ", NamedTextColor.DARK_GRAY))
                .append(
                    Component
                        .text("[", NamedTextColor.DARK_GRAY)
                        .append(Component.text("INFOS").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD))
                        .append(Component.text("]", NamedTextColor.DARK_GRAY))
                        .hoverEvent(HoverEvent.showText(Component.translatable("deathfinder.onDeath.hover.show", NamedTextColor.GREEN)))
                        .clickEvent(ClickEvent.runCommand("/df coords"))
                )
        );

        if(this.addon.configuration().autoRespawn().get()) {
            if(event.isHardcore() && !this.addon.configuration().autoHardcoreRespawn().get()) return;
            int respawnDelay = this.addon.configuration().autoRespawnDelay().get();
            Task task = this.respawnTask.delay(respawnDelay, TimeUnit.SECONDS).build();
            task.execute();

            Notification.builder()
                .title(Component.translatable("deathfinder.onDeath.autoRespawn.title"))
                .text(Component.translatable(
                    "deathfinder.onDeath.autoRespawn.description",
                    Component.text(respawnDelay, NamedTextColor.AQUA)
                ))
                .duration(respawnDelay * 1000L)
                .addButton(NotificationButton.of(Component.translatable("deathfinder.onDeath.autoRespawn.cancel"), task::cancel))
                .buildAndPush();
        }
    }

}
