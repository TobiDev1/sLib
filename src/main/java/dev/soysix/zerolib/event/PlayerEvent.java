package dev.soysix.zerolib.event;

import java.util.UUID;
import org.bukkit.entity.Player;

public class PlayerEvent extends BaseEvent {
   private Player player;

   public PlayerEvent(Player player) {
      this.player = player;
   }

   public UUID getUniqueId() {
      return this.player.getUniqueId();
   }

   public Player getPlayer() {
      return this.player;
   }
}
