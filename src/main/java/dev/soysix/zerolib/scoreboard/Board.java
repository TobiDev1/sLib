package dev.soysix.zerolib.scoreboard;

import dev.soysix.zerolib.chat.CC;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class Board {
   private List<BoardEntry> entries = new ArrayList();
   private List<String> strings = new ArrayList();
   private Scoreboard scoreboard;
   private Objective objective;
   private UUID id;
   private BoardManager board;

   public Board(Player player, BoardManager board) {
      this.id = player.getUniqueId();
      this.board = board;
      this.setUp(player);
   }

   public void setUp(Player player) {
      if (player.getScoreboard() != Bukkit.getScoreboardManager().getMainScoreboard()) {
         this.scoreboard = player.getScoreboard();
      } else {
         this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
      }

      this.objective = this.scoreboard.registerNewObjective("Default", "dummy");
      this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
      if (this.board.getAdapter().getTitle(player) == null) {
         this.objective.setDisplayName(CC.translate("&bDefault Tittle"));
      } else {
         this.objective.setDisplayName(this.board.getAdapter().getTitle(player));
      }

      player.setScoreboard(this.scoreboard);
   }

   String getUniqueString() {
      String string;
      for(string = getRandomColor(); this.strings.contains(string); string = string + getRandomColor()) {
      }

      if (string.length() > 16) {
         return this.getUniqueString();
      } else {
         this.strings.add(string);
         return string;
      }
   }

   BoardEntry getEntryAtPosition(int position) {
      return position >= this.entries.size() ? null : (BoardEntry)this.entries.get(position);
   }

   private static String getRandomColor() {
      Random random = new Random();
      return ((ChatColor)colors().get(random.nextInt(colors().size() - 1))).toString();
   }

   private static List<ChatColor> colors() {
      List<ChatColor> chatColors = new ArrayList();
      Arrays.stream(ChatColor.values()).filter(ChatColor::isColor).forEach(chatColors::add);
      return chatColors;
   }

   public List<BoardEntry> getEntries() {
      return this.entries;
   }

   public List<String> getStrings() {
      return this.strings;
   }

   public Scoreboard getScoreboard() {
      return this.scoreboard;
   }

   public Objective getObjective() {
      return this.objective;
   }

   public UUID getId() {
      return this.id;
   }

   public BoardManager getBoard() {
      return this.board;
   }
}
