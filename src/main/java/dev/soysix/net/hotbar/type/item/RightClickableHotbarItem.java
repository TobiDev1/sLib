package dev.soysix.net.hotbar.type.item;

import dev.soysix.net.hotbar.construct.IHotbarItem;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@AllArgsConstructor
public class RightClickableHotbarItem implements IHotbarItem {
    private final String name;
    private final Consumer<Player> playerConsumer;
    private final ItemStack stack;
    private final int slot;

    public Consumer<Player> getPlayerEvent() {
        return playerConsumer;
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public ItemStack getStack() {
        return stack;
    }

    @Override
    public String getName() {
        return name;
    }
}
