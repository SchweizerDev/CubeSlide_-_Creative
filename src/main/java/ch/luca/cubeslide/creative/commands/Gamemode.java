package ch.luca.cubeslide.creative.commands;

import ch.luca.cubeslide.creative.Creative;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNotPlayer());
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission("creative.gamemode")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("0")) {
                    p.sendMessage(Creative.getPrefix() + "Du bist nun im §eSurvivial§7.");
                    p.setGameMode(GameMode.SURVIVAL);
                } else if (args[0].equalsIgnoreCase("1")) {
                    p.sendMessage(Creative.getPrefix() + "Du bist nun im §eCreative§7.");
                    p.setGameMode(GameMode.CREATIVE);
                } else if (args[0].equalsIgnoreCase("2")) {
                    p.sendMessage(Creative.getPrefix() + "Du bist nun im §eAdventure§7.");
                    p.setGameMode(GameMode.ADVENTURE);
                } else if (args[0].equalsIgnoreCase("3")) {
                    p.sendMessage(Creative.getPrefix() + "Du bist nun im §eSpectator§7.");
                    p.setGameMode(GameMode.SPECTATOR);
                    p.setAllowFlight(true);
                    p.setFlying(true);
                } else {
                    p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/gm <0/1/2/3> <Name>");
                }
            } else if (args.length == 2) {
                Player t = Bukkit.getPlayer(args[1]);
                if (p.hasPermission("creative.gamemode.other")) {
                    if (args[0].equalsIgnoreCase("0")) {
                        if (t == null) {
                            p.sendMessage(Creative.getNotOnline());
                        } else {
                            t.sendMessage(Creative.getPrefix() + "Du bist nun im §eSurvival§7.");
                            t.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(Creative.getPrefix() + "Der Spieler §b" + t.getName() + " §7ist nun im §eSurvival§7.");
                        }
                    } else if (args[0].equalsIgnoreCase("1")) {
                        if (t == null) {
                            p.sendMessage(Creative.getNotOnline());
                        } else {
                            t.sendMessage(Creative.getPrefix() + "Du bist nun im §eCreative§7.");
                            t.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(Creative.getPrefix() + "Der Spieler §b" + t.getName() + " §7ist nun im §eCreative§7.");
                        }
                    } else if (args[0].equalsIgnoreCase("2")) {
                        if (t == null) {
                            p.sendMessage(Creative.getNotOnline());
                        } else {
                            t.sendMessage(Creative.getPrefix() + "Du bist nun im §eAdventure§7.");
                            t.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage(Creative.getPrefix() + "Der Spieler §b" + t.getName() + " §7ist nun im §eAdventure§7.");
                        }
                    } else if (args[0].equalsIgnoreCase("3")) {
                        if (t == null) {
                            p.sendMessage(Creative.getNotOnline());
                        } else {
                            t.sendMessage(Creative.getPrefix() + "Du bist nun im §eSpectator§7.");
                            t.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(Creative.getPrefix() + "Der Spieler §b" + t.getName() + " §7ist nun im §eSpectator§7.");
                            t.setAllowFlight(true);
                            t.setFlying(true);
                        }
                    } else {
                        p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/gm <0/1/2/3> <Spieler>");
                    }
                } else {
                    p.sendMessage(Creative.getNoPerms());
                }
            } else {
                p.sendMessage(Creative.getPrefix() + "Benutze§8: §e/gm <0/1/2/3> <Spieler>");
            }
        } else {
            p.sendMessage(Creative.getNoPerms());
        }
        return false;
    }
}