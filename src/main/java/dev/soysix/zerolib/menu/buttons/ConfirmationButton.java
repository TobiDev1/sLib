package dev.soysix.zerolib.menu.buttons;

import dev.soysix.zerolib.chat.CC;
import dev.soysix.zerolib.menu.Button;
import dev.soysix.zerolib.menu.callback.TypeCallback;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfirmationButton extends Button {
   private final boolean confirm;
   private final TypeCallback<Boolean> callback;
   private final boolean closeAfterResponse;

   public ItemStack getButtonItem(Player player) {
      ItemStack itemStack = new ItemStack(Material.WOOD, 1, (short)(this.confirm ? 5 : 14));
      ItemMeta itemMeta = itemStack.getItemMeta();
      itemMeta.setDisplayName(this.confirm ? CC.translate("&aConfirm") : CC.translate("&cCancel"));
      itemStack.setItemMeta(itemMeta);
      return itemStack;
   }

   public void clicked(Player player, int i, ClickType clickType, int hb) {
      if (this.confirm) {
         player.playSound(player.getLocation(), Sound.NOTE_PIANO, 20.0F, 0.1F);
      } else {
         player.playSound(player.getLocation(), Sound.DIG_GRAVEL, 20.0F, 0.1F);
      }

      if (this.closeAfterResponse) {
         player.closeInventory();
      }

      this.callback.callback(this.confirm);
   }

   public ConfirmationButton(boolean confirm, TypeCallback<Boolean> callback, boolean closeAfterResponse) {
      this.confirm = confirm;
      this.callback = callback;
      this.closeAfterResponse = closeAfterResponse;
   }
}
