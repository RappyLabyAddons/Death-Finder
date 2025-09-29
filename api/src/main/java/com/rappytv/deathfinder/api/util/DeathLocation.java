package com.rappytv.deathfinder.api.util;

import net.labymod.api.util.math.position.Position;
import net.labymod.api.util.math.vector.DoubleVector3;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

@SuppressWarnings("unused")
public class DeathLocation {

    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final String dimension;

    public DeathLocation(double x, double y, double z, @NotNull String dimension) {
        this(x, y, z, 0f, 0f, dimension);
    }

    public DeathLocation(@NotNull Position position, float yaw, float pitch, @NotNull String dimension) {
        this(position.getX(), position.getY(), position.getZ(), yaw, pitch, dimension);
    }

    public DeathLocation(double x, double y, double z, float yaw, float pitch, @NotNull String dimension) {
        Objects.requireNonNull(dimension, "Dimension cannot be null");
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.dimension = dimension;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double getYaw() {
        return this.yaw;
    }

    public double getPitch() {
        return this.pitch;
    }

    @NotNull
    public String getDimension() {
        return this.dimension;
    }

    public DoubleVector3 toDoubleVector3() {
        return new DoubleVector3(this.x, this.y, this.z);
    }

    public boolean equals(DeathLocation location) {
        if(location == null) return false;
        return this.x == location.x
            && this.y == location.y
            && this.z == location.z
            && this.pitch == location.pitch
            && this.yaw == location.yaw
            && this.dimension.equals(location.dimension);
    }
}
