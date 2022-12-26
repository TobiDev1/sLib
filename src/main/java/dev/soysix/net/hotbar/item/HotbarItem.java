package dev.soysix.net.hotbar.item;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Getter
public class HotbarItem {
    private final Consumer<Player> playerConsumer;
    private final ItemStack stack;
    private final int slot;

    public HotbarItem(Consumer<Player> playerConsumer, ItemStack stack, int slot) {
        this.playerConsumer = playerConsumer;
        this.stack = stack;
        this.slot = slot;
    }
}
