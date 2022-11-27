package dev.soysix.zerolib;

import dev.soysix.zerolib.chat.CC;
import dev.soysix.zerolib.commands.BaseCommand;
import dev.soysix.zerolib.commands.CommandFramework;
import dev.soysix.zerolib.config.FileConfig;
import dev.soysix.zerolib.handler.RegisterHandler;
import dev.soysix.zerolib.item.ItemCreator;
import dev.soysix.zerolib.utils.TPSUtils;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ZeroLib {
   public static ZeroLib INSTANCE;
   private Plugin plugin;
   private RegisterHandler registerHandler;
   private CommandFramework commandFramework;
   private String noPermissionMessage = CC.translate("&cError! &7You don't have permissions");
   private String onlyGameCommandMessage = CC.translate("&cError! &7This command is only performable in game.");
   private String disableCommandMessage = CC.translate("&b[Command-Register] &7<command> command was not registered because it was disabled in the configuration");
   private boolean excludeCommands = false;
   private FileConfig excludeCommandFile;
   private String excludeCommandsPath;

   public ZeroLib(JavaPlugin plugin) {
      INSTANCE = this;
      this.plugin = plugin;
      this.registerHandler = new RegisterHandler(plugin);
      ItemCreator.registerGlow();
      this.commandFramework = new CommandFramework(plugin);
   }

   public void initTPS() {
      this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new TPSUtils(), 1L, 1L);
   }

   public void loadCommandsInFile() {
      this.commandFramework.loadCommandsInFile();
   }

   public boolean checkAuthors(String... strings) {
      boolean passed = true;
      String[] var3 = strings;
      int var4 = strings.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         String string = var3[var5];
         if (!this.plugin.getDescription().getAuthors().contains(string)) {
            passed = false;
         }
      }

      if (!passed) {
         Bukkit.getPluginManager().disablePlugin(this.plugin);

         for(int i = 0; i < 25; ++i) {
            Bukkit.getConsoleSender().sendMessage(CC.translate("&cWhy are you changing the plugin.yml"));
         }
      }

      return passed;
   }

   public void setExcludeCommandConfig(FileConfig fileConfig, String path) {
      this.excludeCommandFile = fileConfig;
      this.excludeCommandsPath = path;
      this.excludeCommands = true;
   }

   public void registerCommands(BaseCommand... baseCommands) {
      BaseCommand[] var2 = baseCommands;
      int var3 = baseCommands.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         BaseCommand baseCommand = var2[var4];
         this.commandFramework.registerCommands(baseCommand, (List)null);
      }

   }

   public List<String> getExcludeCommandsList() {
      return this.excludeCommandFile.getStringList(this.excludeCommandsPath);
   }

   public void loadListenersFromPackage(String packageName) {
      this.registerHandler.loadListenersFromPackage(packageName);
   }

   public void loadCommandsFromPackage(String packageName) {
      this.registerHandler.loadCommandsFromPackage(packageName);
   }

   public Plugin getPlugin() {
      return this.plugin;
   }

   public RegisterHandler getRegisterHandler() {
      return this.registerHandler;
   }

   public CommandFramework getCommandFramework() {
      return this.commandFramework;
   }

   public String getNoPermissionMessage() {
      return this.noPermissionMessage;
   }

   public String getOnlyGameCommandMessage() {
      return this.onlyGameCommandMessage;
   }

   public String getDisableCommandMessage() {
      return this.disableCommandMessage;
   }

   public boolean isExcludeCommands() {
      return this.excludeCommands;
   }

   public FileConfig getExcludeCommandFile() {
      return this.excludeCommandFile;
   }

   public String getExcludeCommandsPath() {
      return this.excludeCommandsPath;
   }

   public void setPlugin(Plugin plugin) {
      this.plugin = plugin;
   }

   public void setRegisterHandler(RegisterHandler registerHandler) {
      this.registerHandler = registerHandler;
   }

   public void setCommandFramework(CommandFramework commandFramework) {
      this.commandFramework = commandFramework;
   }

   public void setNoPermissionMessage(String noPermissionMessage) {
      this.noPermissionMessage = noPermissionMessage;
   }

   public void setOnlyGameCommandMessage(String onlyGameCommandMessage) {
      this.onlyGameCommandMessage = onlyGameCommandMessage;
   }

   public void setDisableCommandMessage(String disableCommandMessage) {
      this.disableCommandMessage = disableCommandMessage;
   }

   public void setExcludeCommands(boolean excludeCommands) {
      this.excludeCommands = excludeCommands;
   }

   public void setExcludeCommandFile(FileConfig excludeCommandFile) {
      this.excludeCommandFile = excludeCommandFile;
   }

   public void setExcludeCommandsPath(String excludeCommandsPath) {
      this.excludeCommandsPath = excludeCommandsPath;
   }
}
