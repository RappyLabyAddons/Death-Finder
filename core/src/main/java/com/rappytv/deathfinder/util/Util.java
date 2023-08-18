package com.rappytv.deathfinder.util;

import static com.rappytv.deathfinder.DeathFinderAddon.prefix;

import com.rappytv.deathfinder.DeathFinderAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.util.I18n;

public class Util {
    public static String getTranslation(String key, Object... args) {
        if(!I18n.has(key))
            return key;
        return I18n.getTranslation(key, args);
    }

    public static void msg(Component... lines) {
        Component component = Component
            .text("»\n", NamedTextColor.DARK_GRAY);

        for(Component line : lines) {
            component.append(prefix).append(line).append(Component.text("\n"));
        }

        component.append(Component.text("»", NamedTextColor.DARK_GRAY));
        DeathFinderAddon.get().displayMessage(component);
    }
}
