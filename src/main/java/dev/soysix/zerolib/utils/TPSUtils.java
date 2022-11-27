package dev.soysix.zerolib.utils;

import org.bukkit.scheduler.BukkitRunnable;

public class TPSUtils extends BukkitRunnable {
   private static int TICK_COUNT = 0;
   private static final long[] TICKS = new long[600];

   public static String getTPS() {
      return getTPS(100);
   }

   public static String getTPS(int ticks) {
      if (TICK_COUNT < ticks) {
         return "20.0*";
      } else {
         int target = (TICK_COUNT - 1 - ticks) % TICKS.length;
         long elapsed = System.currentTimeMillis() - TICKS[target];
         return (double)((long)ticks / elapsed) / 1000.0D + "";
      }
   }

   public void run() {
      TICKS[TICK_COUNT % TICKS.length] = System.currentTimeMillis();
      ++TICK_COUNT;
   }
}
