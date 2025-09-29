package com.rappytv.deathfinder.v1_8_9.mixins;

import com.rappytv.deathfinder.api.event.DeathEvent;
import com.rappytv.deathfinder.core.DeathFinderAddon;
import net.labymod.api.Laby;
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
        DeathEvent event = new DeathEvent(this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled());
        if(event.location().equals(DeathFinderAddon.references().deathManager().getLocation())) return;
        Laby.fireEvent(event);
    }
}
