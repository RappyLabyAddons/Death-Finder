package com.rappytv.deathfinder.v1_12_2;

import com.rappytv.deathfinder.api.util.DeathManager;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

import javax.inject.Singleton;

@Singleton
@Implements(DeathManager.class)
public class VersionedDeathManager extends DeathManager {

    @Override
    public void respawn() {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if(player == null) return;
        player.respawnPlayer();
    }
}
