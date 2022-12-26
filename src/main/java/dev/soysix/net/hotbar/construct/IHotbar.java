package dev.soysix.net.hotbar.construct;

import dev.soysix.net.hotbar.settings.HotbarSettings;

import java.util.List;

public interface IHotbar {
    List<IHotbarItem> getHotbarItems();
    HotbarSettings getSettings();
}
