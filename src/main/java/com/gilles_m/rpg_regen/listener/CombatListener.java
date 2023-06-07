package com.gilles_m.rpg_regen.listener;

import com.gilles_m.rpg_regen.RPGRegen;
import com.gilles_m.rpg_regen.manager.CombatManager;
import com.gilles_m.rpg_regen.event.PlayerEnterCombatEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

import java.util.Set;
import java.util.stream.Collectors;

public class CombatListener implements Listener {

    @EventHandler
    public void onHit(final EntityDamageByEntityEvent event) {
        Player player = null;

        //Check if a player hits an entity
        if(event.getDamager() instanceof Player) {
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
        if(player != null) {
            final PlayerEnterCombatEvent playerEnterCombatEvent = new PlayerEnterCombatEvent(player, event.getCause());
            Bukkit.getServer().getPluginManager().callEvent(playerEnterCombatEvent);

            //If the event has not been cancelled put the player in combat
            if(!playerEnterCombatEvent.isCancelled()) {
                CombatManager.getInstance().startCombat(player);
            }
        }
    }

    @EventHandler
    protected void onDamaged(final EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            final PlayerEnterCombatEvent playerEnterCombatEvent = new PlayerEnterCombatEvent(player, event.getCause());
            Bukkit.getServer().getPluginManager().callEvent(playerEnterCombatEvent);

            //If the event has not been cancelled put the player in combat
            if(!playerEnterCombatEvent.isCancelled()) {
                CombatManager.getInstance().startCombat(player);
            }
        }
    }

    @EventHandler
    protected void onPotionSplash(final PotionSplashEvent event) {
        //Check if the intersection of the fighting potion effects with the thrown potion effects is empty
        //If empty -> return
        final Set<PotionEffectType> intersection = event.getPotion().getEffects().stream()
                .map(PotionEffect::getType)
                //Filter the elements present in the config holder
                .filter(effectType -> RPGRegen.getInstance().getConfigurationHolder().getSplashFightingPotions().contains(effectType))
                .collect(Collectors.toSet());

        if(intersection.isEmpty()) {
            return;
        }
        final ProjectileSource source = event.getEntity().getShooter();

        if(source instanceof Player) {
            final Player player = (Player) source;
            final PlayerEnterCombatEvent playerEnterCombatEvent = new PlayerEnterCombatEvent(player,
                    EntityDamageEvent.DamageCause.MAGIC);
            Bukkit.getServer().getPluginManager().callEvent(playerEnterCombatEvent);

            //If the event has not been cancelled put the player in combat
            if(!playerEnterCombatEvent.isCancelled()) {
                CombatManager.getInstance().startCombat(player);
            }
        }
    }

    @EventHandler
    protected void onLingeringSplash(final LingeringPotionSplashEvent event) {
        //Check if the intersection of the fighting potion effects with the thrown potion effects is empty
        //If empty -> return
        final Set<PotionEffectType> intersection = event.getEntity().getEffects().stream()
                .map(PotionEffect::getType)
                //Filter the elements present in the config holder
                .filter(effectType -> RPGRegen.getInstance().getConfigurationHolder().getLingeringFightingPotions().contains(effectType))
                .collect(Collectors.toSet());

        if(intersection.isEmpty()) {
            return;
        }
        final ProjectileSource source = event.getEntity().getShooter();

        if(source instanceof Player) {
            final Player player = (Player) source;
            final PlayerEnterCombatEvent playerEnterCombatEvent = new PlayerEnterCombatEvent(player,
                    EntityDamageEvent.DamageCause.MAGIC);
            Bukkit.getServer().getPluginManager().callEvent(playerEnterCombatEvent);

            //If the event has not been cancelled put the player in combat
            if(!playerEnterCombatEvent.isCancelled()) {
                CombatManager.getInstance().startCombat(player);
            }
        }
    }

    @EventHandler
    protected void onPlayerConsumeItem(final PlayerItemConsumeEvent event) {
        final var consumedItem = event.getItem();

        if(!consumedItem.hasItemMeta()) {
            return;
        }
        if(!(consumedItem.getItemMeta() instanceof PotionMeta)) {
            return;
        }
        final var potionMeta = (PotionMeta) consumedItem.getItemMeta();

        final Set<PotionEffectType> intersection = potionMeta.getCustomEffects().stream()
                .map(PotionEffect::getType)
                //Filter the elements present in the config holder
                .filter(effectType -> RPGRegen.getInstance().getConfigurationHolder().getFightingPotions().contains(effectType))
                .collect(Collectors.toSet());

        if(intersection.isEmpty()) {
            return;
        }
        final Player player = event.getPlayer();
        final PlayerEnterCombatEvent playerEnterCombatEvent = new PlayerEnterCombatEvent(player,
                EntityDamageEvent.DamageCause.MAGIC);
        Bukkit.getServer().getPluginManager().callEvent(playerEnterCombatEvent);

        //If the event has not been cancelled put the player in combat
        if(!playerEnterCombatEvent.isCancelled()) {
            CombatManager.getInstance().startCombat(player);
        }
    }

}
