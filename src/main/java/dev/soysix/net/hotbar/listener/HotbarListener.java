package dev.soysix.net.hotbar.listener;

import dev.soysix.net.hotbar.HotbarHandler;
import dev.soysix.net.hotbar.construct.IHotbar;
import dev.soysix.net.hotbar.construct.IHotbarItem;
import dev.soysix.net.hotbar.type.item.EntityInteractionHotbarItem;
import dev.soysix.net.hotbar.type.item.RightClickableHotbarItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HotbarListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();

        if (HotbarHandler.doesPlayerHaveHotbar(playerId)) {
            if (!event.hasItem() || !event.getAction().name().contains("RIGHT_"))
                return;

            IHotbar hotbar = HotbarHandler.getPlayerHotbar(playerId);
            Player player = event.getPlayer();
            ItemStack item = event.getItem();

            List<IHotbarItem> items = hotbar.getHotbarItems()
                    .stream().filter(hotbarItem -> hotbarItem instanceof RightClickableHotbarItem).collect(Collectors.toList());

            for (IHotbarItem hotbarItem : items) {
                if (hotbarItem.getStack().isSimilar(item)) {
                    RightClickableHotbarItem correctedItem = (RightClickableHotbarItem) hotbarItem;
                    correctedItem.getPlayerEvent().accept(player);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteractWithEntityEvent(PlayerInteractEntityEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();

        if (HotbarHandler.doesPlayerHaveHotbar(playerId)) {
            if (event.getPlayer().getItemInHand() == null)
                return;

            IHotbar hotbar = HotbarHandler.getPlayerHotbar(playerId);
            Player player = event.getPlayer();
            ItemStack item = event.getPlayer().getItemInHand();

            List<IHotbarItem> items = hotbar.getHotbarItems()
                    .stream().filter(hotbarItem -> hotbarItem instanceof EntityInteractionHotbarItem).collect(Collectors.toList());

            for (IHotbarItem hotbarItem : items) {
                if (hotbarItem.getStack().isSimilar(item)) {
                    EntityInteractionHotbarItem correctedItem = (EntityInteractionHotbarItem) hotbarItem;
                    correctedItem.getPlayerEvent().accept(event);
                    break;
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();

        if (HotbarHandler.doesPlayerHaveHotbar(playerId)
                && !HotbarHandler.getPlayerHotbar(playerId).getSettings().isAbleToDropItems()) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerPickup(PlayerPickupItemEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();

        if (HotbarHandler.doesPlayerHaveHotbar(playerId)
                && !HotbarHandler.getPlayerHotbar(playerId).getSettings().isAbleToPickupItems()) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        UUID playerId = event.getWhoClicked().getUniqueId();

        if (event.getCurrentItem() != null && HotbarHandler.doesPlayerHaveHotbar(playerId)
                && !HotbarHandler.getPlayerHotbar(playerId).getSettings().isMovableItems()) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInventoryClick(InventoryDragEvent event) {
        UUID playerId = event.getWhoClicked().getUniqueId();

        if (event.getCursor() != null && HotbarHandler.doesPlayerHaveHotbar(playerId)
                && !HotbarHandler.getPlayerHotbar(playerId).getSettings().isMovableItems()) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLeave(PlayerQuitEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();

        if (HotbarHandler.doesPlayerHaveHotbar(playerId)) {
            HotbarHandler.removeHotbar(event.getPlayer(), HotbarHandler.getPlayerHotbar(playerId).getSettings().isClearOnRemove());
        }
    }
}
