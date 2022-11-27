package dev.soysix.zerolib.scoreboard;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BoardListener implements Listener {
   private final BoardManager board;

   @EventHandler
   public void onPlayerJoin(PlayerJoinEvent event) {
      this.board.getBoardMap().put(event.getPlayer().getUniqueId(), new Board(event.getPlayer(), this.board));
   }

   @EventHandler
   public void onPlayerQuit(PlayerQuitEvent event) {
      this.board.getBoardMap().remove(event.getPlayer().getUniqueId());
   }

   public BoardListener(BoardManager board) {
      this.board = board;
   }
}
