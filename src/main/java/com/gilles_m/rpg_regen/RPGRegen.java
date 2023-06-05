package com.gilles_m.rpg_regen;

import com.github.spigot_gillesm.format_lib.Formatter;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RPGRegen extends JavaPlugin {

    @Getter
    private static RPGRegen instance;

    private static void initialise(final RPGRegen pluginInstance) {
        instance = pluginInstance;
        Formatter.PREFIX = "&f[&cRPG&aRegen&f]";
    }

    @Override
    public void onEnable() {
        Formatter.info("Loading RPGRegen...");
        initialise(this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        Formatter.info("&aDone!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
