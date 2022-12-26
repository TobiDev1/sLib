package dev.soysix.net.hotbar.type.item;

import dev.soysix.net.hotbar.construct.IHotbarItem;
import lombok.AllArgsConstructor;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class DefaultHotbarItem implements IHotbarItem {
    private String name;
    private final ItemStack stack;
    private final int slot;

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
