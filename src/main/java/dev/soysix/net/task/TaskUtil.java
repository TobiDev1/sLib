package dev.soysix.net.task;

import dev.soysix.net.sLib;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class TaskUtil {

    public void run(Runnable runnable) {
        sLib.INSTANCE.getPlugin().getServer().getScheduler().runTask(sLib.INSTANCE.getPlugin(), runnable);
    }

    public static void runTaskAsync(Runnable runnable) {
        sLib.INSTANCE.getPlugin().getServer().getScheduler().runTaskAsynchronously(sLib.INSTANCE.getPlugin(), runnable);
    }

    public static void runTaskLater(Runnable runnable, long delay) {
        sLib.INSTANCE.getPlugin().getServer().getScheduler().runTaskLater(sLib.INSTANCE.getPlugin(), runnable, delay);
    }

    public static void runTaskTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(sLib.INSTANCE.getPlugin(), delay, timer);
    }

    public static void runTaskTimer(Runnable runnable, long delay, long timer) {
        sLib.INSTANCE.getPlugin().getServer().getScheduler().runTaskTimer(sLib.INSTANCE.getPlugin(), runnable, delay, timer);
    }

    public static void runTask(Runnable runnable) {
        sLib.INSTANCE.getPlugin().getServer().getScheduler().runTask(sLib.INSTANCE.getPlugin(), runnable);
    }

    public static void runTimerAsync(Runnable runnable, long delay, long timer) {
        sLib.INSTANCE.getPlugin().getServer().getScheduler().runTaskTimerAsynchronously(sLib.INSTANCE.getPlugin(), runnable, delay, timer);
    }

    public static void runAsync(Runnable runnable) {
        sLib.INSTANCE.getPlugin().getServer().getScheduler().runTaskAsynchronously(sLib.INSTANCE.getPlugin(), runnable);
    }

    public BukkitTask asyncTimer(Callable callable, long delay, long value) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(sLib.INSTANCE.getPlugin(), callable::call, delay, value);
    }

    public static void runLaterAsync(Runnable runnable, long delay) {
        sLib.INSTANCE.getPlugin().getServer().getScheduler().runTaskLaterAsynchronously(sLib.INSTANCE.getPlugin(), runnable, delay);
    }

    public interface Callable {
        void call();
    }

}
