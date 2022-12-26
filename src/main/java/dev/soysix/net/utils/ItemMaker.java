package dev.soysix.net.utils;

import dev.soysix.net.chat.CC;
import lombok.Setter;
import org.bukkit.inventory.*;
import org.bukkit.enchantments.*;

import java.util.*;

import org.bukkit.*;
import org.bukkit.inventory.meta.*;

public class ItemMaker {

    private final ItemStack is;
    private ItemStack itemStack;
    @Setter
    private HashMap<Enchantment, Integer> enchantments;

    private boolean unbreakable;

    private ItemMeta itemMeta;

    public ItemMaker(Material m) {
        this(m, 1);
    }

    public ItemMaker(ItemStack is) {
        this.is = is;
    }

    public ItemMaker(Material m, int amount) {
        this.is = new ItemStack(m, amount);
    }

    public ItemMaker(Material m, int amount, byte durability) {
        this.is = new ItemStack(m, amount, durability);
    }

    public ItemMaker(Material material, int amount, int damage) {
        this.is = new ItemStack(material, amount, (short) damage);
    }

    public ItemMaker clone() {
        return new ItemMaker(this.is);
    }

    public ItemMaker setDurability(short dur) {
        this.is.setDurability(dur);
        return this;
    }

    public ItemMaker setName(String name) {
        ItemMeta im = this.is.getItemMeta();
        im.setDisplayName(CC.translate(name));
        this.is.setItemMeta(im);
        return this;
    }

    public ItemMaker addUnsafeEnchantment(Enchantment ench, int level) {
        this.is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemMaker setAmount(int amount) {
        this.is.setAmount(amount);
        return this;
    }

    public ItemMaker removeEnchantment(Enchantment ench) {
        this.is.removeEnchantment(ench);
        return this;
    }

    public ItemMaker setSkullOwner(String owner) {
        try {
            SkullMeta im = (SkullMeta) this.is.getItemMeta();
            im.setOwner(owner);
            this.is.setItemMeta(im);
        } catch (ClassCastException ex) {
        }
        return this;
    }

    public ItemMaker addEnchant(Enchantment ench, int level) {
        ItemMeta im = this.is.getItemMeta();
        im.addEnchant(ench, level, true);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemMaker addEnchantments(Map<Enchantment, Integer> enchantments) {
        this.is.addEnchantments(enchantments);
        return this;
    }

    public ItemMaker setInfinityDurability() {
        this.is.setDurability((short) 32767);
        return this;
    }

    public ItemMaker setLore(String... lore) {
        ItemMeta im = this.is.getItemMeta();
        im.setLore(CC.translateFromArray(Arrays.asList(lore)));
        this.is.setItemMeta(im);
        return this;
    }

    public ItemMaker setLore(List<String> lore) {
        ItemMeta im = this.is.getItemMeta();
        im.setLore(CC.translateFromArray(lore));
        this.is.setItemMeta(im);
        return this;
    }

    public ItemMaker removeLoreLine(String line) {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList<String>(im.getLore());
        if (!lore.contains(line)) {
            return this;
        }
        lore.remove(line);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemMaker removeLoreLine(int index) {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList<String>(im.getLore());
        if (index < 0 || index > lore.size()) {
            return this;
        }
        lore.remove(index);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemMaker addLoreLine(String line) {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList<String>();
        if (im.hasLore()) {
            lore = new ArrayList<String>(im.getLore());
        }
        lore.add(line);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemMaker addLoreLine(String line, int pos) {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList<String>(im.getLore());
        lore.set(pos, line);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemMaker setDyeColor(DyeColor color) {
        this.is.setDurability(color.getData());
        return this;
    }

    @Deprecated
    public ItemMaker setWoolColor(DyeColor color) {
        if (!this.is.getType().equals(Material.WOOL)) {
            return this;
        }
        this.is.setDurability(color.getData());
        return this;
    }

    public ItemMaker setGlow(boolean glow) {
        if (glow) {
            ItemMeta meta = this.is.getItemMeta();

            meta.addEnchant(new ItemMaker.Glow(), 1, true);
            this.is.setItemMeta(meta);
        }
        return this;
    }

    public ItemMaker name(String name) {
        if (name != null) {
            name = CC.translate(name);
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.setDisplayName(name);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemMaker lore(List<String> lore) {
        if (lore != null) {
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.setLore(CC.translate(lore));
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemMaker lore(String... lore) {
        if (lore != null) {
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.setLore(CC.translate(Arrays.asList(lore)));
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemMaker setLeatherArmorColor(org.bukkit.Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) this.is.getItemMeta();
            im.setColor(color);
            this.is.setItemMeta(im);
        } catch (ClassCastException ex) {
        }
        return this;
    }

    public ItemStack toItemStack() {
        return this.is;
    }

    public ItemMaker data(int durability) {
        this.is.setDurability((short)durability);
        return this;
    }

    public void durability(int anInt) {

    }

    public ItemMaker setEnchant(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    public ItemMaker setUnbreakable(boolean unbreakable) {
        itemMeta.spigot().setUnbreakable(unbreakable);
        return this;
    }


    private static class Glow extends Enchantment {

        public Glow() {
            super(25);
        }

        @Override
        public boolean canEnchantItem(ItemStack arg0) {
            return false;
        }

        @Override
        public boolean conflictsWith(Enchantment arg0) {
            return false;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return null;
        }

        @Override
        public int getMaxLevel() {
            return 2;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }
    }

    public ItemStack build() {
        return this.itemStack;
    }

}
