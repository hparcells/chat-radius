package com.netlify.hparcells.chatradius;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatRadius extends JavaPlugin implements Listener {
    public ChatRadiusConfigHandler chatRadiusConfigHandler = new ChatRadiusConfigHandler(this);

    public double radius;

    @Override
    public void onEnable() {
        // Load the events.
        getServer().getPluginManager().registerEvents(this, this);

        // Load the config.
        chatRadiusConfigHandler.loadConfig();
        radius = Double.parseDouble(chatRadiusConfigHandler.chatRadiusConfig.getString("radius"));

        getLogger().info("Chat Radius loaded." + radius);
    }

    @Override
    public void onDisable() {
        getLogger().info("Chat Radius disabled.");
    }

    @EventHandler
    public void asyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        // The person who sent the message.
        Player sender = event.getPlayer();

        // Remove players out of range.
        for(Player recipient : Bukkit.getOnlinePlayers()) {
            // Check distance to sender.
            double recipientDistance = Math.sqrt(
                Math.pow(recipient.getLocation().getX() - sender.getLocation().getX(), 2)
                + Math.pow(recipient.getLocation().getZ() - sender.getLocation().getZ(), 2)
            );

            // Remove the recipient if they are out of the radius.
            if(recipientDistance > radius && !recipient.hasPermission("chatradius.spy")) {
                event.getRecipients().remove(recipient);
                recipient.kickPlayer("test");
            }
        }
    }
}
