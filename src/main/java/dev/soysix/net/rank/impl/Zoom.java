package dev.soysix.net.rank.impl;

import dev.soysix.net.rank.Rank;
import club.frozed.core.ZoomAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Zoom implements Rank {

    @Override
    public String getName(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        return ZoomAPI.getRankName(player);
    }

    @Override
    public String getPrefix(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        return ZoomAPI.getRankPrefix(player);
    }

    @Override
    public String getSuffix(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        return ZoomAPI.getRankSuffix(player);
    }

    @Override
    public String getColor(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        return ZoomAPI.getRankColor(player) + ZoomAPI.getRankName(player);
    }

    @Override
    public int getWeight(UUID uuid) {
        return 0;
    }
}
