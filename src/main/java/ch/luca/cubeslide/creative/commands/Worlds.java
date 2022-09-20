package ch.luca.creative.commands;

import java.io.File;
import java.io.IOException;

import ch.luca.creative.Creative;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Worlds implements CommandExecutor {

    private File welt = new File("plugins/Creative/Worlds.yml");
    private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(this.welt);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNotPlayer());
            return true;
        }
        Player p = (Player) sender;
        String cmderr = Creative.getPrefix() + "Benutze§8: §e/worlds help";
        if (!p.isOp()) {
            p.sendMessage(Creative.getNoPerms());
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                p.sendMessage(Creative.getPrefix() + "§cSystem §8- §7WorldManager");
                p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/worlds setspawn §8- §7Setzt den Weltenspawn");
                p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/worlds create <Name> <flat/normal> §8- §7Generiere eine Welt");
                p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/worlds import <Name> §8- §7Lade eine Welt neu");
                p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/worlds unload <Name> §8- §7Entlade eine Welt");
                p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/worlds tp <Name> §8- §7Teleportiere dich in eine Welt");
            } else if (args[0].equalsIgnoreCase("setspawn")) {
                p.getWorld().setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
                p.sendMessage(Creative.getPrefix() + "Du hast den Spawnpunkt gesetzt!");
            } else {
                p.sendMessage(cmderr);
            }
        } else if (args.length == 2) {
            String wn = args[1];
            World wld = Bukkit.getWorld(wn);
            if (args[0].equalsIgnoreCase("import")) {
                if (wld != null) {
                    p.sendMessage(Creative.getPrefix() + "Diese Welt exsistiert es bereits!");
                    return true;
                }
                File getworld = new File(args[1]);
                if (!getworld.exists()) {
                    p.sendMessage(Creative.getPrefix() + "Diese Welt befindet sich nicht im Serverordner!");
                    return true;
                }
                try {
                    WorldCreator wc = new WorldCreator(wn);
                    p.sendMessage(Creative.getPrefix() + "Bitte warten! Welt wird importiert...");
                    Bukkit.createWorld(wc);
                    Bukkit.getWorld(wn).setAmbientSpawnLimit(0);
                    Bukkit.getWorld(wn).setAnimalSpawnLimit(0);
                    Bukkit.getWorld(wn).setMonsterSpawnLimit(0);
                    Bukkit.getWorld(wn).setWaterAnimalSpawnLimit(0);
                    Bukkit.getWorld(wn).setStorm(false);
                    Bukkit.getWorld(wn).setPVP(true);
                    p.sendMessage(Creative.getPrefix() + "Welt wurde importiert!");
                    this.cfg.set("Worlds." + args[1], Boolean.valueOf(true));
                } catch (Exception e) {
                    p.sendMessage(Creative.getPrefix() + "§4Error...");
                }
                try {
                    this.cfg.save(this.welt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (args[0].equalsIgnoreCase("unload")) {
                if (wld == null) {
                    p.sendMessage(Creative.getPrefix() + "Diese Welt gibt es nicht!");
                    return true;
                }
                if (wld.getPlayers().size() != 0) {
                    p.sendMessage(Creative.getPrefix() + "Es befinden sich spieler in dieser Welt!");
                    return true;
                }
                try {
                    p.sendMessage(Creative.getPrefix() + "Bitte warten! Welt wird entladen...");
                    Bukkit.unloadWorld(wn, true);
                    p.sendMessage(Creative.getPrefix() + "Welt wurde entladen!");
                    this.cfg.set("Worlds." + args[1], null);
                } catch (Exception e) {
                    p.sendMessage(Creative.getPrefix() + "§4Error...");
                }
                try {
                    this.cfg.save(this.welt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (args[0].equalsIgnoreCase("tp")) {
                    if (wld == null) {
                        p.sendMessage(Creative.getPrefix() + "Diese Welt gibt es nicht!");
                        return true;
                    }
                    try {
                        p.teleport(wld.getSpawnLocation());
                        p.sendMessage(Creative.getPrefix() + "Du wirst teleportiert!");
                    } catch (Exception exception) {
                    }
                } else {
                    p.sendMessage(cmderr);
                }
            }
        } else if (args.length == 3) {
            String wn = args[1];
            World wld = Bukkit.getWorld(wn);
            if (args[0].equalsIgnoreCase("create")) {
                if (wld != null) {
                    p.sendMessage(Creative.getPrefix() + "Diese Welt gibt es bereits!");
                    return true;
                }
                try {
                    WorldCreator wc = new WorldCreator(wn);
                    if (args[2].equalsIgnoreCase("flat")) {
                        wc.type(WorldType.FLAT);
                    } else if (args[2].equalsIgnoreCase("normal")) {
                        wc.type(WorldType.NORMAL);
                    } else {
                        p.sendMessage(cmderr);
                        return true;
                    }
                    p.sendMessage(Creative.getPrefix() + "Bitte warten! Welt wird erstellt...");
                    wc.createWorld();
                    Bukkit.createWorld(wc);
                    Bukkit.getWorld(wn).setAmbientSpawnLimit(0);
                    Bukkit.getWorld(wn).setAnimalSpawnLimit(0);
                    Bukkit.getWorld(wn).setMonsterSpawnLimit(0);
                    Bukkit.getWorld(wn).setWaterAnimalSpawnLimit(0);
                    Bukkit.getWorld(wn).setStorm(false);
                    Bukkit.getWorld(wn).setPVP(true);
                    p.sendMessage(Creative.getPrefix() + "Welt wurde erstellt!");
                    this.cfg.set("Worlds." + args[1], Boolean.valueOf(true));
                } catch (Exception exception) {
                }
                try {
                    this.cfg.save(this.welt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                p.sendMessage(cmderr);
            }
        } else {
            p.sendMessage(cmderr);
        }
        return true;
    }
}


