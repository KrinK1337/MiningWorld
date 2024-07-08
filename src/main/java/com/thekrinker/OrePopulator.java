package com.thekrinker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;

public class OrePopulator extends BlockPopulator {
  MiningWorld plugin;

  OreManager oreManager;
  
  FileConfiguration oreConfig;
  
  public OrePopulator(MiningWorld plugin) {
    this.plugin = plugin;
    this.oreManager = plugin.oreManager;
    this.oreConfig = this.oreManager.getMessages();
  }
  
  @Override
  public void populate(WorldInfo worldInfo, Random random, int cx, int cz, LimitedRegion region) {

    List<String> keyList = new ArrayList<>(
      this.oreConfig.getConfigurationSection("config").getKeys(false)
    );

    for(int times = 0; times < 3; times++){
      int x = random.nextInt(16) + cx * 16;
      int z = random.nextInt(16) + cz * 16;
      
      // Loop through all ores
      for (int oreIndex = 0; oreIndex < keyList.size(); oreIndex++) {
        String oreName = keyList.get(oreIndex);

        // Load properties
        int height = this.oreConfig.getInt("config." + oreName + ".height");
        int radius = this.oreConfig.getInt("config." + oreName + ".radius");
        int rarity = this.oreConfig.getInt("config." + oreName + ".rarity");

        // Spawn ore
        if (random.nextInt(100) < rarity) {
          Material ore = Material.getMaterial(oreName);

          int y = random.nextInt(height);

          for( int i = 0; i < radius; i++) {
            if (region.isInRegion(x, y, z)) {
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

