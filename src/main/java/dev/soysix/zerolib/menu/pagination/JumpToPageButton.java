package dev.soysix.zerolib.menu.pagination;

import dev.soysix.zerolib.chat.CC;
import dev.soysix.zerolib.menu.Button;
import dev.soysix.zerolib.menu.pagination.normal.PaginatedMenu;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JumpToPageButton extends Button {
   private int page;
   private PaginatedMenu menu;
   private boolean current;

   public ItemStack getButtonItem(Player player) {
      ItemStack itemStack = new ItemStack(this.current ? Material.ENCHANTED_BOOK : Material.BOOK, this.page);
      ItemMeta itemMeta = itemStack.getItemMeta();
      itemMeta.setDisplayName(CC.translate("&cPage " + this.page));
      if (this.current) {
         itemMeta.setLore(Arrays.asList("", CC.translate("&aCurrent page")));
      }

      itemStack.setItemMeta(itemMeta);
      return itemStack;
   }

   public void clicked(Player player, int i, ClickType clickType, int hb) {
      this.menu.modPage(player, this.page - this.menu.getPage());
      Button.playNeutral(player);
   }

   public JumpToPageButton(int page, PaginatedMenu menu, boolean current) {
      this.page = page;
      this.menu = menu;
      this.current = current;
   }
}
