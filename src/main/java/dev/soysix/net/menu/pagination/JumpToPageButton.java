package dev.soysix.net.menu.pagination;

import dev.soysix.net.chat.CC;
import dev.soysix.net.menu.Button;
import dev.soysix.net.item.ItemMaker;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class JumpToPageButton extends Button {

    private final int page;
    private final PaginatedMenu menu;
    private final boolean current;

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemMaker itemBuilder = new ItemMaker(this.current ? Material.ENCHANTED_BOOK : Material.BOOK, this.page);
        itemBuilder.setName(CC.translate("&cPage " + this.page));

        if (this.current) {
            itemBuilder.setLore(
                    "",
                    CC.translate("&aCurrent page")
            );
        }

        return itemBuilder.toItemStack();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        this.menu.modPage(player, this.page - this.menu.getPage());
        Button.playNeutral(player);
    }
}
