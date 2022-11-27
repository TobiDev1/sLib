package dev.soysix.zerolib.menu.buttons;

import dev.soysix.zerolib.item.ItemCreator;
import dev.soysix.zerolib.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class AirButton extends Button {
   public ItemStack getButtonItem(Player player) {
      return (new ItemCreator(Material.AIR)).get();
   }

   public boolean shouldCancel(Player player, int slot, ClickType clickType) {
      return true;
   }
}
