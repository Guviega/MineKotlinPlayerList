package com.guviega;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class MineKotlinPlayerList extends JavaPlugin {

    private final long PING_UPDATE_TICKRATE = 20L, COORDS_UPDATE_TICKRATE = 4L;

    @Override
    public void onEnable() {
        getLogger().info("MineKotlinPlayerList started to run!");
        updatePlayerList();
    }

    @Override
    public void onDisable() {
        getLogger().info("MineKotlinPlayerList stop running.");
    }

    private void updatePlayerList() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers())
                    p.setPlayerListHeader("Ping: "+p.getPing()+" ms");
            }
        }.runTaskTimer(this, 0L, PING_UPDATE_TICKRATE);
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Location loc = p.getLocation();
                    p.setPlayerListFooter("X: "+loc.getBlockX()+" Y: "+loc.getBlockY()+" Z: "+loc.getBlockZ());
                }
            }
        }.runTaskTimer(this, 0L, COORDS_UPDATE_TICKRATE);
    }
}
