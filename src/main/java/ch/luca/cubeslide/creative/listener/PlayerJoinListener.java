package ch.luca.cubeslide.creative.listener;

import ch.luca.cubeslide.creative.Creative;
import ch.luca.cubeslide.creative.api.LocationManager;
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
