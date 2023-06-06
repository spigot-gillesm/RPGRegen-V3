package com.gilles_m.rpg_regen;

import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class PlayerRegenerator {

    @Getter
    private final Player player;

    private final float period;

    private final float amount;

    private final boolean useFoodLevel;

    private BukkitTask task;

    private PlayerRegenerator(final Builder builder) {
        this.player = builder.player;
        this.period = builder.period;
        this.amount = builder.amount;
        this.useFoodLevel = builder.useFoodLevel;
    }

    private void heal() {
        //Before doing anything, we check if the player is still alive
        if(player.isDead()) {
            return;
        }
        final float playerMaxHealth = (float) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        final float playerCurrentHealth = (float) player.getHealth();
        final float playerFoodLevel = player.getFoodLevel();
        float effectiveAmount = this.amount;

        //Check if player is already at full health
        if(playerCurrentHealth >= playerMaxHealth) {
            return;
        }
        if(useFoodLevel) {
            effectiveAmount *= playerFoodLevel / 20;
        }
        player.setHealth(Math.min(playerCurrentHealth + effectiveAmount, playerMaxHealth));
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
                heal();
            }
        }.runTaskTimer(RPGRegen.getInstance(), 0, (long) period * 20);
    }

    public void stop() {
        if(task != null) {
            task.cancel();
        }
    }

    public static Builder newBuilder(final Player player) {
        return new Builder(player);
    }

    public static class Builder {

        private final Player player;

        private float period = 2;

        private float amount = 1;

        private boolean useFoodLevel = true;

        private Builder(final Player player) {
            this.player = player;
        }

        public Builder period(final float period) {
            this.period = period;
            return this;
        }

        public Builder amount(final float amount) {
            this.amount = amount;
            return this;
        }

        public Builder useFoodLevel(final boolean useFoodLevel) {
            this.useFoodLevel = useFoodLevel;
            return this;
        }

        public PlayerRegenerator build() {
            if(period <= 0) {
                throw new IllegalArgumentException("The period must be strictly positive");
            }
            if(amount < 0) {
                throw new IllegalArgumentException("The amount must be positive");
            }
            return new PlayerRegenerator(this);
        }

    }

}
