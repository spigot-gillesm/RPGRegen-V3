package com.gilles_m.rpg_regen.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerEnterCombatEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private boolean isCancelled;
    @Getter
    private final Player player;
    @Getter
    private final EntityDamageEvent.DamageCause damageCause;

    public PlayerEnterCombatEvent(final Player player, final EntityDamageEvent.DamageCause damageCause) {
        this.player = player;
        this.damageCause = damageCause;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

}
