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
        chatRadiusConfigHandler.loadConfig();
        radius = Double.parseDouble(chatRadiusConfigHandler.chatRadiusConfig.getString("radius"));

        getLogger().info("Chat Radius loaded.");
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
                Math.pow((recipient.getLocation().getX() - sender.getLocation().getX()), 2)
                + Math.pow((recipient.getLocation().getY() - sender.getLocation().getY()), 2)
            );

            getLogger().info("h: " + recipientDistance);

            // Remove the recipient if they are out of the radius.
            if(recipientDistance < radius) {
                event.getRecipients().remove(recipient);
            }
        }
    }
}
