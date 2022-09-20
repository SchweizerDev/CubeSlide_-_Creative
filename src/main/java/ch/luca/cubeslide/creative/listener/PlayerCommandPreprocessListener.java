package ch.luca.cubeslide.creative.listener;


import ch.luca.cubeslide.creative.Creative;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class PlayerCommandPreprocessListener implements Listener {

    @EventHandler
    public void onCommandSpy(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        for(Player target : Bukkit.getOnlinePlayers()) {
            if(Creative.getInstance().getConfig().getBoolean("CmdSpy." + target.getName())) {
                target.sendMessage(Creative.getPrefix() + "§e" + p.getName() + " §8➤ §7" + e.getMessage());
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void UnknowCommand(PlayerCommandPreprocessEvent e) {
        if(!e.isCancelled()) {
            Player p = e.getPlayer();
            String msg = e.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if(topic == null) {
                p.sendMessage("§8[§3ProxySystem§8] §7Der Befehl §e" + e.getMessage() + " §7ist uns nicht bekannt.");
                e.setCancelled(true);
            }
        }
    }
}