package com.rappytv.back;

public class Location {

    public double x;
    public double y;
    public double z;
    public float yaw;
    public float pitch;

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = 0f;
        this.pitch = 0f;
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
}
