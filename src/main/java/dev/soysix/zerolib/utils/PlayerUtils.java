package dev.soysix.zerolib.utils;

import dev.soysix.zerolib.chat.CC;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.UUID;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerUtils {
   public static Location teleportToTop(Location loc) {
      return new Location(loc.getWorld(), loc.getX(), (double)loc.getWorld().getHighestBlockYAt(loc.getBlockX(), loc.getBlockZ()), loc.getZ(), loc.getYaw(), loc.getPitch());
   }

   public static boolean hasAvailableSlot(Player player) {
      Inventory inv = player.getInventory();
      ItemStack[] var2 = inv.getContents();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ItemStack item = var2[var4];
         if (item == null) {
            return true;
         }
      }

      return false;
   }

   public static String getTps() {
      return TPSUtils.getTPS();
   }

   public static String getUptime() {
      long serverTime = ManagementFactory.getRuntimeMXBean().getStartTime();
      String text = DurationFormatUtils.formatDurationWords(System.currentTimeMillis() - serverTime, true, true);
      return text;
   }

   public static long getMaxMemory() {
      long text = Runtime.getRuntime().maxMemory() / 1024L / 1024L;
      return text;
   }

   public static long getAllMemory() {
      long text = Runtime.getRuntime().totalMemory() / 1024L / 1024L;
      return text;
   }

   public static long getFreeMemory() {
      long text = Runtime.getRuntime().freeMemory() / 1024L / 1024L;
      return text;
   }

   public static void sendPlayerSound(Player p, String sound) {
      if (!sound.equalsIgnoreCase("none") && sound != null) {
         p.playSound(p.getLocation(), Sound.valueOf(sound), 2.0F, 2.0F);
      }

   }

   public static int getPing(Player who) {
      try {
         String bukkitversion = Bukkit.getServer().getClass().getPackage().getName().substring(23);
         Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + bukkitversion + ".entity.CraftPlayer");
         Object handle = craftPlayer.getMethod("getHandle").invoke(who);
         return (Integer)handle.getClass().getDeclaredField("ping").get(handle);
      } catch (Exception var4) {
         return -1;
      }
   }

   public static boolean checkPlayerVote(UUID uuid, String server) {
      String pageRequest = "https://api.namemc.com/server/" + server + "/likes?profile=" + uuid.toString();

      try {
         URL url = new URL(pageRequest);
         ArrayList<Object> lines = new ArrayList();
         URLConnection urlConnection = url.openConnection();
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

         String line;
         while((line = bufferedReader.readLine()) != null) {
            lines.add(line);
         }

         return lines.contains("true");
      } catch (IOException var8) {
         Bukkit.getConsoleSender().sendMessage("§cAn error occurred while checking vote on name-mc");
         return false;
      }
   }

   public static String getPingWithColor(int ping) {
      if (ping <= 40) {
         return CC.GREEN + ping;
      } else if (ping <= 70) {
         return CC.YELLOW + ping;
      } else {
         return ping <= 100 ? CC.GOLD + ping : CC.RED + ping;
      }
   }

   public static String getColorHealth(double health) {
      if (health > 15.0D) {
         return CC.GREEN + convertHealth(health);
      } else if (health > 10.0D) {
         return CC.GOLD + convertHealth(health);
      } else {
         return health > 5.0D ? CC.YELLOW + convertHealth(health) : CC.RED + convertHealth(health);
      }
   }

   public static double convertHealth(double health) {
      double dividedHealth = health / 2.0D;
      if (dividedHealth % 1.0D == 0.0D) {
         return dividedHealth;
      } else if (dividedHealth % 0.5D == 0.0D) {
         return dividedHealth;
      } else if (dividedHealth - (double)((int)dividedHealth) > 0.5D) {
         return (double)((int)dividedHealth + 1);
      } else {
         return dividedHealth - (double)((int)dividedHealth) > 0.25D ? (double)((int)dividedHealth) + 0.5D : (double)((int)dividedHealth);
      }
   }

   public static String getHeartIcon() {
      return StringEscapeUtils.unescapeJava("❤");
   }
}
