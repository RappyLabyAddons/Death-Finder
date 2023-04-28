package com.rappytv.back.util;

import static com.rappytv.back.BackAddon.prefix;

import com.rappytv.back.BackAddon;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.util.I18n;

public class Util {
    public static String getTranslation(String key, Object... args) {
        if(!I18n.has(key))
            return key;
        return I18n.getTranslation(key, args);
    }

    public static void msg(String text) {
        TextComponent.Builder component = TextComponent.builder();
        String[] lines = text.split("\n");
        component.append("§8»\n");

        for(String line : lines) {
            component.append(prefix + line + "\n");
        }

        component.append("§8»");
        BackAddon.get().displayMessage(component.build());
    }
}
