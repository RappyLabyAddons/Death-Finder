package com.rappytv.deathfinder.core.smartchat.placeholder;

import com.rappytv.deathfinder.api.util.DeathLocation;
import com.rappytv.deathfinder.core.DeathFinderAddon;
import com.rappytv.deathfinder.core.config.DeathFinderConfig;
import de.jardateien.smartchat.api.SmartChatPlaceholder;
import net.labymod.api.addon.LabyAddon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DeathLocationPlaceholder extends SmartChatPlaceholder {

    public DeathLocationPlaceholder(@NotNull LabyAddon<DeathFinderConfig> addon) {
        super(addon, "death_location");
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String parse() {
        DeathLocation death = DeathFinderAddon.references().deathManager().getLocation();
        if(death == null) {
            return null;
        }

        return String.format(
            "[x: %s, y: %s, z: %s, yaw: %s, pitch: %s]",
            DECIMAL_FORMAT.format(death.getX()),
            DECIMAL_FORMAT.format(death.getY()),
            DECIMAL_FORMAT.format(death.getZ()),
            DECIMAL_FORMAT.format(death.getYaw()),
            DECIMAL_FORMAT.format(death.getPitch())
        );
    }
}
