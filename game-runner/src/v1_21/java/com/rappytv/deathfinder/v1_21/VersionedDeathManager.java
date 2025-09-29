package com.rappytv.deathfinder.v1_21;

import com.rappytv.deathfinder.api.util.DeathManager;
import javax.inject.Singleton;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

@Singleton
@Implements(DeathManager.class)
public class VersionedDeathManager extends DeathManager {

    @Override
    public void respawn() {
        LocalPlayer player = Minecraft.getInstance().player;
        if(player == null) return;
        player.respawn();
    }
}
