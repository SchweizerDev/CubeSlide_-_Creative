package ch.luca.creative.itemremover;

import ch.luca.creative.Creative;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class ItemRemover implements Runnable {

    int timeLimit = 500;
    int counter = timeLimit;
    int items = 0;
    @Override
    public void run() {
        counter--;
        if(counter == 120)
            Bukkit.broadcastMessage(Creative.getPrefix() + "Items werden in §e" + counter + " §7Sekunden entfernt.");
        if(counter == 60)
            Bukkit.broadcastMessage(Creative.getPrefix() + "Items werden in §e" + counter + " §7Sekunden entfernt.");
        if(counter == 40)
            Bukkit.broadcastMessage(Creative.getPrefix() + "Items werden in §e" + counter + " §7Sekunden entfernt.");
        if(counter == 20)
            Bukkit.broadcastMessage(Creative.getPrefix() + "Items werden in §e" + counter + " §7Sekunden entfernt.");
        if(counter < 4 && counter != 0) {
            Bukkit.broadcastMessage(Creative.getPrefix() + "Items werden in §e" + counter + " §7Sekunden entfernt.");
        }
        if(counter == 0) {
            for(World w : Bukkit.getWorlds()) {
                for(Entity e : w.getEntities()) {
                    if(e.getType().equals(EntityType.DROPPED_ITEM)) {
                        e.remove();
                        items++;
                    }
                }
            }
            Bukkit.broadcastMessage(Creative.getPrefix() + "Es wurden §e" + items + " §7Items entfernt.");
            counter = timeLimit;
            items = 0;
        }
    }
}
