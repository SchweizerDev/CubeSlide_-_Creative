package ch.luca.creative.commands;

import ch.luca.creative.Creative;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Kopf implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNotPlayer());
            return true;
        }
        Player p = (Player)sender;
        if (!p.hasPermission("creative.kopf")) {
            p.sendMessage(Creative.getNoPerms());
            return true;
        }
        if (args.length == 1) {
            ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta im = (SkullMeta)is.getItemMeta();
            im.setOwner(args[0]);
            im.setDisplayName(Creative.getPrefix() + "Kopf von §e" + args[0]);
            is.setItemMeta(im);
            p.getInventory().addItem(new ItemStack[] { is });
            p.updateInventory();
            p.sendMessage(Creative.getPrefix() + "Du hast den Kopf von §e" + args[0] + " §7bekommen.");
        } else {
            p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/kopf <Spieler>");
        }
        return true;
    }
}