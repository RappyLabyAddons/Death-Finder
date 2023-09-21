package com.rappytv.deathfinder.v1_20_2;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.events.DeathEvent;
import com.rappytv.deathfinder.util.Location;
import net.labymod.api.Laby;
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
        System.out.println("\n\n1\n\n");
        if(this.minecraft == null || this.minecraft.player == null) return;
        System.out.println("\n\n2\n\n");

        LocalPlayer player = this.minecraft.player;
        Location deathLocation = new Location(player.getX(), player.getY(), player.getZ());

        if(DeathFinderAddon.get().configuration().saveRotation().get()) {
            deathLocation.setYaw(player.getXRot());
            deathLocation.setPitch(player.getYRot());
            System.out.println("\n\n3\n\n");
        }
        if(deathLocation.equals(DeathFinderAddon.getDeathLocation())) return;
        System.out.println("\n\n4\n\n");

        Laby.fireEvent(new DeathEvent(deathLocation));
    }
}
