package com.thekrinker;

import com.thekrinker.Utilities.ConfigManager;
import com.thekrinker.WorldGeneration.CustomChunkGenerator;
import com.thekrinker.Commands.ReloadCommand;

import java.util.logging.Level;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiningWorld extends JavaPlugin {

  public ConfigManager configManager;

  @Override
  public void onEnable() {
      getLogger().log(Level.INFO, "MiningWorld Plugin Enabled! :3");
      
      this.configManager = new ConfigManager(this);
      this.configManager.registerConfig();

      getCommand("mwreload").setExecutor(new ReloadCommand(this));

  }

  public void reloadConfig() {
    this.configManager.resetDefaultConfig();
  }

  @Override
  public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
    return new CustomChunkGenerator(this);
  }
  
}