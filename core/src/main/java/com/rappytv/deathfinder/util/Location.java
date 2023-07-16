package com.rappytv.deathfinder.util;

public class Location {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public Location(double x, double y, double z) {
        this(x, y, z, 0f, 0f);
    }

    public Location(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    public double getYaw() {
        return yaw;
    }
    public double getPitch() {
        return pitch;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setZ(double z) {
        this.z = z;
    }
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public boolean equals(Location location) {
        if(location == null) return false;
        return this.x == location.x
            && this.y == location.y
            && this.z == location.z
            && this.pitch == location.pitch
            && this.yaw == location.yaw;
    }
}
