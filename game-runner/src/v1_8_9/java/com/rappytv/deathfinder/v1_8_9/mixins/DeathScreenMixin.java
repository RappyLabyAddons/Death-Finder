package com.rappytv.deathfinder.v1_8_9.mixins;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.events.DeathEvent;
import com.rappytv.deathfinder.util.DeathLocation;
import net.labymod.api.Laby;
import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGameOver.class)
public class DeathScreenMixin extends GuiScreen {

    @Inject(method = "initGui", at = @At("HEAD"))
    public void onDeathScreen(CallbackInfo ci) {
        ClientPlayer player = Laby.labyAPI().minecraft().getClientPlayer();
        if(player == null) return;

        DeathLocation deathLocation = new DeathLocation(
            player.getPosX(),
            player.getPosY(),
            player.getPosZ(),
            ((Entity) player).getRotationYaw(),
            ((Entity) player).getRotationPitch()
        );

        if(deathLocation.equals(DeathFinderAddon.getDeathLocation())) return;

        Laby.fireEvent(new DeathEvent(deathLocation));
    }
}
