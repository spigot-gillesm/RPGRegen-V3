package com.gilles_m.rpg_regen;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    protected void onPlayerJoin(final PlayerJoinEvent event) {
        RegeneratorManager.getInstance().start(event.getPlayer());
    }

    @EventHandler
    protected void onPlayerLeave(final PlayerQuitEvent event) {
        RegeneratorManager.getInstance().stop(event.getPlayer());
    }

}
