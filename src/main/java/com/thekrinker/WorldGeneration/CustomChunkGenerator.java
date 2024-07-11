package com.thekrinker.WorldGeneration;

import com.thekrinker.MiningWorld;
import com.thekrinker.Utilities.ConfigManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

public class CustomChunkGenerator extends ChunkGenerator {

    public MiningWorld plugin;

    public ConfigManager configManager;

    public FileConfiguration configFile;

    public OrePopulator orePopulator;

    public CustomChunkGenerator(MiningWorld plugin) {
        this.plugin = plugin;
        this.configManager = plugin.configManager;
        this.configFile = this.configManager.getConfig();
    }



    @Override
    public void generateSurface(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        
        // DEEPSLATE Y level
        int deepslateY = this.configFile.getInt(".Deepslate Y");
        
        // Checks if Flat bedrock is enabled in config
        boolean flatBedrock = this.configFile.getBoolean(".Flat Bedrock");
        
        // Generates BEDROCK, DEEPSLATE and STONE
        for(int x = 0; x < 16; x++) {

            for(int z = 0; z < 16; z++) {

                for(int y = orePopulator.minWorldHeight; y <= orePopulator.maxWorldHeight; y++) {
                    Material blockMaterial = Material.STONE;
                    int maxDeepslateWithNoise = deepslateY + random.nextInt(5);

                    if(y < maxDeepslateWithNoise) {
                        blockMaterial = Material.DEEPSLATE;
                    }

                    chunkData.setBlock(x, y, z, blockMaterial);
                }

                if (flatBedrock) {
                    chunkData.setBlock(x, orePopulator.minWorldHeight, z, Material.BEDROCK);
                } else {
                    chunkData.setBlock(x, orePopulator.minWorldHeight, z, Material.BEDROCK);

                    int maxBedrockNoiseHeight = orePopulator.minWorldHeight + 4;

                    for(int bedrockY = orePopulator.minWorldHeight; bedrockY < maxBedrockNoiseHeight; bedrockY++) {
                        if (random.nextInt(2) == 1) {
                            chunkData.setBlock(x, bedrockY, z, Material.BEDROCK);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
    return Arrays.asList(new OrePopulator(plugin));
    }

    public boolean shouldGenerateCaves() {
        return true;
    }

    public boolean shouldGenerateDecorations() {
        return false;
    }

    public boolean shouldGenerateStructures() {
        return false;
    }

    public boolean shouldGenerateDungeons() {
        return false;
    }

    public boolean shouldGenerateMineshafts() {
        return false;
    }
}