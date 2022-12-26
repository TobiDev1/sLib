package dev.soysix.net.hotbar.type;

import dev.soysix.net.hotbar.construct.IHotbar;
import dev.soysix.net.hotbar.construct.IHotbarItem;
import dev.soysix.net.hotbar.settings.HotbarSettings;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class DefaultHotbar implements IHotbar {
    private List<IHotbarItem> hotbarItems;
    private HotbarSettings settings;

    public DefaultHotbar(HotbarSettings settings) {
        this.hotbarItems = new ArrayList<>();
        this.settings = settings;
    }

    public void addHandlerItem(IHotbarItem hotbarItem) {
        this.hotbarItems.add(hotbarItem);
    }

    public void clicked(PlayerInteractEvent event) {

    }

    @Override
    public List<IHotbarItem> getHotbarItems() {
        return this.hotbarItems;
    }

    @Override
    public HotbarSettings getSettings() {
        return this.settings;
    }
}
