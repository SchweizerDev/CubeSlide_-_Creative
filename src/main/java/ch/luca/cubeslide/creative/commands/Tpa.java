package ch.luca.creative.commands;

import ch.luca.creative.Creative;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Tpa implements CommandExecutor {

    public static HashMap<Player, Player> tpa = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNotPlayer());
            return true;
        }
        Player p = (Player) sender;
        if ((args.length == 0) || (args.length != 1)) {
            p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/tpa <Spieler>");
        }
        if (tpa.containsKey(p)) {
            tpa.remove(p);
        }
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0].toLowerCase());
            tpa.put(target, p);
            p.sendMessage(Creative.getPrefix() + "Du hast eine TPA-Anfrage an §e" + target.getDisplayName() + " §7gesendet.");
            target.sendMessage(Creative.getPrefix() + "Du hast eine TPA-Anfrage von §e" + p.getDisplayName() + " §7erhalten.");
            target.sendMessage(Creative.getPrefix() + "§aAnnehmen§8: §e/tpaccept");
            Bukkit.getScheduler().scheduleSyncDelayedTask(Creative.getInstance(), new Runnable() {

                @Override
                public void run() {
                    if (tpa.get(target) != null) {
                        p.sendMessage(Creative.getPrefix() + "Die TPA-Anfrage an §e" + target.getDisplayName() + " §7ist abgelaufen.");
                        target.sendMessage(Creative.getPrefix() + "Die TPA-Anfrage von §e" + p.getDisplayName() + " §7ist abgelaufen.");
                        tpa.remove(target);
                    }
                }

            }, 1200L);
        }
        return false;
    }
}
