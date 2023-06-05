package com.gilles_m.rpg_regen;

import org.bukkit.entity.Player;

import java.util.HashSet;
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
        registeredRegenerators.stream()
                .filter(regenerator -> regenerator.getPlayer().equals(player))
                .findFirst()
                //Check if a regenerator already exists for the player before creating one
                .ifPresentOrElse(
                        PlayerRegenerator::start,
                        () -> {
                            final var regenerator = PlayerRegenerator.newBuilder(player)
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
        registeredRegenerators.stream()
                .filter(regenerator -> regenerator.getPlayer().equals(player))
                .findFirst()
                .ifPresent(PlayerRegenerator::stop);
    }

    public static RegeneratorManager getInstance() {
        return INSTANCE;
    }

}
