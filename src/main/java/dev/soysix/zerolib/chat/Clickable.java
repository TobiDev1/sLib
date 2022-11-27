package dev.soysix.zerolib.chat;

import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class Clickable {
   private final List<TextComponent> components = new ArrayList();
   private String hoverText;
   private String text;
   private Clickable.Action action;

   public Clickable(String msg) {
      TextComponent message = new TextComponent(CC.translate(msg));
      this.components.add(message);
      this.text = msg;
   }

   public Clickable(String msg, String hoverMsg, String clickString, Clickable.Action action) {
      this.add(msg, hoverMsg, clickString);
      this.text = msg;
      this.hoverText = hoverMsg;
      this.action = action;
   }

   public TextComponent add(String msg, String hoverMsg, String clickString) {
      TextComponent message = new TextComponent(CC.translate(msg));
      if (hoverMsg != null) {
         message.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(CC.translate(hoverMsg))).create()));
      }

      if (clickString != null) {
         if (this.action != null) {
            switch(this.action) {
            case URL:
               message.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.OPEN_URL, clickString));
               break;
            case COMMAND:
               message.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, clickString));
            }
         } else {
            message.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, clickString));
         }
      }

      this.components.add(message);
      this.text = msg;
      this.hoverText = hoverMsg;
      return message;
   }

   public void add(String message) {
      this.components.add(new TextComponent(message));
   }

   public void sendToPlayer(Player player) {
      player.spigot().sendMessage(this.asComponents());
   }

   public TextComponent[] asComponents() {
      return (TextComponent[])this.components.toArray(new TextComponent[0]);
   }

   public Clickable() {
   }

   public List<TextComponent> getComponents() {
      return this.components;
   }

   public String getHoverText() {
      return this.hoverText;
   }

   public String getText() {
      return this.text;
   }

   public Clickable.Action getAction() {
      return this.action;
   }

   public static enum Action {
      URL,
      COMMAND;
   }
}
