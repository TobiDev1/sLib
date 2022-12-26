package dev.soysix.net;

import dev.soysix.net.chat.CC;
import dev.soysix.net.command.BaseCommand;
import dev.soysix.net.command.CommandFramework;
import dev.soysix.net.config.FileConfig;
import dev.soysix.net.handler.RegisterHandler;
import dev.soysix.net.utils.TPSUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public final class sLib {

    public static sLib INSTANCE;

    private Plugin plugin;

    private RegisterHandler registerHandler;

    private CommandFramework commandFramework;

    private String noPermission = CC.translate("&cYou don't have permission.");
    private String noConsole = CC.translate("&cNo Console");
    private String disableCommand = CC.translate("&4<command> &ccommand was not registered because it was disabled in the configuration");

    private boolean excludeCommands = false;
    private FileConfig excludeCommandFile;
    private String excludeCommandsPath;

    public sLib(JavaPlugin plugin) {
        INSTANCE = this;
        this.plugin = plugin;
        this.registerHandler = new RegisterHandler(plugin);
        this.commandFramework = new CommandFramework(plugin);
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new TPSUtils(), 1, 1);
    }

    public void loadCommandsInFile() {
        commandFramework.loadCommandsInFile();
    }

    public boolean checkAuthors(String... strings) {
        boolean passed = true;

        for (String string : strings) {
            if (!this.plugin.getDescription().getAuthors().contains(string)) {
                passed = false;
            }
        }

        if (!passed) {
            Bukkit.getPluginManager().disablePlugin(this.plugin);
            for (int i = 0; i < 25; i++) {
                Bukkit.getConsoleSender().sendMessage(CC.translate("&cWhy are you changing the plugin.yml"));
            }
        }
        return passed;
    }

    public void setExcludeCommandConfig(FileConfig fileConfig, String path) {
        this.excludeCommandFile = fileConfig;
        this.excludeCommandsPath = path;
        excludeCommands = true;
    }

    public void registerCommands(BaseCommand... baseCommands) {
        for (BaseCommand baseCommand : baseCommands) {
            commandFramework.registerCommands(baseCommand, null);
        }
    }

    public List<String> getExcludeCommandsList() {
        return excludeCommandFile.getStringList(excludeCommandsPath);
    }

    public void loadListenersFromPackage(String packageName) {
        registerHandler.loadListenersFromPackage(packageName);
    }

    public void loadCommandsFromPackage(String packageName) {
        registerHandler.loadCommandsFromPackage(packageName);
    }
}