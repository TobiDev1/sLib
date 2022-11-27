package dev.soysix.zerolib.menu.pagination.twentyone;

import dev.soysix.zerolib.chat.CC;
import dev.soysix.zerolib.menu.Button;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PageButton extends Button {
   private final int mod;
   private final TwentyOneSlotsPaginatedMenu menu;

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

      itemStack.setItemMeta(itemMeta);
      return itemStack;
   }

   public void clicked(Player player, int i, ClickType clickType, int hb) {
      if (this.hasNext(player)) {
         this.menu.modPage(player, this.mod);
         player.playSound(player.getLocation(), Sound.CLICK, 20.0F, 15.0F);
      } else {
         player.playSound(player.getLocation(), Sound.BLAZE_HIT, 1.0F, 0.5F);
      }

   }

   private boolean hasNext(Player player) {
      int pg = this.menu.getPage() + this.mod;
      return pg > 0 && this.menu.getPages(player) >= pg;
   }

   public PageButton(int mod, TwentyOneSlotsPaginatedMenu menu) {
      this.mod = mod;
      this.menu = menu;
   }
}
