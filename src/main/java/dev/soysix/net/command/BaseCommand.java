package dev.soysix.net.command;

import dev.soysix.net.sLib;

public abstract class BaseCommand {

    public BaseCommand(){
        sLib.INSTANCE.getCommandFramework().registerCommands(this, null);
    }

    public abstract void onCommand(CommandArgs command);
}
