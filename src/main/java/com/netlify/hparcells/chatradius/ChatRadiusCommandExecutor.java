package com.netlify.hparcells.chatradius;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatRadiusCommandExecutor implements CommandExecutor {
    private ChatRadius plugin;

    public ChatRadiusCommandExecutor(ChatRadius plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }
}
