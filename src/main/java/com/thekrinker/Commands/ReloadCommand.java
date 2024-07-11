package com.thekrinker.Commands;

import com.thekrinker.MiningWorld;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {

    MiningWorld plugin;

    public ReloadCommand(MiningWorld plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        //MiningWorldReload command - Reloads the MiningWorld config.yml
        if (sender instanceof Player){
            Player p = (Player) sender;
            p.sendMessage(ChatColor.LIGHT_PURPLE + "MiningWorld Config reloaded!");
        }

        plugin.getLogger().info("MiningWorld Config reloaded!");
        
        plugin.reloadConfig();
        return true;
    }
}
