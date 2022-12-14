package ch.luca.cubeslide.creative.commands;

import ch.luca.cubeslide.creative.Creative;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNotPlayer());
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission("creative.teleport")) {
            if (args.length == 0) {
                p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/tp <Spieler> <Spieler>");
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0].toLowerCase());
                if (target != null) {
                    Location targetlocation = target.getLocation();
                    p.teleport(targetlocation);
                    p.sendMessage(Creative.getPrefix() + "Du hast dich zu §e" + target.getName() + " §7teleportiert.");
                } else {
                    p.sendMessage(Creative.getNotOnline());
                }
            } else if (args.length == 2) {
                Player target_1 = Bukkit.getPlayer(args[0].toLowerCase());
                Player target_2 = Bukkit.getPlayer(args[1].toLowerCase());
                if ((target_1 != null) && (target_2 != null)) {
                    Location tloc2 = target_2.getLocation();
                    target_1.teleport(tloc2);
                    target_1.sendMessage(Creative.getPrefix() + "Du wurdest zu §e" + target_2.getDisplayName() + " §7teleportiert.");
                    target_2.sendMessage(Creative.getPrefix() + "Der Spieler §e" + target_1.getDisplayName() + " §7wurde zu dir teleportiert.");
                    if ((target_1 != p) || (target_2 != p)) {
                        p.sendMessage(Creative.getPrefix() + "Der Spieler §e" + target_1.getDisplayName() + " §7wurde zu §a" + target_2.getDisplayName() + " §7teleportiert.");
                        return true;
                    }
                } else {
                    p.sendMessage(Creative.getNotOnline());
                }
            } else if (args.length == 3) {
                try {
                    Location loc = p.getLocation();
                    double x;
                    if (args[0].equalsIgnoreCase("~")) {
                        x = loc.getX();
                    } else {
                        x = Integer.parseInt(args[0]);
                    }
                    double y;
                    if (args[0].equalsIgnoreCase("~")) {
                        y = loc.getX();
                    } else {
                        y = Integer.parseInt(args[1]);
                    }
                    double z;
                    if (args[0].equalsIgnoreCase("~")) {
                        z = loc.getX();
                    } else {
                        z = Integer.parseInt(args[2]);
                    }
                    loc.setX(x);
                    loc.setY(y);
                    loc.setZ(z);
                    p.teleport(loc);
                    p.sendMessage(Creative.getPrefix() + "Du wurdest teleportiert.");
                } catch (NumberFormatException error) {
                    p.sendMessage(Creative.getPrefix() + "Die Koordinaten müssen eine Zahl sein.");
                }
            }
        } else {
            p.sendMessage(Creative.getNoPerms());
        }
        return false;
    }
}
