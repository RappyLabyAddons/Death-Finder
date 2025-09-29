package com.rappytv.deathfinder.api.util;

/**
 * From <a href="https://github.com/labymod-addons/waypoints/blob/master/api/src/main/java/net/labymod/addons/waypoints/waypoint/WaypointType.java">WaypointType.java</a> because it's accessed without being able to check if Waypoint addon is installed
 */
public enum WaypointType {

    /**
     * A permanent waypoint.
     */
    PERMANENT,

    /**
     * A waypoint that will be deleted after the player leaves the server.
     */
    SERVER_SESSION,

    /**
     * A waypoint that will not be saved to the configuration and thus only be available during the
     * current game session.
     */
    ADDON_MANAGED
}