package com.thekrinker;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class OreManager {
  private MiningWorld plugin;
  
  private FileConfiguration data = null;
  
  private File dataFile = null;
  
  public OreManager(MiningWorld plugin) {
    this.plugin = plugin;
  }
  
  public FileConfiguration getMessages() {
    if (this.data == null)
      reloadMessages(); 
    return this.data;
  }
  
  public void reloadMessages() {
    if (this.data == null)
      this.dataFile = new File(this.plugin.getDataFolder(), "oreConfig.yml"); 
    this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.dataFile);
    try {
      Reader defConfigStream = new InputStreamReader(this.plugin.getResource("oreConfig.yml"), "UTF8");
      if (defConfigStream != null) {
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        this.data.setDefaults((Configuration)defConfig);
      } 
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } 
  }
  
  public void resetDefaultMessages() {
    this.data = null;
    this.dataFile = null;
    registerMessages();
  }
  
  public void saveMessages() {
    try {
      this.data.save(this.dataFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void registerMessages() {
    this.dataFile = new File(this.plugin.getDataFolder(), "oreConfig.yml");
    if (!this.dataFile.exists()) {
      getMessages().options().copyDefaults(true);
      saveMessages();
    } 
  }
}