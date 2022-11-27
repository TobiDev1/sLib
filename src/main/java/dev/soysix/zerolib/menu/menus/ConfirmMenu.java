package dev.soysix.zerolib.menu.menus;

import dev.soysix.zerolib.menu.Button;
import dev.soysix.zerolib.menu.Menu;
import dev.soysix.zerolib.menu.buttons.ConfirmationButton;
import dev.soysix.zerolib.menu.callback.TypeCallback;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class ConfirmMenu extends Menu {
   private final String title;
   private final TypeCallback<Boolean> response;
   private final boolean closeAfterResponse;
   private final Button[] centerButtons;

   public ConfirmMenu(String title, TypeCallback<Boolean> response, boolean closeAfter, Button... centerButtons) {
      this.title = title;
      this.response = response;
      this.closeAfterResponse = closeAfter;
      this.centerButtons = centerButtons;
   }

   public Map<Integer, Button> getButtons(Player player) {
      HashMap<Integer, Button> buttons = new HashMap();

      int i;
      for(i = 0; i < 3; ++i) {
         for(int y = 0; y < 3; ++y) {
            buttons.put(this.getSlot(i, y), new ConfirmationButton(true, this.response, this.closeAfterResponse));
            buttons.put(this.getSlot(8 - i, y), new ConfirmationButton(false, this.response, this.closeAfterResponse));
         }
      }

      if (this.centerButtons != null) {
         for(i = 0; i < this.centerButtons.length; ++i) {
            if (this.centerButtons[i] != null) {
               buttons.put(this.getSlot(4, i), this.centerButtons[i]);
            }
         }
      }

      return buttons;
   }

   public String getTitle(Player player) {
      return this.title;
   }
}
