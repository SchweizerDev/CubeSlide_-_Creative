package ch.luca.creative.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    @EventHandler
    public void onSignColor(SignChangeEvent e) {
        Player p = e.getPlayer();
        if(p.hasPermission("creative.colorsign")) {
            String msg1 = ChatColor.translateAlternateColorCodes('&', e.getLine(0));
            String msg2 = ChatColor.translateAlternateColorCodes('&', e.getLine(1));
            String msg3 = ChatColor.translateAlternateColorCodes('&', e.getLine(2));
            String msg4 = ChatColor.translateAlternateColorCodes('&', e.getLine(3));
            e.setLine(0, msg1);
            e.setLine(1, msg2);
            e.setLine(2, msg3);
            e.setLine(3, msg4);
        }
    }
}
