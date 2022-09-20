package ch.luca.creative.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

public class PlayerAchievementAwardedListener implements Listener {

    @EventHandler
    public void onReward(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }
}
