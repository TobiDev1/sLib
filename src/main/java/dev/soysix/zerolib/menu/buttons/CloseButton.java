package dev.soysix.zerolib.menu.buttons;

import dev.soysix.zerolib.item.ItemCreator;
import dev.soysix.zerolib.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CloseButton extends Button {
   public ItemStack getButtonItem(Player player) {
      return (new ItemCreator(Material.INK_SACK)).setDurability((int)1).setName("&cClose").get();
   }

   public void clicked(Player player, int i, ClickType clickType, int hb) {
      playNeutral(player);
      player.closeInventory();
   }
}
