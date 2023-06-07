package com.gilles_m.rpg_regen.listener;

import com.gilles_m.rpg_regen.RPGRegen;
import com.gilles_m.rpg_regen.event.PlayerEnterCombatEvent;
import com.gilles_m.rpg_regen.event.PlayerLeaveCombatEvent;
import com.gilles_m.rpg_regen.manager.RegeneratorManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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

    @EventHandler(priority = EventPriority.HIGHEST)
    protected void onPlayerEnterCombat(final PlayerEnterCombatEvent event) {
        if(RPGRegen.getInstance().getConfigurationHolder().getExcludedDamageCauses().contains(event.getDamageCause())) {
            event.setCancelled(true);
            return;
        }
        RegeneratorManager.getInstance().stop(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGH)
    protected void onPlayerLeaveCombat(final PlayerLeaveCombatEvent event) {
        RegeneratorManager.getInstance().start(event.getPlayer());
    }

}
