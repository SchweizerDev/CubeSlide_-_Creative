package ch.luca.creative.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;


public class Util {

    public static void healPlayer(Player p) {
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(20);
        p.setFireTicks(0);
        for (PotionEffect effect : p.getActivePotionEffects()) {
            if (p.hasPotionEffect(effect.getType())) {
                p.removePotionEffect(effect.getType());
            }
        }
    }

    public static String asString(long l) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(l).replace(",", "'");
    }

    public static void removeItemFromHand(Player p, int amount) {
        ItemStack hand = p.getItemInHand();
        if (amount <= 0) {
            return;
        }
        if ((hand == null) || (hand.getType() == Material.AIR)) {
            return;
        }
        if (hand.getAmount() <= amount) {
            p.setItemInHand(new ItemStack(Material.AIR));
        } else {
            hand.setAmount(hand.getAmount() - amount);
        }
    }

    public static int getAvailableItems(Inventory inv, ItemStack item) {
        int counted = 0;
        ItemStack[] arrayOfItemStack;
        int j = (arrayOfItemStack = inv.getContents()).length;
        for (int i = 0; i < j; i++) {
            ItemStack content = arrayOfItemStack[i];
            if ((content != null) && (content.getType() != Material.AIR) && (content.getType() == item.getType())
                    && (content.getDurability() == item.getDurability())) {
                counted += content.getAmount();
            }
        }
        return counted;
    }

    public static boolean removeItems(Inventory inv, ItemStack item, int amount) {
        if (!hasEnoughItems(inv, item, amount)) {
            return false;
        }
        int toRemove = amount;
        HashMap<Integer, ItemStack> slots = new HashMap<Integer, ItemStack>();
        ItemStack slotItem;
        for (int slot = 0; slot < inv.getSize(); slot++) {
            slotItem = inv.getItem(slot);
            if ((slotItem != null) && (slotItem.getType() != Material.AIR) && (slotItem.getType() == item.getType())
                    && (slotItem.getDurability() == item.getDurability())) {
                slots.put(Integer.valueOf(slot), slotItem);
            }
        }
        for (Map.Entry<Integer, ItemStack> entrySlots : slots.entrySet()) {
            if (((ItemStack) entrySlots.getValue()).getAmount() <= toRemove) {
                inv.setItem(((Integer) entrySlots.getKey()).intValue(), new ItemStack(Material.AIR));
                toRemove -= ((ItemStack) entrySlots.getValue()).getAmount();
            } else {
                ItemStack invItem = inv.getItem(((Integer) entrySlots.getKey()).intValue());
                invItem.setAmount(invItem.getAmount() - toRemove);
                break;
            }
        }
        return true;
    }

    public static boolean hasEnoughItems(Inventory inv, ItemStack item, int amount) {
        return getAvailableItems(inv, item) >= amount;
    }

    public static void addItem(Player p, ItemStack item) {
        if (p.getInventory().firstEmpty() == -1) {
        } else {
            p.getInventory().addItem(new ItemStack[]{item});
        }
    }

    public static Random rand = new Random();
    public static int randInt(int min, int max) {
        int randomNum = rand.nextInt(max - min + 1) + min;
        return randomNum;
    }

    public static boolean isInt(String amount) {
        try {
            Integer.parseInt(amount);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void runForAll(AllPlayer ap) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            ap.run(p);
        }
    }
    public interface AllPlayer{
        public void run(Player p);
    }

}
