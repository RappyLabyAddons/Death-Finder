package com.rappytv.deathfinder.events;

import com.rappytv.deathfinder.util.Location;
import net.labymod.api.event.Event;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public record DeathEvent(Location location) implements Event {

    public DeathEvent {
        Objects.requireNonNull(location, "Location cannot be null!");
    }

    @Override
    public @NotNull Location location() {
        return location;
    }
}
