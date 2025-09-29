package com.rappytv.deathfinder.v1_16_5.mixins;

import com.rappytv.deathfinder.api.event.DeathEvent;
import com.rappytv.deathfinder.core.DeathFinderAddon;
import net.labymod.api.Laby;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {

    @Shadow
    @Final
    private boolean hardcore;

    protected DeathScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("HEAD"))
    public void onDeathScreen(CallbackInfo ci) {
        DeathEvent event = new DeathEvent(this.hardcore);
        if(event.location().equals(DeathFinderAddon.references().deathManager().getLocation())) return;
        Laby.fireEvent(event);
    }
}
