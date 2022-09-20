package ch.luca.creative.listener;

import ch.luca.creative.commands.Spawn;
import ch.luca.creative.Creative;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class PlayerMoveListener implements Listener {

    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();
        if (((from.getBlockX() != to.getBlockX()) || (from.getBlockZ() != to.getBlockZ()) || (from.getBlockY() != to.getBlockY())) && (Spawn.teleport.contains(p))) {
            Spawn.teleport.remove(p);
            p.sendMessage(Creative.getPrefix() + "Die Teleportation wurde abgebrochen.");
        }
    }
}

