package dev.soysix.net.crate.events;

import dev.soysix.net.crate.Crate;
import dev.soysix.net.event.PlayerEvent;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class ReceiveCrateEvent extends PlayerEvent {

    private Crate crate;

    public ReceiveCrateEvent(Player player, Crate crate) {
        super(player);
        this.crate = crate;
    }
}