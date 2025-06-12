package com.gilles_m.rpg_regen;

import com.gilles_m.rpg_regen.event.PlayerLeaveCombatEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class CombatChecker {

    @Getter
    private final Player player;

    private BukkitTask task;

    public CombatChecker(final Player player) {
        this.player = player;
    }

    public void start(final double delay) {
        if(task != null) {
            task.cancel();
        }
        task = RPGRegen.getInstance().getServer().getScheduler().runTaskLater(RPGRegen.getInstance(),
                () -> Bukkit.getServer().getPluginManager().callEvent(new PlayerLeaveCombatEvent(player)),
                (long) delay * 20L);
    }

}
