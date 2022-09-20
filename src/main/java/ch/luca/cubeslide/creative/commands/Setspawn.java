package ch.luca.creative.commands;

import ch.luca.creative.api.LocationManager;
import ch.luca.creative.Creative;
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
