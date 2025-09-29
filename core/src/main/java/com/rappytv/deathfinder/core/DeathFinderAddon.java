package com.rappytv.deathfinder.core;

import com.rappytv.deathfinder.api.generated.ReferenceStorage;
import com.rappytv.deathfinder.core.commands.DeathFinderCommand;
import com.rappytv.deathfinder.core.config.DeathFinderConfig;
import com.rappytv.deathfinder.core.listeners.AddonEnableListener;
import com.rappytv.deathfinder.core.listeners.DeathListener;
import com.rappytv.deathfinder.core.smartchat.SmartChatManager;
import net.labymod.api.Laby;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextDecoration;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.api.revision.SimpleRevision;
import net.labymod.api.util.version.SemanticVersion;

@AddonMain
public class DeathFinderAddon extends LabyAddon<DeathFinderConfig> {

    private final static Component PREFIX = Component.empty()
        .append(Component.text("DF ").color(NamedTextColor.DARK_PURPLE).decorate(TextDecoration.BOLD))
        .append(Component.text("» ", NamedTextColor.DARK_GRAY));
    private static ReferenceStorage referenceStorage;

    @Override
    protected void preConfigurationLoad() {
        Laby.references().revisionRegistry().register(new SimpleRevision(
            "deathfinder",
            new SemanticVersion("1.1.1"),
            "2025-09-29"
        ));
    }

    @Override
    protected void enable() {
        referenceStorage = this.referenceStorageAccessor();

        this.registerSettingCategory();
        this.registerCommand(new DeathFinderCommand(this));
        this.registerListener(new AddonEnableListener(this));
        this.registerListener(new DeathListener(this));

        if(Laby.labyAPI().addonService().isEnabled("smartchat")) {
            SmartChatManager.registerPlaceholders(this);
            this.logger().info("Successfully registered SmartChat placeholders!");
        } else {
            this.logger().info("SmartChat not enabled. Skipping placeholder registration...");
        }
    }

    @Override
    protected Class<? extends DeathFinderConfig> configurationClass() {
        return DeathFinderConfig.class;
    }

    public static Component prefix() {
        return PREFIX.copy();
    }

    public static ReferenceStorage references() {
        return referenceStorage;
    }
}
