package com.gilles_m.rpg_regen.listener;

import com.gilles_m.rpg_regen.manager.CombatManager;
import com.gilles_m.rpg_regen.event.PlayerEnterCombatEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.projectiles.ProjectileSource;

public class CombatListener implements Listener {

    @EventHandler
    public void onHit(final EntityDamageByEntityEvent event) {
        Player player = null;
        boolean inCombat = false;

        //Check if a player hits an entity
        if(event.getDamager() instanceof Player) {
            inCombat = true;
            player = (Player) event.getDamager();
        }
        //Check if a player has been hit by another entity
        else if(event.getEntity() instanceof Player) {
            player = (Player) event.getEntity();
        }
        //Check for projectiles
        if(event.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE) ||
                event.getCause().equals(EntityDamageEvent.DamageCause.MAGIC)) {

            final ProjectileSource source = ((Projectile) event.getDamager()).getShooter();

            //Check if a player shot an entity
            if(source instanceof Player) {
                player = (Player) source;
            }
            //Check if the player was shot by another entity
            else if(event.getEntity() instanceof Player) {
                player = (Player) event.getEntity();
            }
        }
        //Check if a player is effectively in combat
        if(player != null && inCombat) {
            final PlayerEnterCombatEvent playerEnterCombatEvent = new PlayerEnterCombatEvent(player, event.getCause());
            Bukkit.getServer().getPluginManager().callEvent(playerEnterCombatEvent);

            //If the event has not been cancelled put the player in combat
            if(!playerEnterCombatEvent.isCancelled()) {
                CombatManager.getInstance().startCombat(player);
            }
        }
    }

}
