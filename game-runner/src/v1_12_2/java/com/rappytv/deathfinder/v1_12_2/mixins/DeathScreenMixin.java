package com.rappytv.deathfinder.v1_12_2.mixins;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.events.DeathEvent;
import com.rappytv.deathfinder.util.Location;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGameOver.class)
public class DeathScreenMixin extends GuiScreen {

    @Inject(method = "initGui", at = @At("TAIL"))
    public void onDeathScreen(CallbackInfo ci) {
        if(this.mc == null || this.mc.player == null) return;

        EntityPlayerSP player = this.mc.player;
        Location deathLocation = new Location(player.posX, player.posY, player.posZ);

        if(DeathFinderAddon.get().configuration().saveRotation().get()) {
            deathLocation.setYaw(player.rotationYaw);
            deathLocation.setPitch(player.rotationPitch);
        }
        if(deathLocation.equals(DeathFinderAddon.getDeathLocation())) return;

        new DeathEvent(deathLocation);
    }
}
