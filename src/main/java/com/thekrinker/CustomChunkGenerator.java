package com.thekrinker;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {

    public MiningWorld plugin;

    public OrePopulator material;

    public CustomChunkGenerator(MiningWorld plugin) {
        this.plugin = plugin;
    }

    @Override
    public void generateSurface(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
                for(int yDeepslate = 1; yDeepslate < 100; yDeepslate++){
                    chunkData.setBlock(x, yDeepslate, z, Material.DEEPSLATE);
                }
                for(int yStone = 100; yStone <=250; yStone++){
                    chunkData.setBlock(x, yStone, z, Material.STONE);
                }
            }
        }
    }

    @Override
    public void generateBedrock(WorldInfo info, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
            chunkData.setBlock(x, 0, z, Material.BEDROCK);
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

    public boolean shouldGenerateBedrock() {
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