package dev.soysix.net.hotbar.type.item;

import dev.soysix.net.hotbar.construct.IHotbarItem;
import lombok.AllArgsConstructor;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@AllArgsConstructor
public class EntityInteractionHotbarItem implements IHotbarItem {
    private String name;
    private final Consumer<PlayerInteractEntityEvent> playerConsumer;
    private final ItemStack stack;
    private final int slot;

    public Consumer<PlayerInteractEntityEvent> getPlayerEvent() {
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
