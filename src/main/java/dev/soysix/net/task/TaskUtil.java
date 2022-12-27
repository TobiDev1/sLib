package dev.soysix.net.task;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class TaskUtil {

    private final JavaPlugin plugin;

    public TaskUtil(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void run(Runnable runnable) {
        plugin.getServer().getScheduler().runTask(plugin, runnable);
    }

    public void runTaskAsync(Runnable runnable) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    public void runTaskLater(Runnable runnable, long delay) {
        plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
    }

    public void runTaskTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(plugin, delay, timer);
    }

    public void runTaskTimer(Runnable runnable, long delay, long timer) {
        plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, delay, timer);
    }

    public void runTask(Runnable runnable) {
        plugin.getServer().getScheduler().runTask(plugin, runnable);
    }

    public void runTimerAsync(Runnable runnable, long delay, long timer) {
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, timer);
    }

    public void runAsync(Runnable runnable) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    public BukkitTask asyncTimer(Callable callable, long delay, long value) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, callable::call, delay, value);
    }

    public void runLaterAsync(Runnable runnable, long delay) {
        plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
    }

    public interface Callable {
        void call();
    }

}
