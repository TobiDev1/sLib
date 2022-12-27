package dev.soysix.net.rank.impl;

import dev.soysix.net.rank.Rank;
import me.quartz.hestia.HestiaAPI;

import java.util.UUID;

public class HestiaCore implements Rank {

    @Override
    public String getName(UUID uuid) {
        return HestiaAPI.instance.getRank(uuid);
    }

    @Override
    public String getPrefix(UUID uuid) {
        return HestiaAPI.instance.getRankPrefix(uuid);
    }

    @Override
    public String getSuffix(UUID uuid) {
        return HestiaAPI.instance.getRankSuffix(uuid);
    }

    @Override
    public String getColor(UUID uuid) {
        return HestiaAPI.instance.getRank(uuid);
    }

    @Override
    public int getWeight(UUID uuid) {
        return 0;
    }
}
