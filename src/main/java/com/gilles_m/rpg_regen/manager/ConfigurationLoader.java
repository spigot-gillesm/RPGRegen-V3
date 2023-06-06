package com.gilles_m.rpg_regen.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gilles_m.rpg_regen.RPGRegen;
import com.github.spigot_gillesm.file_utils.FileUtils;

import java.io.IOException;

public class ConfigurationLoader {

    private static final ConfigurationLoader INSTANCE = new ConfigurationLoader();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new YAMLFactory());

    private static final String CONFIGURATION_FILE_PATH = "config.yml";

    private ConfigurationLoader() {
        //Prevent outside instantiation
    }

    public void load() throws IOException {
        var configurationHolder = new ConfigurationHolder();

        if(FileUtils.doResourceExists(CONFIGURATION_FILE_PATH)) {
            final var file = FileUtils.getResource(CONFIGURATION_FILE_PATH);
            //Read the content of the configuration and store it in configurationHolder variable
            configurationHolder = OBJECT_MAPPER.readValue(file, ConfigurationHolder.class);
        } else {
            RPGRegen.getInstance().getConfig().options().copyDefaults();
            RPGRegen.getInstance().saveDefaultConfig();
        }
        RPGRegen.getInstance().setConfigurationHolder(configurationHolder);
    }

    public static ConfigurationLoader getInstance() {
        return INSTANCE;
    }

}
