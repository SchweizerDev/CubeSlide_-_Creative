package ch.luca.cubeslide.creative.commands;

import ch.luca.cubeslide.creative.Creative;
import ch.luca.cubeslide.creative.api.LocationManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Setspawn implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNotPlayer());
            return true;
        }
        Player p = (Player)sender;
        if(p.hasPermission("creative.setspawn")) {
            if(args.length == 0) {
                Location location = p.getLocation();
                LocationManager.setSpawnLoc(location);
                p.sendMessage(Creative.getPrefix() + "Der Spawn wurde gesetzt.");
            } else {
                p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/setspawn");
            }
        } else {
            p.sendMessage(Creative.getNoPerms());
        }
        return false;
    }
}
