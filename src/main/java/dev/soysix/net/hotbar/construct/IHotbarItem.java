package dev.soysix.net.hotbar.construct;

import org.bukkit.inventory.ItemStack;

public interface IHotbarItem {
    int getSlot();
    ItemStack getStack();
    String getName();
}
