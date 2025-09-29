package com.rappytv.deathfinder.core.smartchat;

import com.rappytv.deathfinder.core.DeathFinderAddon;
import com.rappytv.deathfinder.core.smartchat.placeholder.DeathLocationPlaceholder;
import de.jardateien.smartchat.SmartChatAddon;

public class SmartChatManager {

    private SmartChatManager() {
    }

    private static boolean registered = false;

    public static void registerPlaceholders(DeathFinderAddon addon) {
        if(registered) {
            throw new IllegalStateException("SmartChat placeholder have already been registered!");
        }
        SmartChatAddon.placeholderRegistry().register(new DeathLocationPlaceholder(addon));
        registered = true;
    }

    public static boolean isRegistered() {
        return registered;
    }
}
