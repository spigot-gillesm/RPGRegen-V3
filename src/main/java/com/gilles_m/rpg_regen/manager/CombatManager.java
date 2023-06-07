package com.gilles_m.rpg_regen.manager;

import com.gilles_m.rpg_regen.CombatChecker;
import com.gilles_m.rpg_regen.RPGRegen;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class CombatManager {

    private static final CombatManager INSTANCE = new CombatManager();

    private final Set<CombatChecker> registeredCombatChecker = new HashSet<>();

    private CombatManager() {
        //Prevent outside instantiation
    }

    /**
     * Put the player in combat.
     *
     * @param player the player
     */
    public void startCombat(final Player player) {
        getCombatChecker(player).start(RPGRegen.getInstance().getConfigurationHolder().getCombatDuration());
    }

    /**
     * Get the combat checker of the given player or create one of none exist.
     *
     * @param player the player
     * @return the player's combat checker
     */
    public CombatChecker getCombatChecker(final Player player) {
        return registeredCombatChecker.stream()
                .filter(combatChecker -> combatChecker.getPlayer().equals(player))
                .findFirst()
                .orElseGet(() -> {
                    //Create and register a new combat checker if none exist
                    final var combatChecker = new CombatChecker(player);
                    registeredCombatChecker.add(combatChecker);

                    return combatChecker;
                });
    }

    public static CombatManager getInstance() {
        return INSTANCE;
    }

}
