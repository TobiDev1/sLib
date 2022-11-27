package dev.soysix.zerolib.menu.buttons;

import dev.soysix.zerolib.item.ItemCreator;
import dev.soysix.zerolib.menu.Button;
import dev.soysix.zerolib.menu.pagination.normal.PaginatedMenu;
import java.util.Collections;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class PageInfoButton extends Button {
   private PaginatedMenu paginatedMenu;

   public ItemStack getButtonItem(Player player) {
      return (new ItemCreator(Material.NETHER_STAR)).setName("&ePage Info").setLore(Collections.singletonList("&e" + this.paginatedMenu.getPage() + "&7/&a" + this.paginatedMenu.getPages(player))).glow().get();
   }

   public boolean shouldCancel(Player player, int slot, ClickType clickType) {
      return true;
   }

   public PageInfoButton(PaginatedMenu paginatedMenu) {
      this.paginatedMenu = paginatedMenu;
   }
}
