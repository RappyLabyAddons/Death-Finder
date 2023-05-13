package com.rappytv.deathfinder.v1_19_2.mixins;

import com.rappytv.deathfinder.DeathFinderAddon;
import com.rappytv.deathfinder.events.DeathEvent;
import com.rappytv.deathfinder.util.Location;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class DeathEventMixin {

    @Inject(method = "gameEvent(Lnet/minecraft/world/level/gameevent/GameEvent;Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"))
    public void onDeath(GameEvent event, Entity entity, CallbackInfo ci) {
        if(event == GameEvent.ENTITY_DIE && entity.getType() == EntityType.PLAYER) {
            LocalPlayer player = Minecraft.getInstance().player;

            if(player != null && player.position().equals(entity.position())) {
                Location deathLocation = new Location(player.getX(), player.getY(), player.getZ());

                if(DeathFinderAddon.get().configuration().saveRotation().get()) {
                    deathLocation.setYaw(player.getXRot());
                    deathLocation.setPitch(player.getYRot());
                }

                new DeathEvent(deathLocation);
            }
        }
    }
}