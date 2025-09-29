package com.rappytv.deathfinder.api.event;

import com.rappytv.deathfinder.api.util.DeathLocation;
import net.labymod.addons.waypoints.Waypoints;
import net.labymod.api.Laby;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.event.Event;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class DeathEvent implements Event {

    private final DeathLocation location;
    private final boolean hardcore;

    public DeathEvent(boolean hardcore) {
        this.hardcore = hardcore;
        ClientPlayer player = Laby.labyAPI().minecraft().getClientPlayer();
        if(player == null) throw new NullPointerException("Player cannot be null");

        this.location = new DeathLocation(
            player.position(),
            player.getRotationYaw(),
            player.getRotationPitch(),
            Objects.requireNonNullElse(
                Waypoints.references().waypointService().getDimension(),
                "labymod:unknown"
            )
        );
    }

    @NotNull
    public DeathLocation location() {
        return this.location;
    }

    public boolean isHardcore() {
        return this.hardcore;
    }
}
