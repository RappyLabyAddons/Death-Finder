package com.rappytv.back;

import com.rappytv.back.commands.BackCommand;
import com.rappytv.back.commands.CoordsCommand;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.api.util.I18n;

@AddonMain
public class BackAddon extends LabyAddon<BackConfig> {

    public final static String prefix = "";
    public static Location death;

    @Override
    protected void enable() {
        registerSettingCategory();
        registerCommand(new BackCommand(this));
        registerCommand(new CoordsCommand(this));
    }

    @Override
    protected Class<? extends BackConfig> configurationClass() {
        return BackConfig.class;
    }

    public static String getTranslation(String key, Object... args) {
        if(!I18n.has(key))
            return key;
        return I18n.getTranslation(key, args);
    }
}
