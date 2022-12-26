package dev.soysix.net.rank.impl;

import dev.soysix.net.rank.Rank;
import dev.phoenix.phoenix.PhoenixAPI;
import dev.phoenix.phoenix.profile.Profile;

import java.util.UUID;

public class PheonixCore implements Rank {

    @Override
    public String getName(UUID uuid) {
        Profile data = PhoenixAPI.INSTANCE.getProfile(uuid);
        return data == null ? "No Data" : data.getBestRank().getName();
    }

    @Override
    public String getPrefix(UUID uuid) {
        Profile data = PhoenixAPI.INSTANCE.getProfile(uuid);
        return data == null ? "No Data" : data.getBestRank().getPrefix();
    }

    @Override
    public String getSuffix(UUID uuid) {
        Profile data = PhoenixAPI.INSTANCE.getProfile(uuid);
        return data == null ? "No Data" : data.getBestRank().getSuffix();
    }

    @Override
    public String getColor(UUID uuid) {
        Profile data = PhoenixAPI.INSTANCE.getProfile(uuid);
        return data == null ? "No Data" : data.getBestRank().getColor() + data.getBestRank().getName();
    }

    @Override
    public int getWeight(UUID uuid) {
        Profile globalPlayer = PhoenixAPI.INSTANCE.getProfile(uuid);
        return globalPlayer == null ? 0 : globalPlayer.getPriority();
    }

}
