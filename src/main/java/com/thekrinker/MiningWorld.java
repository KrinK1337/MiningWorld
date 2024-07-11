package com.thekrinker;

import com.thekrinker.WorldGeneration.CustomChunkGenerator;
import com.thekrinker.Commands.ReloadCommand;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiningWorld extends JavaPlugin {

  public int maxWorldHeight;
  public int minWorldHeight;

  private File configFile;

  @Override
  public void onEnable() {
      getLogger().log(Level.INFO, "MiningWorld Plugin Enabled! :3");

      this.getConfig();
      this.registerConfig();
      this.reloadConfig();

      getCommand("mwreload").setExecutor(new ReloadCommand(this));

      // Load config options
      maxWorldHeight = this.getConfig().getInt("Max-World-Height");
      minWorldHeight = this.getConfig().getInt("Min-World-Height");
  }

  public void reloadMiningWorldConfig() {
    reloadConfig();
  }

  public void registerConfig() {
    this.configFile = new File(this.getDataFolder(), "config.yml");
    if (!this.configFile.exists()) {
      getConfig().options().copyDefaults(true);
      saveConfig();
    }
  }

  @Override
  public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
    return new CustomChunkGenerator(this);
  }
  
}