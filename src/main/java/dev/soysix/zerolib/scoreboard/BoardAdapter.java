package dev.soysix.zerolib.scoreboard;

import java.util.List;
import org.bukkit.entity.Player;

public interface BoardAdapter {
   String getTitle(Player var1);

   List<String> getLines(Player var1);

   BoardStyle getBoardStyle(Player var1);
}
