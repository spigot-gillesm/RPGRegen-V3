package com.gilles_m.rpg_regen.listener;

import com.gilles_m.rpg_regen.manager.RegeneratorManager;
import com.gilles_m.rpg_regen.event.PlayerEnterCombatEvent;
import com.gilles_m.rpg_regen.event.PlayerLeaveCombatEvent;
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

    @EventHandler
    protected void onPlayerEnterCombat(final PlayerEnterCombatEvent event) {
        RegeneratorManager.getInstance().stop(event.getPlayer());
    }

    @EventHandler
    protected void onPlayerLeaveCombat(final PlayerLeaveCombatEvent event) {
        RegeneratorManager.getInstance().start(event.getPlayer());
    }

}
