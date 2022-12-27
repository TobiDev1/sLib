package dev.soysix.net.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public final class CC {

    public static final String SB_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------------";
    public static final String MENU_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------------------";
    public static final String DARK_MENU_BAR = ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------";
    public static final String CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------------------------------";

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> translate(List<String> list) {
        return (List<String>)list.stream().map(CC::translate).collect(Collectors.toList());
    }

    public static String strip(String text) {
        return ChatColor.stripColor(text);
    }

    public static String text(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void sender(CommandSender sender, String text) {
        sender.sendMessage(translate(text));
    }

    public static void message(Player player, String text) {
        player.sendMessage(translate(text));
    }

    public static void broadcast(String text) {
        Bukkit.broadcastMessage(translate(text));
    }

    public static void log(String text) {
        Bukkit.getConsoleSender().sendMessage(translate(text));
    }

    public static List<String> translateFromArray(List<String> text) {
        return text.stream().map(CC::translate).collect(Collectors.toList());
    }
}
