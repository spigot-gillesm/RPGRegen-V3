package com.gilles_m.rpg_regen;

import com.gilles_m.rpg_regen.listener.CombatListener;
import com.gilles_m.rpg_regen.listener.PlayerListener;
import com.gilles_m.rpg_regen.manager.ConfigurationHolder;
import com.gilles_m.rpg_regen.manager.ConfigurationLoader;
import com.github.spigot_gillesm.command_lib.CommandLib;
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

    private static void initialise(RPGRegen pluginInstance) {
        instance = pluginInstance;
        Formatter.PREFIX = "&f[&cRPG&aRegen&f]";
        FileUtils.PLUGIN_DATA_FOLDER_PATH = instance.getDataFolder().getPath();

        //Initialize commands
        CommandLib.initialize(pluginInstance);
    }

    @Override
    public void onEnable() {
        Formatter.info("Loading RPGRegen...");
        initialise(this);
        loadObjects();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CombatListener(), this);
        Formatter.info("&aDone!");
    }

    public void loadObjects() {
        try {
            ConfigurationLoader.getInstance().load();
        } catch (final IOException exception) {
            Formatter.error(exception.getMessage());
            Formatter.warning("Could not load configurations from the config.yml file. Does the file exist in the plugin folder?");
            Formatter.warning("Using default configuration.");
            this.configurationHolder = new ConfigurationHolder();
        }
    }

}
