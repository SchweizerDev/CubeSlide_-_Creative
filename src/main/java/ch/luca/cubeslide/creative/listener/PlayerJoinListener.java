package ch.luca.creative.listener;

import ch.luca.creative.api.LocationManager;
import ch.luca.creative.Creative;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        Creative.getInstance().getUserManager().getUser(p);
        p.setGameMode(GameMode.CREATIVE);
        if(p.hasPlayedBefore()) {
            p.sendMessage(Creative.getPrefix() + "Willkommen auf §2Creative§7.");
            p.sendMessage(Creative.getPrefix() + "Informationen erhälst du mit §e/creative.");
            p.setGameMode(GameMode.CREATIVE);
        } else {
            p.teleport(LocationManager.getSpawnLoc());
            p.sendMessage(Creative.getPrefix() + "Willkommen auf §2Creative§7.");
            p.sendMessage(Creative.getPrefix() + "Informationen erhälst du mit §e/creative.");
            p.setGameMode(GameMode.CREATIVE);
        }
    }
}
