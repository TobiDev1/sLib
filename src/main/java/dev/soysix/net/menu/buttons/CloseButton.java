package dev.soysix.net.menu.buttons;

import dev.soysix.net.menu.Button;
import dev.soysix.net.utils.ItemMaker;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CloseButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemMaker(Material.INK_SACK)
                .setName("&cClose")
                .setDurability((short) 1).toItemStack();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        playNeutral(player);
        player.closeInventory();
    }
}
