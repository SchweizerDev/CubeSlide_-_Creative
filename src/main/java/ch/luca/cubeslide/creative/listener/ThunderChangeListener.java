package ch.luca.cubeslide.creative.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;

public class ThunderChangeListener implements Listener {

    @EventHandler
    public void onThunder(ThunderChangeEvent e) {
        e.setCancelled(true);
    }
}
