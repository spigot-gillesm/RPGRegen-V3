package com.gilles_m.rpg_regen.manager;

import com.gilles_m.rpg_regen.PlayerRegenerator;
import com.gilles_m.rpg_regen.RPGRegen;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RegeneratorManager {

    private static final RegeneratorManager INSTANCE = new RegeneratorManager();

    private final Set<PlayerRegenerator> registeredRegenerators = new HashSet<>();

    private RegeneratorManager() {
        //Prevent outside instantiation
    }

    /**
     * Start the player regenerator of the given player. Creates one if none exist.
     *
     * @param player the player
     */
    public void start(final Player player) {
        getRegenerator(player)
                //Check if a regenerator already exists for the player before creating one
                .ifPresentOrElse(
                        PlayerRegenerator::start,
                        () -> {
                            final ConfigurationHolder configuration = RPGRegen.getInstance().getConfigurationHolder();
                            //Create the regenerator using the config values
                            final var regenerator = PlayerRegenerator.newBuilder(player)
                                    .period(configuration.getPeriod())
                                    .amount(configuration.getAmount())
                                    .useFoodLevel(configuration.isUseFoodLevel())
                                    .build();
                            regenerator.start();
                            registeredRegenerators.add(regenerator);
                        });
    }

    /**
     * Stop the player regenerator of the given player if one exists for that player.
     *
     * @param player the player
     */
    public void stop(final Player player) {
        getRegenerator(player).ifPresent(PlayerRegenerator::stop);
    }

    /**
     * Stop and clear existing regenerators and restart one for each online player.
     */
    public void reload() {
        for(final PlayerRegenerator regenerator : registeredRegenerators) {
            regenerator.stop();
        }
        registeredRegenerators.clear();

        for(final Player player : Bukkit.getServer().getOnlinePlayers()) {
            start(player);
        }
    }

    /**
     * Get the regenerator of the given player if one exists.
     *
     * @param player the player
     * @return an optional of PlayerRegenerator
     */
    public Optional<PlayerRegenerator> getRegenerator(final Player player) {
        return registeredRegenerators.stream()
                .filter(regenerator -> regenerator.getPlayer().equals(player))
                .findFirst();
    }

    public static RegeneratorManager getInstance() {
        return INSTANCE;
    }

}
