package ch.luca.creative.commands;

import ch.luca.creative.Creative;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Rename implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNoPerms());
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission("creative.rename")) {
            ItemStack hand = p.getItemInHand();
            if ((hand == null) || (hand.getType() == Material.AIR)) {
                p.sendMessage(Creative.getPrefix() + "Du musst ein Item in der Hand halten.");
                return true;
            }
            ItemMeta meta = hand.getItemMeta();
            if (args.length == 0) {
                if ((meta == null) || (hand.getItemMeta().getDisplayName() == null)) {
                    p.sendMessage(Creative.getPrefix() + "Der Name kann nicht zurückgesetzt werden.");
                    return true;
                }
                meta.setDisplayName(null);
                hand.setItemMeta(meta);
                p.sendMessage(Creative.getPrefix() + "Der Name wurde erfolgreich zurückgesetzt.");
                return true;
            }
            String name = "";
            for (int i = 0; i < args.length; i++) {
                if (name.isEmpty()) {
                    name = args[i];
                    continue;
                }
                name = name + " " + args[i];
            }
            String neunerName = ChatColor.translateAlternateColorCodes('&', name);
            meta.setDisplayName(neunerName);
            hand.setItemMeta(meta);
            p.sendMessage(Creative.getPrefix() + "Das Item wurde erfolgreich umbenannt.");
            return true;
        } else {
            p.sendMessage(Creative.getNoPerms());
        }
        return false;
    }
}