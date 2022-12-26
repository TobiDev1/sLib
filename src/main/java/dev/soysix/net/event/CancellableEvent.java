package dev.soysix.net.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;

public class CancellableEvent extends BaseEvent implements Cancellable {

    @Getter
    @Setter private boolean cancelled;

}
