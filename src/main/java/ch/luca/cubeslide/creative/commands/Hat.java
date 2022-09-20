package ch.luca.creative.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.luca.creative.Creative;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hat implements CommandExecutor {
    private List<Material> blockedMaterials;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Hat() {
        this.blockedMaterials = new ArrayList();
        this.blockedMaterials.addAll(Arrays.asList(Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE,
                Material.LEATHER_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_CHESTPLATE,
                Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE,
                Material.DIAMOND_BOOTS, Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.GOLD_BOOTS,
                Material.GOLD_LEGGINGS, Material.GOLD_CHESTPLATE));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNotPlayer());
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("creative.hat")) {
            p.sendMessage(Creative.getNoPerms());
            return true;
        }
        if (args.length > 1) {
            p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/hat <off>");
            return true;
        }
        if (args.length == 0) {
            ItemStack hand = p.getItemInHand();
            ItemStack head = p.getInventory().getHelmet();
            if ((hand == null) || (hand.getType() == Material.AIR)) {
                p.sendMessage(Creative.getPrefix() + "Du musst ein Item in der Hand halten!");
                return true;
            }
            if (isOtherArmor(hand)) {
                p.sendMessage(Creative.getPrefix() + "Rüstungsteile können nicht als Hat benutzt werden!");
                return true;
            }
            p.getInventory().setHelmet(hand);
            p.setItemInHand(head);
            p.sendMessage(Creative.getPrefix() + "Du hast jetzt dein Hut an.");
        }
        if (args.length == 1) {
            if ((args[0].equalsIgnoreCase("off"))) {
                ItemStack head = p.getInventory().getHelmet();
                if ((head == null) || (head.getType() == Material.AIR)) {
                    p.sendMessage(Creative.getPrefix() + "Du hast kein Item auf dem Kopf.");
                    return true;
                }
                p.getInventory().setHelmet(new ItemStack(Material.AIR));
                if (p.getInventory().firstEmpty() == -1)
                    p.getWorld().dropItemNaturally(p.getLocation(), head);
                else {
                    p.getInventory().addItem(head);
                }
                p.sendMessage(Creative.getPrefix() + "Du hast dein Hut entfernt!");
            } else {
                p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/hat <off>");
                return true;
            }
        }
        return true;
    }

    private boolean isOtherArmor(ItemStack item) {
        return this.blockedMaterials.contains(item.getType());
    }
}