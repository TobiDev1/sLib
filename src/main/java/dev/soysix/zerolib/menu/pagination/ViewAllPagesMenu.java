package dev.soysix.zerolib.menu.pagination;

import dev.soysix.zerolib.menu.Button;
import dev.soysix.zerolib.menu.Menu;
import dev.soysix.zerolib.menu.buttons.BackButton;
import dev.soysix.zerolib.menu.pagination.normal.PaginatedMenu;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import org.bukkit.entity.Player;

public class ViewAllPagesMenu extends Menu {
   @NonNull
   PaginatedMenu menu;

   public String getTitle(Player player) {
      return "Jump to page";
   }

   public Map<Integer, Button> getButtons(Player player) {
      HashMap<Integer, Button> buttons = new HashMap();
      buttons.put(0, new BackButton(this.menu));
      int index = 10;

      for(int i = 1; i <= this.menu.getPages(player); ++i) {
         buttons.put(index++, new JumpToPageButton(i, this.menu, this.menu.getPage() == i));
         if ((index - 8) % 9 == 0) {
            index += 2;
         }
      }

      return buttons;
   }

   public boolean isAutoUpdate() {
      return true;
   }

   public ViewAllPagesMenu(@NonNull PaginatedMenu menu) {
      if (menu == null) {
         throw new NullPointerException("menu is marked non-null but is null");
      } else {
         this.menu = menu;
      }
   }

   @NonNull
   public PaginatedMenu getMenu() {
      return this.menu;
   }
}
