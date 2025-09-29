package com.rappytv.deathfinder.core.commands;

import com.rappytv.deathfinder.api.util.DeathLocation;
import com.rappytv.deathfinder.core.DeathFinderAddon;
import net.labymod.addons.waypoints.WaypointService;
import net.labymod.addons.waypoints.Waypoints;
import net.labymod.addons.waypoints.waypoint.WaypointBuilder;
import net.labymod.addons.waypoints.waypoint.WaypointType;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.chat.command.SubCommand;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import java.text.SimpleDateFormat;

public class DeathFinderCommand extends Command {

    public DeathFinderCommand(DeathFinderAddon addon) {
        super("deathfinder", "df");

        this.withSubCommand(new BackCommand());
        this.withSubCommand(new CoordsCommand());
        this.withSubCommand(new WaypointCommand(addon));
    }

    @Override
    public boolean execute(String prefix, String[] arguments) {
        this.displayMessage(
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
                this.displayMessage(
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
                this.displayMessage(
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
            this.displayMessage(
                Component.empty()
                    .append(DeathFinderAddon.prefix)
                    .append(Component.translatable(
                        "deathfinder.command.coords.success",
                        NamedTextColor.YELLOW
                    ))
                    .append(Component.newline())
                    .append(DeathFinderAddon.prefix)
                    .append(Component.text("X: ", NamedTextColor.GREEN))
                    .append(this.formatValue(death.getX()))
                    .append(Component.newline())
                    .append(DeathFinderAddon.prefix)
                    .append(Component.text("Y: ", NamedTextColor.GREEN))
                    .append(this.formatValue(death.getY()))
                    .append(Component.newline())
                    .append(DeathFinderAddon.prefix)
                    .append(Component.text("Z: ", NamedTextColor.GREEN))
                    .append(this.formatValue(death.getZ()))
                    .append(Component.newline())
                    .append(DeathFinderAddon.prefix)
                    .append(Component.text("Yaw: ", NamedTextColor.GREEN))
                    .append(this.formatValue(death.getYaw()))
                    .append(Component.newline())
                    .append(DeathFinderAddon.prefix)
                    .append(Component.text("Pitch: ", NamedTextColor.GREEN))
                    .append(this.formatValue(death.getPitch()))
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

        private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        private final DeathFinderAddon addon;

        public WaypointCommand(DeathFinderAddon addon) {
            super("waypoint");

            this.addon = addon;
        }

        @Override
        public boolean execute(String prefix, String[] arguments) {
            if(DeathFinderAddon.getDeathLocation() == null) {
                this.displayMessage(
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
                this.displayMessage(
                    Component.empty()
                        .append(DeathFinderAddon.prefix)
                        .append(Component.translatable(
                            "deathfinder.command.waypoint.notEnabled",
                            NamedTextColor.RED
                        ))
                );
                return true;
            }
            WaypointService service = Waypoints.references().waypointService();
            DeathLocation death = DeathFinderAddon.getDeathLocation();
            service.add(
                WaypointBuilder.create()
                    .title(Component.text(this.getWaypointName()))
                    .type(this.getWaypointType())
                    .location(death.toDoubleVector3())
                    .color(this.addon.configuration().waypoints().color().get())
                    .applyCurrentContext()
                    .dimension(death.getDimension())
                    .build()
            );
            service.refresh();
            this.displayMessage(
                Component.empty()
                    .append(DeathFinderAddon.prefix)
                    .append(Component.translatable(
                        "deathfinder.command.waypoint.success",
                        NamedTextColor.GREEN
                    ))
            );
            return true;
        }

        private String getWaypointName() {
            return this.addon.configuration().waypoints().title().get().replace(
                "{date}",
                this.format.format(DeathFinderAddon.getDeathLocation().getTimestamp())
            );
        }

        private WaypointType getWaypointType() {
            return WaypointType.valueOf(this.addon.configuration().waypoints().type().get().name());
        }
    }
}
