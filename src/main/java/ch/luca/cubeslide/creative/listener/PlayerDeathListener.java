package ch.luca.cubeslide.creative.listener;

import ch.luca.cubeslide.creative.Creative;
import ch.luca.cubeslide.creative.api.LocationManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        p.teleport(LocationManager.getSpawnLoc());
        e.setDeathMessage(null);
        p.sendMessage(Creative.getPrefix() + "Du bist gestorben.");
    }
}
