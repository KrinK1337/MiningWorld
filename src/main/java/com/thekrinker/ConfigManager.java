package com.thekrinker;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
  private MiningWorld plugin;
  
  private FileConfiguration data = null;
  
  private File dataFile = null;
  
  public ConfigManager(MiningWorld plugin) {
    this.plugin = plugin;
  }
  
  public FileConfiguration getConfig() {
    if (this.data == null)
      reloadConfig(); 
    return this.data;
  }
  
  public void reloadConfig() {
    if (this.data == null)
      this.dataFile = new File(this.plugin.getDataFolder(), "config.yml"); 
    this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.dataFile);
    try {
      Reader defConfigStream = new InputStreamReader(this.plugin.getResource("config.yml"), "UTF8");
      if (defConfigStream != null) {
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        this.data.setDefaults((Configuration)defConfig);
      } 
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } 
  }
  
  public void resetDefaultConfig() {
    this.data = null;
    this.dataFile = null;
    registerConfig();
  }
  
  public void saveConfig() {
    try {
      this.data.save(this.dataFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void registerConfig() {
    this.dataFile = new File(this.plugin.getDataFolder(), "config.yml");
    if (!this.dataFile.exists()) {
      getConfig().options().copyDefaults(true);
      saveConfig();
    } 
  }
}