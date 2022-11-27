package dev.soysix.zerolib.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class BoardEntry {
   private Board board;
   private String text;
   private String string;
   private Team team;

   public BoardEntry(Board board, String text) {
      this.board = board;
      this.text = text;
      this.string = board.getUniqueString();
      this.setUp();
   }

   public void setUp() {
      Scoreboard scoreboard = this.board.getScoreboard();
      if (scoreboard != null) {
         String name = this.string;
         if (name.length() > 16) {
            name = name.substring(0, 16);
         }

         Team team;
         if (scoreboard.getTeam(name) != null) {
            team = scoreboard.getTeam(name);
         } else {
            team = scoreboard.registerNewTeam(name);
         }

         if (!team.getEntries().contains(this.string)) {
            team.addEntry(this.string);
         }

         if (!this.board.getEntries().contains(this)) {
            this.board.getEntries().add(this);
         }

         this.team = team;
      }

   }

   public void send(int position) {
      if (this.text.length() > 16) {
         String prefix = this.text.substring(0, 16);
         String suffix;
         if (prefix.charAt(15) == 167) {
            prefix = prefix.substring(0, 15);
            suffix = this.text.substring(15);
         } else if (prefix.charAt(14) == 167) {
            prefix = prefix.substring(0, 14);
            suffix = this.text.substring(14);
         } else if (ChatColor.getLastColors(prefix).equalsIgnoreCase(ChatColor.getLastColors(this.string))) {
            suffix = this.text.substring(16);
         } else {
            suffix = ChatColor.getLastColors(prefix) + this.text.substring(16);
         }

         if (suffix.length() > 16) {
            suffix = suffix.substring(0, 16);
         }

         this.team.setPrefix(prefix);
         this.team.setSuffix(suffix);
      } else {
         this.team.setPrefix(this.text);
         this.team.setSuffix("");
      }

      Score score = this.board.getObjective().getScore(this.string);
      score.setScore(position);
   }

   public void quit() {
      this.board.getStrings().remove(this.string);
      this.board.getScoreboard().resetScores(this.string);
   }

   public Board getBoard() {
      return this.board;
   }

   public String getText() {
      return this.text;
   }

   public String getString() {
      return this.string;
   }

   public Team getTeam() {
      return this.team;
   }

   public void setBoard(Board board) {
      this.board = board;
   }

   public void setText(String text) {
      this.text = text;
   }

   public void setString(String string) {
      this.string = string;
   }

   public void setTeam(Team team) {
      this.team = team;
   }
}
