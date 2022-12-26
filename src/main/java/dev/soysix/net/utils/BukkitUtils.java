package dev.soysix.net.utils;

import com.google.common.base.Preconditions;
import dev.soysix.net.chat.CC;
import net.minecraft.server.v1_8_R3.MathHelper;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class BukkitUtils {

    public static boolean isOnline(OfflinePlayer target) {
        return target != null && target.isOnline();
    }

    public static int getRandomNumber(int random) {
        return new Random().nextInt(random);
    }

    public static void sendToConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(CC.translate(message));
    }

    public static List<String> getCompletions(String[] args, List<String> input) {
        return getCompletions(args, input, 80);
    }

    private static List<String> getCompletions(String[] args, List<String> input, int limit) {
        Preconditions.checkNotNull((Object) args);
        Preconditions.checkArgument(args.length != 0);
        String argument = args[args.length - 1];
        return input.stream().filter(string -> string.regionMatches(true, 0, argument, 0, argument.length())).limit(limit).collect(Collectors.toList());
    }
}