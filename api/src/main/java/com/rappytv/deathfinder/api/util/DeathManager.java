package com.rappytv.deathfinder.api.util;

import net.labymod.api.reference.annotation.Referenceable;

@Referenceable
public abstract class DeathManager {

    private DeathLocation location;

    public abstract void respawn();

    public DeathLocation getLocation() {
        return this.location;
    }

    public void setLocation(DeathLocation location) {
        this.location = location;
    }
}
