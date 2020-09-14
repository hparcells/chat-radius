package com.netlify.hparcells.chatradius;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ChatRadiusConfigHandler {
    private final ChatRadius plugin;

    public FileConfiguration chatRadiusConfig;
    public File chatRadiusConfigFile;

    public ChatRadiusConfigHandler(ChatRadius plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        chatRadiusConfigFile = new File(plugin.getDataFolder(), "config.yml");

        if(!chatRadiusConfigFile.exists()) {
            try {
                chatRadiusConfigFile.createNewFile();
                plugin.getConfig().options().copyDefaults(true);
                plugin.saveConfig();

                System.out.println("Created a config file.");
            }catch(IOException e) {
                System.out.println("Error when creating the config file.");
            }
        }

        reloadConfig();
    }

    public void reloadConfig() {
        chatRadiusConfig = YamlConfiguration.loadConfiguration(chatRadiusConfigFile);
    }
}