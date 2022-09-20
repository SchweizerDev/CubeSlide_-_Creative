package ch.luca.cubeslide.creative.commands;

import ch.luca.cubeslide.creative.Creative;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Abfall implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;
        p.openInventory(Bukkit.createInventory(null, 36, Creative.getPrefix() + "Mülleimer"));
        return false;
    }
}
