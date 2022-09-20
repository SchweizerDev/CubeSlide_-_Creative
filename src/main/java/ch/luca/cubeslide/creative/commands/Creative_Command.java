package ch.luca.creative.commands;

import ch.luca.creative.Creative;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Creative_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNotPlayer());
            return true;
        }
        Player p = (Player) sender;
        if(args.length == 0) {
            p.sendMessage(Creative.getPrefix() + "");
            p.sendMessage(Creative.getPrefix() + "§cSystem §8- §7Creative");
            p.sendMessage(Creative.getPrefix() + "");
            p.sendMessage(Creative.getPrefix() + "§8» §cWillkommen auf §2Creative");
            p.sendMessage(Creative.getPrefix() + "§8» §7Hier kannst du deiner §dFantasie §7freien");
            p.sendMessage(Creative.getPrefix() + "§8» §7lauf lassen. Jedoch beachte die §eRegeln");
            p.sendMessage(Creative.getPrefix() + "§8» §7auf §3Discord§7.");
            p.sendMessage(Creative.getPrefix() + "§8» §7Zusätzlich ist Redstone aus Sicher-");
            p.sendMessage(Creative.getPrefix() + "§8» §7heitsgründen §cdeaktiviert§7. Bei Fragen wende");
            p.sendMessage(Creative.getPrefix() + "§8» §7dich an das §cTeam§7.");
        } else {
            p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/creative");
        }
        return false;
    }
}
