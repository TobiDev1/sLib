package dev.soysix.zerolib.menu.pagination.twentyone;

import dev.soysix.zerolib.menu.Button;
import dev.soysix.zerolib.menu.Menu;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.entity.Player;

public abstract class TwentyOneSlotsPaginatedMenu extends Menu {
   private int page = 1;

   public TwentyOneSlotsPaginatedMenu() {
      this.setUpdateAfterClick(false);
   }

   public String getTitle(Player player) {
      return this.getPrePaginatedTitle(player) + " - " + this.page + "/" + this.getPages(player);
   }

   public final void modPage(Player player, int mod) {
      this.page += mod;
      this.getButtons().clear();
      this.openMenu(player);
   }

   public final int getPages(Player player) {
      int buttonAmount = this.getAllPagesButtons(player).size();
      return buttonAmount == 0 ? 1 : (int)Math.ceil((double)buttonAmount / (double)this.getMaxItemsPerPage(player));
   }

   public final Map<Integer, Button> getButtons(Player player) {
      int minIndex = (int)((double)(this.page - 1) * (double)this.getMaxItemsPerPage(player));
      int maxIndex = (int)((double)this.page * (double)this.getMaxItemsPerPage(player));
      maxIndex += 4;
      HashMap<Integer, Button> buttons = new HashMap();
      AtomicInteger index = new AtomicInteger(0);
      int numberToAdd = this.page * 10 + 1;
      Iterator var7 = this.getAllPagesButtons(player).entrySet().iterator();

      while(var7.hasNext()) {
         Entry<Integer, Button> entry = (Entry)var7.next();
         int ind = index.getAndIncrement();
         if (ind >= minIndex && ind < maxIndex) {
            ind -= (int)((double)this.getMaxItemsPerPage(player) * (double)(this.page - 1)) - 10;
            if (ind == 16) {
               index.set(this.page == 1 ? 9 : 9 + numberToAdd);
            }

            if (ind == 25) {
               index.set(this.page == 1 ? 18 : 18 + numberToAdd);
            }

            buttons.put(ind, entry.getValue());
         }
      }

      buttons.put(0, new PageButton(-1, this));
      buttons.put(8, new PageButton(1, this));

      for(int i = 1; i < 8; ++i) {
         buttons.put(i, this.getPlaceholderButton());
      }

      Map<Integer, Button> global = this.getGlobalButtons(player);
      if (global != null) {
         Iterator var12 = global.entrySet().iterator();

         while(var12.hasNext()) {
            Entry<Integer, Button> gent = (Entry)var12.next();
            buttons.put(gent.getKey(), gent.getValue());
         }
      }

      return buttons;
   }

   public int getMaxItemsPerPage(Player player) {
      return 18;
   }

   public Map<Integer, Button> getGlobalButtons(Player player) {
      return null;
   }

   public abstract String getPrePaginatedTitle(Player var1);

   public abstract Map<Integer, Button> getAllPagesButtons(Player var1);

   public int getPage() {
      return this.page;
   }
}
