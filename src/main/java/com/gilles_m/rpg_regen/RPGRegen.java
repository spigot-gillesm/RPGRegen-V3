package com.gilles_m.rpg_regen;

import com.github.spigot_gillesm.file_utils.FileUtils;
import com.github.spigot_gillesm.format_lib.Formatter;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class RPGRegen extends JavaPlugin {

    @Getter
    private static RPGRegen instance;

    @Setter
    @Getter
    private ConfigurationHolder configurationHolder;

    private static void initialise(final RPGRegen pluginInstance) {
        instance = pluginInstance;
        Formatter.PREFIX = "&f[&cRPG&aRegen&f]";
        FileUtils.PLUGIN_DATA_FOLDER_PATH = instance.getDataFolder().getPath();
    }

    @Override
    public void onEnable() {
        Formatter.info("Loading RPGRegen...");
        initialise(this);

        try {
            ConfigurationLoader.getInstance().load();
        } catch (IOException e) {
            Formatter.warning("Could not load configurations from the config.yml file. Does the file exist in the plugin folder?");
            Formatter.warning("Using default configuration.");
            this.configurationHolder = new ConfigurationHolder();
        }
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        Formatter.info("&aDone!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
