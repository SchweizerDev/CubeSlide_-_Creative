package ch.luca.creative.commands;

import ch.luca.creative.api.Actionbar;
import ch.luca.creative.api.LocationManager;
import ch.luca.creative.Creative;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Spawn implements CommandExecutor {

    public static ArrayList<Player> teleport = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNotPlayer());
            return true;
        }
        Player p = (Player)sender;
        if(teleport.contains(p)) {
            teleport.remove(p);
            p.sendMessage(Creative.getPrefix() + "Die Teleportation wurde abgebrochen.");
            return true;
        }
        final Location spawn = LocationManager.getSpawnLoc();
        if(!teleport.contains(p)) {
            teleport.add(p);
        }
        Actionbar.send(p, "§7Teleportvorgang§8: §c⬜⬜⬜");
        p.playSound(p.getLocation(), Sound.NOTE_BASS, 2, 2);
        new BukkitRunnable() {
            @Override
            public void run() {
                if(teleport.contains(p)) {
                    Actionbar.send(p, "§7Teleportvorgang§8: §a⬛§c⬜⬜");
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 2, 2);
                }
            }
        }.runTaskLater(Creative.getInstance(), 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                if(teleport.contains(p)) {
                    Actionbar.send(p, "§7Teleportvorgang§8: §a⬛⬛§c⬜");
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 2, 2);
                }
            }
        }.runTaskLater(Creative.getInstance(), 40);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Creative.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(teleport.contains(p)) {
                    teleport.remove(p);
                    p.teleport(spawn);
                    Actionbar.send(p, "§7Teleportvorgang§8: §a⬛⬛⬛");
                    p.sendMessage(Creative.getPrefix() + "Du wurdest zum Spawn teleportiert.");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 2);
                }
            }
        }, 60);
        return false;
    }
}
