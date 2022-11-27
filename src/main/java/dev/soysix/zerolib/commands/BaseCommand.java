package dev.soysix.zerolib.commands;

import dev.soysix.zerolib.ZeroLib;
import org.bukkit.plugin.Plugin;

public abstract class BaseCommand {

    public BaseCommand(){
            ZeroLib.INSTANCE.getCommandFramework().registerCommands(this, null);
    }

    public abstract void onCommand(CommandArgs command);
}
