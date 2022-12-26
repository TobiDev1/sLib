package dev.soysix.net.hotbar;

import com.google.common.base.Preconditions;
import dev.soysix.net.hotbar.construct.IHotbar;
import dev.soysix.net.hotbar.construct.IHotbarItem;
import dev.soysix.net.hotbar.listener.HotbarListener;
import dev.soysix.net.sLib;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class HotbarHandler {
    private static Map<UUID, IHotbar> playersHotbarMap;
    private static boolean initialized;

    public static void init() {
        Preconditions.checkState(!initialized, "Hotbar Handler is already initialized.");
        playersHotbarMap = new ConcurrentHashMap<>();
        Bukkit.getPluginManager().registerEvents(new HotbarListener(), sLib.INSTANCE.getPlugin());
        initialized = true;
    }

    /**
     *
     * @param uuid - Players id.
     * @return - If the player actually has a hotbar.
     */
    public static boolean doesPlayerHaveHotbar(UUID uuid) {
        return playersHotbarMap.containsKey(uuid);
    }

    /**
     *
     * @param uuid - Players id.
     * @return - Hotbar they are currently using.
     */
    public static IHotbar getPlayerHotbar(UUID uuid) {
        return playersHotbarMap.get(uuid);
    }

    /**
     *
     * @param player - player instance.
     * @param hotbar - hotbar instance.
     * @param clearHotbar - should clear the players hotbar.
     */

    public static void applyHotbar01(Player player, IHotbar hotbar, boolean clearHotbar) {
        if (clearHotbar)
            clearHotbar(player.getInventory());
        List<Integer> slotsInUse = new ArrayList<>();
        for (IHotbarItem item : hotbar.getHotbarItems()) {
            // if we have same slot items, the one that comes after will override the first
            // so to stop this we tell the developer there is an error, then we continue.
            if (slotsInUse.contains(item.getSlot())) {
                System.err.println("Hotbar Item is using the same slot as another hotbar item!");
                continue;
            }

            player.getInventory().setItem(item.getSlot(), item.getStack());
            slotsInUse.add(item.getSlot());
        }

        // In most cases we need to update the player inventory.
        new BukkitRunnable() {
            @Override
            public void run() {
                player.updateInventory();
                playersHotbarMap.put(player.getUniqueId(), hotbar);
            }
        }.runTaskLater(sLib.INSTANCE.getPlugin(), 2);
    }

    /**
     *
     * @param player - player instance.
     */
    public static void removeHotbar(Player player, boolean clear) {
        if (!doesPlayerHaveHotbar(player.getUniqueId())) {
            System.err.println("Hotbar was gonna remove someones hotbar when they don't have one!");
            return;
        }

        // Clear the players hotbar for other plugins to add things back
        // to the players inventory.
        if(clear)
            clearHotbar(player.getInventory());
        playersHotbarMap.remove(player.getUniqueId());
    }


    public static void clearHotbar(PlayerInventory inventory) {
        // The inventory starts from the hotbar then left to right
        // meaning we start at 0 and go to 8.
        for (int i = 0; i < 8; i++) {
            inventory.setItem(i, null);
        }
    }
}
