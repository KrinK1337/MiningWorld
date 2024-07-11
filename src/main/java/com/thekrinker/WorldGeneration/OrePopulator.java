package com.thekrinker.WorldGeneration;

import com.thekrinker.MiningWorld;
import com.thekrinker.Utilities.ConfigManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;

public class OrePopulator extends BlockPopulator {
  
  // Load config options
  public int maxWorldHeight = this.configFile.getInt(".Max world height");
  public int minWorldHeight = this.configFile.getInt(".Min world height");

  public MiningWorld plugin;

  public ConfigManager configManager;
  
  public FileConfiguration configFile;
  
  public OrePopulator(MiningWorld plugin) {
    this.plugin = plugin;
    this.configManager = plugin.configManager;
    this.configFile = this.configManager.getConfig();
  }
  
  @Override
  public void populate(WorldInfo worldInfo, Random random, int cx, int cz, LimitedRegion region) {

    // Initial array of materials in config file
    List<String> keyList = new ArrayList<>(
      this.configFile.getConfigurationSection("Ores").getKeys(false)
    );

    // How many times to run ore spawning per chunk
    for(int times = 0; times < 10; times++){
      int x = random.nextInt(16) + cx * 16;
      int z = random.nextInt(16) + cz * 16;
      
      // Loop through all ores
      for (int oreIndex = 0; oreIndex < keyList.size(); oreIndex++) {
        String oreName = keyList.get(oreIndex);

        // Load ore properties
        int radius = this.configFile.getInt("Ores." + oreName + ".radius");
        int rarity = this.configFile.getInt("Ores." + oreName + ".rarity");

        // Spawn ore
        if (random.nextInt(100) < rarity) {
          Material ore = Material.getMaterial(oreName);

          int y = random.nextInt(maxWorldHeight + 64) - 64;

          // Radius of the ore vein
          for( int i = 0; i < radius; i++) {
            if (region.isInRegion(x, y, z) && y < maxWorldHeight) {
              Material materialType = region.getType(x, y, z);

              if (materialType == Material.DEEPSLATE || materialType == Material.STONE) {

                // Try to check if ore has DEEPSLATE variant
                if (materialType == Material.DEEPSLATE) {

                  if (ore == Material.DIRT) {
                    ore = Material.TUFF;
                  } else {
                    Material deepslateVariant = Material.getMaterial("DEEPSLATE_" + oreName);
    
                    if (deepslateVariant != null) {
                      ore = deepslateVariant;
                    }
                  }
                }

                region.setType(x, y, z, ore);

              }
            }

            switch (random.nextInt(6)) {
              case 0: 
                x--; 
                break;
              case 1: 
                x++; 
                break;
              case 2: 
                y--; 
                break;
              case 3:
                y++; 
                break;
              case 4: 
                z--; 
                break;
              case 5: 
                z++; 
                break;
            }
          }
        }
      }
    }
  }
}

