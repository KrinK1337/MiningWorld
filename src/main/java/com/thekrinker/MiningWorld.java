package com.thekrinker;

import java.util.logging.Level;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiningWorld extends JavaPlugin {

  public ConfigManager configManager;

  public OrePopulator material;

  @Override
  public void onEnable() {
      getLogger().log(Level.INFO, "MiningWorld Plugin Enabled! :3");
      this.configManager = new ConfigManager(this);
      this.configManager.registerConfig();
  }

  @Override
  public void onDisable() {
      getLogger().log(Level.INFO, "MiningWorld Plugin Disabled :(");
  }

  public void reloadConfig() {
    this.configManager.resetDefaultConfig();
  }
  @Override
  public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
    return new CustomChunkGenerator(this);
  }
  
}