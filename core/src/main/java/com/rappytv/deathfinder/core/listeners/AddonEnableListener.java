package com.rappytv.deathfinder.core.listeners;

import com.rappytv.deathfinder.core.DeathFinderAddon;
import com.rappytv.deathfinder.core.smartchat.SmartChatManager;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.addon.lifecycle.GlobalAddonPostEnableEvent;

public class AddonEnableListener {

    private final DeathFinderAddon addon;

    public AddonEnableListener(DeathFinderAddon addon) {
        this.addon = addon;
    }

    @Subscribe
    public void onAddonEnable(GlobalAddonPostEnableEvent event) {
        if(!event.addonInfo().getNamespace().equals("smartchat")) return;
        if(SmartChatManager.isRegistered()) return;
        SmartChatManager.registerPlaceholders(this.addon);
        this.addon.logger().info("Successfully registered SmartChat placeholders!");
    }

}
