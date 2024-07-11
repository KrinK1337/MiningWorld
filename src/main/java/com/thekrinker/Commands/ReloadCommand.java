package com.thekrinker.Commands;

import com.thekrinker.MiningWorld;
import com.thekrinker.Utilities.ConfigManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {

    ConfigManager configManager;

    MiningWorld plugin;

    public ReloadCommand(MiningWorld plugin) {
        this.plugin = plugin;
        this.configManager = plugin.configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        //MiningWorldReload command - Reloads the MiningWorld config.yml
        if (sender instanceof Player){
            Player p = (Player) sender;
            p.sendMessage(ChatColor.LIGHT_PURPLE + "MiningWorld Config reloaded!");
        }

        plugin.getLogger().info("MiningWorld Config reloaded!");
        
        configManager.reloadConfig();
        return true;
    }
}
