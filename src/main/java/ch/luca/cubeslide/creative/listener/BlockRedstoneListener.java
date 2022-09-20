package ch.luca.creative.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class BlockRedstoneListener implements Listener {

    @EventHandler
    public void on(BlockRedstoneEvent e) {
        e.setNewCurrent(0);
    }
}
