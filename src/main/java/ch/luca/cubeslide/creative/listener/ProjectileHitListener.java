package ch.luca.creative.listener;

import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void expBottle(ProjectileHitEvent e) {
        if(e.getEntity() instanceof ThrownExpBottle) {
            ThrownExpBottle thrownExpBottle = (ThrownExpBottle)e.getEntity();
            Player p = (Player)thrownExpBottle.getShooter();
            p.giveExp(7);
        }
    }

    @EventHandler
    public void expBottleLis(ExpBottleEvent e) {
        e.setExperience(0);
    }
}
