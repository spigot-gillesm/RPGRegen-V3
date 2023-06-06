package com.gilles_m.rpg_regen.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLeaveCombatEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    @Getter
    private final Player player;

    public PlayerLeaveCombatEvent(Player player) {

        this.player = player;

    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
