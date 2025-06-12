package com.gilles_m.rpg_regen;

import com.gilles_m.rpg_regen.manager.ConfigurationHolder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Set;

public class PlayerRegenerator {

    @Getter
    private final Player player;

    private final double period;

    private final double amount;

    private final boolean useFoodLevel;

    private final int bonusLevelInterval;

    private final double bonusPerLevelInterval;

    private final boolean replaceMinecraftSystem;

    private final Set<String> whitelistedWorlds;

    private final Set<String> blacklistedWorlds;

    private BukkitTask task;

    public PlayerRegenerator(Player player, ConfigurationHolder configurationHolder) {
        this.player = player;
        this.period = configurationHolder.getPeriod();
        this.amount = configurationHolder.getAmount();
        this.useFoodLevel = configurationHolder.isUseFoodLevel();
        this.bonusLevelInterval = configurationHolder.getBonusLevelInterval();
        this.bonusPerLevelInterval = configurationHolder.getBonusPerLevelInterval();
        this.replaceMinecraftSystem = configurationHolder.isReplaceMinecraftSystem();
        this.whitelistedWorlds = configurationHolder.getWhitelistedWorlds();
        this.blacklistedWorlds = configurationHolder.getBlacklistedWorlds();
    }

    private void heal() {
        //Before doing anything, we check if the player is still alive
        if(player.isDead()) {
            return;
        }
        final double playerMaxHealth = player.getAttribute(Attribute.MAX_HEALTH).getValue();
        final double playerCurrentHealth = player.getHealth();
        final double playerFoodLevel = player.getFoodLevel();
        //Make sure the effective amount remains positive
        double effectiveAmount = Math.max(0, this.amount + getHealthBonus());

        //Check if player is already at full health
        if(playerCurrentHealth >= playerMaxHealth) {
            return;
        }
        if(useFoodLevel) {
            effectiveAmount *= playerFoodLevel / 20;
        }
        player.setHealth(Math.min(playerCurrentHealth + effectiveAmount, playerMaxHealth));
    }

    private double getHealthBonus() {
        if(bonusLevelInterval <= 0 || bonusPerLevelInterval == 0) {
            return 0;
        }
        final int multiplier = player.getLevel() / bonusLevelInterval;

        return multiplier * bonusPerLevelInterval;
    }

    /**
     * Check whether the player should regenerate health.
     */
    private boolean shouldHeal() {
        final World world = player.getWorld();

        if(!whitelistedWorlds.isEmpty() && !whitelistedWorlds.contains(world.getName())) {
            return false;
        }
        if(blacklistedWorlds.contains(world.getName())) {
            return false;
        }
        //If the plugin doesn't replace the Minecraft regen system -> check if natural regeneration is on
        //      If natural regen is on -> do not heal the player
        return replaceMinecraftSystem || !Boolean.TRUE.equals(world.getGameRuleValue(GameRule.NATURAL_REGENERATION));
    }

    public void start() {
        //Stop any already running runnable
        stop();
        task = new BukkitRunnable() {

            @Override
            public void run() {
                if(player == null || !Bukkit.getServer().getOnlinePlayers().contains(player)) {
                    cancel();

                    return;
                }
                if(shouldHeal()) {
                    heal();
                }
            }

        }.runTaskTimer(RPGRegen.getInstance(), 0, (long) period * 20);
    }

    public void stop() {
        if(task != null) {
            task.cancel();
        }
    }

}
