package ch.luca.cubeslide.creative.commands;

import ch.luca.cubeslide.creative.Creative;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Tpaccept implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNotPlayer());
            return true;
        }
        Player p = (Player) sender;
        if (args.length != 0) {
            p.sendMessage(Creative.getPrefix() + "/tpaccept");
        } else if (args.length == 0) {
            if ((Tpa.tpa.get(p) == null)) {
                p.sendMessage(Creative.getPrefix() + "Du hast keine TPA-Anfragen erhalten.");
            } else if (Tpa.tpa.get(p) != null) {
                Player t = (Player) Tpa.tpa.get(p);
                Spawn.teleport.add(t);
                p.sendMessage(Creative.getPrefix() + "Du hast die Anfrage von §e" + t.getDisplayName() + " §7angenommen.");
                t.sendMessage(Creative.getPrefix() + "Die Anfrage an §e" + p.getDisplayName() + " §7wurde angenommen.");
                t.sendMessage("§7Teleportvorgang§8: §c⬜⬜⬜");
                t.playSound(t.getLocation(), Sound.NOTE_BASS, 2, 2);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (Spawn.teleport.contains(t)) {
                            t.sendMessage("§7Teleportvorgang§8: §a⬛§c⬜⬜");
                            t.playSound(t.getLocation(), Sound.NOTE_BASS, 2, 2);
                        }
                    }
                }.runTaskLater(Creative.getInstance(), 20);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (Spawn.teleport.contains(t)) {
                            t.sendMessage("§7Teleportvorgang§8: §a⬛⬛§c⬜");
                            t.playSound(t.getLocation(), Sound.NOTE_BASS, 2, 2);
                        }
                    }
                }.runTaskLater(Creative.getInstance(), 40);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Creative.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        if (Spawn.teleport.contains(t)) {
                            Spawn.teleport.remove(p);
                            Spawn.teleport.remove(t);
                            Tpa.tpa.remove(p);
                            Tpa.tpa.remove(t);
                            t.teleport(p.getLocation());
                            t.sendMessage("§7Teleportvorgang§8: §a⬛⬛⬛");
                            t.sendMessage(Creative.getPrefix() + "Du wurdest zu §e" + p.getName() + " §7teleportiert.");
                            t.playSound(t.getLocation(), Sound.LEVEL_UP, 2, 2);
                        }
                    }
                }, 60);
            } else {
                p.sendMessage(Creative.getPrefix() + "Du hast keine TPA-Anfragen erhalten.");
            }
        }
        return false;
    }
}

