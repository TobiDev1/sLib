package dev.soysix.zerolib.config;

import dev.soysix.zerolib.chat.CC;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class ConfigCursor {
   private final FileConfig fileConfig;
   private String path;

   public boolean exists() {
      return this.exists((String)null);
   }

   public boolean exists(String path) {
      return this.fileConfig.getConfiguration().contains(this.path + (path == null ? "" : "." + path));
   }

   public Set<String> getKeys() {
      return this.getKeys((String)null);
   }

   public Set<String> getKeys(String path) {
      return this.fileConfig.getConfiguration().getConfigurationSection(this.path + (path == null ? "" : "." + path)).getKeys(false);
   }

   public String getString(String path) {
      return CC.translate(this.fileConfig.getConfiguration().getString((this.path == null ? "" : this.path + ".") + path));
   }

   public boolean getBoolean(String path) {
      return this.fileConfig.getConfiguration().getBoolean((this.path == null ? "" : this.path + ".") + "." + path);
   }

   public int getInt(String path) {
      return this.fileConfig.getConfiguration().getInt((this.path == null ? "" : this.path + ".") + "." + path);
   }

   public long getLong(String path) {
      return this.fileConfig.getConfiguration().getLong((this.path == null ? "" : this.path + ".") + "." + path);
   }

   public List<String> getStringList(String path) {
      return this.fileConfig.getConfiguration().getStringList((this.path == null ? "" : this.path + ".") + "." + path);
   }

   public UUID getUuid(String path) {
      return UUID.fromString(this.fileConfig.getConfiguration().getString(this.path + "." + path));
   }

   public World getWorld(String path) {
      return Bukkit.getWorld(this.fileConfig.getConfiguration().getString(this.path + "." + path));
   }

   public void set(Object value) {
      this.set((String)null, value);
   }

   public void set(String path, Object value) {
      this.fileConfig.getConfiguration().set(this.path + (path == null ? "" : "." + path), value);
   }

   public void save() {
      this.fileConfig.save();
   }

   public ConfigCursor(FileConfig fileConfig, String path) {
      this.fileConfig = fileConfig;
      this.path = path;
   }

   public FileConfig getFileConfig() {
      return this.fileConfig;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String path) {
      this.path = path;
   }
}
