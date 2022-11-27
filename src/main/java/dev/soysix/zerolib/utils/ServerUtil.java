package dev.soysix.zerolib.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

@UtilityClass
public class ServerUtil {

    public final String SERVER_VERSION =
            Bukkit.getServer()
                    .getClass().getPackage()
                    .getName().split("\\.")[3]
                    .substring(1);

    public final int SERVER_VERSION_INT = Integer.parseInt(
            SERVER_VERSION
                    .replace("1_", "")
                    .replaceAll("_R\\d", ""));

}
