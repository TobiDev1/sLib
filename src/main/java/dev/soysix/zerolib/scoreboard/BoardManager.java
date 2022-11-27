package dev.soysix.zerolib.scoreboard;

import dev.soysix.zerolib.ZeroLib;
import dev.soysix.zerolib.chat.CC;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class BoardManager {
   private BoardAdapter adapter;
   private Map<UUID, Board> boardMap;

   public BoardManager(BoardAdapter adapter, int updateTick) {
      this.adapter = adapter;
      this.boardMap = new HashMap();
      Bukkit.getPluginManager().registerEvents(new BoardListener(this), ZeroLib.INSTANCE.getPlugin());
      Bukkit.getScheduler().runTaskTimer(ZeroLib.INSTANCE.getPlugin(), this::sendScoreboard, 0L, (long)updateTick);
   }

   public void sendScoreboard() {
      Iterator var1 = Bukkit.getServer().getOnlinePlayers().iterator();

      while(true) {
         Player player;
         Board board;
         do {
            if (!var1.hasNext()) {
               return;
            }

            player = (Player)var1.next();
            board = (Board)this.boardMap.get(player.getUniqueId());
         } while(board == null);

         Scoreboard scoreboard = board.getScoreboard();
         Objective objective = board.getObjective();
         String title = CC.translate(this.adapter.getTitle(player));
         if (!objective.getDisplayName().equals(title)) {
            objective.setDisplayName(title);
         }

         List<String> lines = this.adapter.getLines(player);
         if (lines != null && !lines.isEmpty()) {
            if (!this.adapter.getBoardStyle(player).isDescending()) {
               Collections.reverse(lines);
            }

            int j;
            if (board.getEntries().size() > lines.size()) {
               for(j = lines.size(); j < board.getEntries().size(); ++j) {
                  BoardEntry entry = board.getEntryAtPosition(j);
                  if (entry != null) {
                     entry.quit();
                  }
               }
            }

            j = this.adapter.getBoardStyle(player).getStart();

            for(int i = 0; i < lines.size(); ++i) {
               BoardEntry entry = board.getEntryAtPosition(i);
               String line = CC.translate((String)lines.get(i));
               if (entry == null) {
                  entry = new BoardEntry(board, line);
               }

               entry.setText(line);
               entry.setUp();
               entry.send(this.adapter.getBoardStyle(player).isDescending() ? j-- : j++);
            }
         } else {
            board.getEntries().forEach(BoardEntry::quit);
            board.getEntries().clear();
         }

         if (player.getScoreboard() != scoreboard) {
            player.setScoreboard(scoreboard);
         }
      }
   }

   public BoardAdapter getAdapter() {
      return this.adapter;
   }

   public Map<UUID, Board> getBoardMap() {
      return this.boardMap;
   }
}
