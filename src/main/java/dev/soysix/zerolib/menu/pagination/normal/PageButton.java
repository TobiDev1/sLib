package dev.soysix.zerolib.menu.pagination.normal;

import dev.soysix.zerolib.chat.CC;
import dev.soysix.zerolib.menu.Button;
import dev.soysix.zerolib.menu.pagination.ViewAllPagesMenu;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PageButton extends Button {
   private final int mod;
   private final PaginatedMenu menu;

   public ItemStack getButtonItem(Player player) {
      ItemStack itemStack = new ItemStack(Material.CARPET);
      if (this.mod > 0) {
         itemStack.setDurability((short)13);
      } else {
         itemStack.setDurability((short)14);
      }

      ItemMeta itemMeta = itemStack.getItemMeta();
      if (this.hasNext(player)) {
         itemMeta.setDisplayName(this.mod > 0 ? CC.translate("&aNext page") : CC.translate("&cPrevious page"));
      } else {
         itemMeta.setDisplayName(CC.translate("&7") + (this.mod > 0 ? "Last page" : "First page"));
      }

      itemMeta.setLore(Arrays.asList("", CC.translate("&eRight click to"), CC.translate("&ejump to a page")));
      itemStack.setItemMeta(itemMeta);
      return itemStack;
   }

   public void clicked(Player player, int i, ClickType clickType, int hb) {
      if (clickType == ClickType.RIGHT) {
         (new ViewAllPagesMenu(this.menu)).openMenu(player);
         playNeutral(player);
      } else if (this.hasNext(player)) {
         this.menu.modPage(player, this.mod);
         Button.playNeutral(player);
      } else {
         Button.playFail(player);
      }

   }

   private boolean hasNext(Player player) {
      int pg = this.menu.getPage() + this.mod;
      return pg > 0 && this.menu.getPages(player) >= pg;
   }

   public PageButton(int mod, PaginatedMenu menu) {
      this.mod = mod;
      this.menu = menu;
   }
}
