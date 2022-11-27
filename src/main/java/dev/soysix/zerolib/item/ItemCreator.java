package dev.soysix.zerolib.item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemCreator {
   private ItemStack itemStack;

   public ItemCreator(Material material) {
      this.itemStack = new ItemStack(material, 1);
   }

   public ItemCreator(ItemStack itemStack) {
      this.itemStack = itemStack.clone();
   }

   public ItemCreator(Material material, int damage) {
      this.itemStack = new ItemStack(material, 1, (short)damage);
   }

   public ItemCreator(Material material, int amount, int damage) {
      this.itemStack = new ItemStack(material, amount, (short)damage);
   }

   public ItemCreator setName(String name) {
      if (name != null) {
         name = ChatColor.translateAlternateColorCodes('&', name);
         ItemMeta meta = this.itemStack.getItemMeta();
         meta.setDisplayName(name);
         this.itemStack.setItemMeta(meta);
      }

      return this;
   }

   public ItemCreator setLore(List<String> lore) {
      if (lore != null) {
         List<String> list = new ArrayList();
         lore.forEach((line) -> {
            list.add(ChatColor.translateAlternateColorCodes('&', line));
         });
         ItemMeta meta = this.itemStack.getItemMeta();
         meta.setLore(list);
         this.itemStack.setItemMeta(meta);
      }

      return this;
   }

   public ItemCreator setAmount(int amount) {
      this.itemStack.setAmount(amount);
      return this;
   }

   public ItemCreator addEnchant(Enchantment enchantment, int level) {
      this.itemStack.addUnsafeEnchantment(enchantment, level);
      return this;
   }

   public ItemCreator addEnchants(ItemCreator.ItemCreatorEnchantment... itemCreatorEnchantments) {
      ItemCreator.ItemCreatorEnchantment[] var2 = itemCreatorEnchantments;
      int var3 = itemCreatorEnchantments.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ItemCreator.ItemCreatorEnchantment itemCreatorEnchantment = var2[var4];
         this.itemStack.addUnsafeEnchantment(itemCreatorEnchantment.getEnchantment(), itemCreatorEnchantment.getLevel());
      }

      return this;
   }

   public ItemCreator addEnchants(List<String> enchants) {
      if (enchants != null) {
         enchants.forEach((enchant) -> {
            String[] arr = enchant.replace(" ", "").split(",");
            Enchantment enchantment = Enchantment.getByName(arr[0]);
            int level = Integer.parseInt(arr[1]);
            this.itemStack.addUnsafeEnchantment(enchantment, level);
         });
      }

      return this;
   }

   public ItemCreator setDurability(short dur) {
      this.itemStack.setDurability(dur);
      return this;
   }

   public ItemCreator setDurability(int dur) {
      this.itemStack.setDurability((short)dur);
      return this;
   }

   public ItemCreator setOwner(String owner) {
      if (this.itemStack.getType() == Material.SKULL_ITEM) {
         SkullMeta meta = (SkullMeta)this.itemStack.getItemMeta();
         meta.setOwner(owner);
         this.itemStack.setItemMeta(meta);
         return this;
      } else {
         throw new IllegalArgumentException("setOwner() only applicable for Skull Item");
      }
   }

   public ItemCreator setArmorColor(Color color) {
      try {
         LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta)this.itemStack.getItemMeta();
         leatherArmorMeta.setColor(color);
         this.itemStack.setItemMeta(leatherArmorMeta);
      } catch (Exception var3) {
         Bukkit.getConsoleSender().sendMessage("Error set armor color.");
      }

      return this;
   }

   public ItemCreator glow() {
      ItemMeta meta = this.itemStack.getItemMeta();
      meta.addEnchant(new ItemCreator.Glow(), 1, true);
      this.itemStack.setItemMeta(meta);
      return this;
   }

   public static void registerGlow() {
      try {
         Field f = Enchantment.class.getDeclaredField("acceptingNew");
         f.setAccessible(true);
         f.set((Object)null, true);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      try {
         ItemCreator.Glow glow = new ItemCreator.Glow();
         Enchantment.registerEnchantment(glow);
      } catch (IllegalArgumentException var1) {
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public ItemStack get() {
      return this.itemStack;
   }

   private static class Glow extends Enchantment {
      public Glow() {
         super(25);
      }

      public boolean canEnchantItem(ItemStack arg0) {
         return false;
      }

      public boolean conflictsWith(Enchantment arg0) {
         return false;
      }

      public EnchantmentTarget getItemTarget() {
         return null;
      }

      public int getMaxLevel() {
         return 2;
      }

      public String getName() {
         return null;
      }

      public int getStartLevel() {
         return 1;
      }
   }

   public static class ItemCreatorEnchantment {
      private final Enchantment enchantment;
      private final int level;

      public ItemCreatorEnchantment(Enchantment enchantment, int level) {
         this.enchantment = enchantment;
         this.level = level;
      }

      public Enchantment getEnchantment() {
         return this.enchantment;
      }

      public int getLevel() {
         return this.level;
      }
   }
}
