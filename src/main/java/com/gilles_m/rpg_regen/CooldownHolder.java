package com.gilles_m.rpg_regen;

import org.bukkit.entity.Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * The CooldownHolder class keeps track of all the entities cooldown by storing their id for a certain duration.
 */
public class CooldownHolder {

    private static final CooldownHolder INSTANCE = new CooldownHolder();

    private final Set<Integer> cooldowns = new HashSet<>();

    private CooldownHolder() {
        //Prevent outside instantiation
    }

    /**
     * (re) Start the cooldown for the entity for the given duration.
     *
     * @param entity the entity
     * @param cooldown the cooldown in seconds
     */
    public void startCooldown(final Entity entity, final int cooldown) {
        cooldowns.add(entity.getEntityId());
        RPGRegen.getInstance().getServer().getScheduler().runTaskLater(RPGRegen.getInstance(),
                () -> cooldowns.remove(entity.getEntityId()),
                cooldown * 20L);
    }

    public boolean isInCooldown(final Entity entity) {
        return cooldowns.contains(entity.getEntityId());
    }

    public static CooldownHolder getInstance() {
        return INSTANCE;
    }

}
