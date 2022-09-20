package ch.luca.creative.commands;

import ch.luca.creative.Creative;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commandspy implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Creative.getNotPlayer());
            return true;
        }
        Player p = (Player)sender;
        if(p.hasPermission("creative.commandspy")) {
            if(args.length == 1) {
                if(Creative.getInstance().getConfig().getBoolean("CmdSpy." + args[0])) {
                    Creative.getInstance().getConfig().set("CmdSpy." + args[0], Boolean.valueOf(false));
                    Creative.getInstance().saveConfig();
                    p.sendMessage(Creative.getPrefix() + "Du hast den CommandSpy von §e" + args[0] + " §cdeaktivert§7.");
                } else {
                    Creative.getInstance().getConfig().set("CmdSpy." + args[0], Boolean.valueOf(true));
                    Creative.getInstance().saveConfig();
                    p.sendMessage(Creative.getPrefix() + "Du hast den CommandSpy von §e" + args[0] + " §aaktivert§7.");
                }
            } else {
                if(Creative.getInstance().getConfig().getBoolean("CmdSpy." + p.getName())) {
                    Creative.getInstance().getConfig().set("CmdSpy." + p.getName(), Boolean.valueOf(false));
                    Creative.getInstance().saveConfig();
                    p.sendMessage(Creative.getPrefix() + "Du hast dein CommandSpy §cdeaktiviert§7.");
                } else {
                    Creative.getInstance().getConfig().set("CmdSpy." + p.getName(), Boolean.valueOf(true));
                    Creative.getInstance().saveConfig();
                    p.sendMessage(Creative.getPrefix() + "Du hast dein CommandSpy §aaktiviert§7.");
                }
            }
        } else {
            p.sendMessage(Creative.getNoPerms());
        }
        return false;
    }
}
