package com.rappytv.deathfinder.api.events;

import com.rappytv.deathfinder.api.util.DeathLocation;
import net.labymod.api.event.Event;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public record DeathEvent(DeathLocation location) implements Event {

    public DeathEvent {
        Objects.requireNonNull(location, "Location cannot be null!");
    }

    @Override
    public @NotNull DeathLocation location() {
        return this.location;
    }
}
