package com.rappytv.deathfinder.commands;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.util.DeathLocation;
import net.labymod.addons.waypoints.WaypointService;
import net.labymod.addons.waypoints.Waypoints;
import net.labymod.addons.waypoints.waypoint.WaypointMeta;
import net.labymod.addons.waypoints.waypoint.WaypointType;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.chat.command.SubCommand;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.util.Color;
import net.labymod.api.util.math.vector.FloatVector3;

public class DeathFinderCommand extends Command {

    public DeathFinderCommand() {
        super("deathfinder", "df");

        withSubCommand(new BackCommand());
        withSubCommand(new CoordsCommand());
        withSubCommand(new WaypointCommand());
    }

    @Override
    public boolean execute(String prefix, String[] arguments) {
        displayMessage(
            Component.empty()
                .append(DeathFinderAddon.prefix)
                .append(Component.translatable(
                    "deathfinder.command.usage",
                    NamedTextColor.GRAY,
                    Component.text(
                        "/" + prefix + " <back/coords/waypoint>",
                        NamedTextColor.AQUA
                    )
                ))
        );
        return true;
    }

    private static class BackCommand extends SubCommand {

        public BackCommand() {
            super("back");
        }

        @Override
        public boolean execute(String prefix, String[] arguments) {
            if(DeathFinderAddon.getDeathLocation() == null) {
                displayMessage(
                    Component.empty()
                        .append(DeathFinderAddon.prefix)
                        .append(Component.translatable(
                            "deathfinder.command.noSavedPoint",
                            NamedTextColor.RED
                        ))
                );
                return true;
            }
            DeathLocation death = DeathFinderAddon.getDeathLocation();
            Laby.references().chatExecutor().chat(
                String.format(
                    "/tp @p %s %s %s %s %s",
                    death.getX(),
                    death.getY(),
                    death.getZ(),
                    death.getYaw(),
                    death.getPitch()
                ),
                false
            );
            return true;
        }
    }

    private static class CoordsCommand extends SubCommand {

        public CoordsCommand() {
            super("coords");
        }

        @Override
        public boolean execute(String prefix, String[] arguments) {
            if(DeathFinderAddon.getDeathLocation() == null) {
                displayMessage(
                    Component.empty()
                        .append(DeathFinderAddon.prefix)
                        .append(Component.translatable(
                            "deathfinder.command.noSavedPoint",
                            NamedTextColor.RED
                        ))
                );
                return true;
            }
            DeathLocation death = DeathFinderAddon.getDeathLocation();
            displayMessage(
                Component.empty()
                    .append(DeathFinderAddon.prefix)
                    .append(Component.translatable(
                        "deathfinder.command.coords.success",
                        NamedTextColor.YELLOW
                    ))
                    .append(Component.newline())
                    .append(DeathFinderAddon.prefix)
                    .append(Component.text("X: ", NamedTextColor.GREEN))
                    .append(formatValue(death.getX()))
                    .append(Component.newline())
                    .append(DeathFinderAddon.prefix)
                    .append(Component.text("Y: ", NamedTextColor.GREEN))
                    .append(formatValue(death.getY()))
                    .append(Component.newline())
                    .append(DeathFinderAddon.prefix)
                    .append(Component.text("Z: ", NamedTextColor.GREEN))
                    .append(formatValue(death.getZ()))
                    .append(Component.newline())
                    .append(DeathFinderAddon.prefix)
                    .append(Component.text("Yaw: ", NamedTextColor.GREEN))
                    .append(formatValue(death.getYaw()))
                    .append(Component.newline())
                    .append(DeathFinderAddon.prefix)
                    .append(Component.text("Pitch: ", NamedTextColor.GREEN))
                    .append(formatValue(death.getPitch()))
            );
            return true;
        }

        private Component formatValue(double value) {
            return Component.text(
                String.format(java.util.Locale.US,"%.2f", value),
                NamedTextColor.AQUA
            );
        }
    }

    private static class WaypointCommand extends SubCommand {

        public WaypointCommand() {
            super("waypoint");
        }

        @Override
        public boolean execute(String prefix, String[] arguments) {
            if(DeathFinderAddon.getDeathLocation() == null) {
                displayMessage(
                    Component.empty()
                        .append(DeathFinderAddon.prefix)
                        .append(Component.translatable(
                            "deathfinder.command.noSavedPoint",
                            NamedTextColor.RED
                        ))
                );
                return true;
            }
            if(!Laby.labyAPI().addonService().isEnabled("labyswaypoints")) {
                displayMessage(
                    Component.empty()
                        .append(DeathFinderAddon.prefix)
                        .append(Component.translatable(
                            "deathfinder.command.waypoint.notEnabled",
                            NamedTextColor.RED
                        ))
                );
                return true;
            }
            WaypointService service = Waypoints.getReferences().waypointService();
            DeathLocation death = DeathFinderAddon.getDeathLocation();
            service.addWaypoint(new WaypointMeta(
                Component.text("Death location"),
                Color.of("#5e17eb"),
                WaypointType.PERMANENT,
                new FloatVector3((float) death.getX(), (float) death.getY(), (float) death.getZ()),
                true,
                service.actualWorld(),
                service.actualServer(),
                service.actualDimension() != null
                    ? service.actualDimension()
                    : "labymod:unknown"
            ));
            service.refreshWaypoints();
            displayMessage(
                Component.empty()
                    .append(DeathFinderAddon.prefix)
                    .append(Component.translatable(
                        "deathfinder.command.waypoint.success",
                        NamedTextColor.GREEN
                    ))
            );
            return true;
        }
    }
}
