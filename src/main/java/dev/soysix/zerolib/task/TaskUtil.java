package dev.soysix.zerolib.task;

import dev.soysix.zerolib.ZeroLib;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskUtil {
   public static void run(Runnable runnable) {
      ZeroLib.INSTANCE.getPlugin().getServer().getScheduler().runTask(ZeroLib.INSTANCE.getPlugin(), runnable);
   }

   public static void runTimer(Runnable runnable, long delay, long timer) {
      ZeroLib.INSTANCE.getPlugin().getServer().getScheduler().runTaskTimer(ZeroLib.INSTANCE.getPlugin(), runnable, delay, timer);
   }

   public static void runTimer(BukkitRunnable runnable, long delay, long timer) {
      runnable.runTaskTimer(ZeroLib.INSTANCE.getPlugin(), delay, timer);
   }

   public static void runTimerAsync(BukkitRunnable runnable, long delay, long timer) {
      runnable.runTaskTimerAsynchronously(ZeroLib.INSTANCE.getPlugin(), delay, timer);
   }

   public static void runLater(Runnable runnable, long delay) {
      ZeroLib.INSTANCE.getPlugin().getServer().getScheduler().runTaskLater(ZeroLib.INSTANCE.getPlugin(), runnable, delay);
   }

   public static void runLaterAsync(Runnable runnable, long delay) {
      ZeroLib.INSTANCE.getPlugin().getServer().getScheduler().runTaskLaterAsynchronously(ZeroLib.INSTANCE.getPlugin(), runnable, delay);
   }

   public static void runTaskTimerAsynchronously(Runnable runnable, int delay) {
      ZeroLib.INSTANCE.getPlugin().getServer().getScheduler().runTaskTimerAsynchronously(ZeroLib.INSTANCE.getPlugin(), runnable, (long)(20 * delay), (long)(20 * delay));
   }

   public static void runAsync(Runnable runnable) {
      ZeroLib.INSTANCE.getPlugin().getServer().getScheduler().runTaskAsynchronously(ZeroLib.INSTANCE.getPlugin(), runnable);
   }
}
