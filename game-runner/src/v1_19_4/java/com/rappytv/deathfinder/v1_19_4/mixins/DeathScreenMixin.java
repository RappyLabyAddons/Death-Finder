package com.rappytv.deathfinder.v1_19_4.mixins;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.events.DeathEvent;
import com.rappytv.deathfinder.util.Location;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {

    protected DeathScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    public void onDeathScreen(CallbackInfo ci) {
        if(this.minecraft == null || this.minecraft.player == null) return;

        LocalPlayer player = this.minecraft.player;
        Location deathLocation = new Location(player.getX(), player.getY(), player.getZ());

        if(DeathFinderAddon.get().configuration().saveRotation().get()) {
            deathLocation.setYaw(player.getXRot());
            deathLocation.setPitch(player.getYRot());
        }
        if(deathLocation.equals(DeathFinderAddon.getDeathLocation())) return;

        new DeathEvent(deathLocation);
    }
}
