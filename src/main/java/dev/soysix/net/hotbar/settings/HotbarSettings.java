package dev.soysix.net.hotbar.settings;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HotbarSettings {
    private boolean movableItems;
    private boolean ableToPickupItems;
    private boolean ableToDropItems;
    private boolean clearOnRemove;

    public HotbarSettings(boolean movableItems, boolean ableToPickupItems, boolean ableToDropItems, boolean clearOnRemove) {
        this.movableItems = movableItems;
        this.ableToPickupItems = ableToPickupItems;
        this.ableToDropItems = ableToDropItems;
        this.clearOnRemove = clearOnRemove;
    }
}
