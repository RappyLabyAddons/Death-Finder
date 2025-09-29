package com.rappytv.deathfinder.v1_19_4;

import com.rappytv.deathfinder.api.util.DeathManager;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import javax.inject.Singleton;

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
