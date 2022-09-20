package ch.luca.cubeslide.creative;

import ch.luca.cubeslide.creative.itemremover.ItemRemover;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ch.luca.cubeslide.creative.commands.*;
import ch.luca.cubeslide.creative.listener.*;

import java.io.File;

public class Creative extends JavaPlugin {

    public File file = new File("plugins/Creative/config.yml");
    PluginManager pm = Bukkit.getPluginManager();

    @Getter
    private static String prefix = "§8[§2Creative§8] §7";
    @Getter
    private static String noPerms = "§8[§2Creative§8] §7Du hast keine Berechtigung.";
    @Getter
    private static String notOnline = "§8[§2Creative§8] §7Dieser Spieler ist nicht online.";
    @Getter
    private static String notPlayer = "§8[§2Creative§8] §7Du bist kein Spieler.";

    @Getter
    private static Creative instance;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "Creative wurde §aaktiviert§7!");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "Author§8: §eLuca §7| §7Version§8: §a1.5");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "MySQL wird verbunden...");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "Alle Daten wurden §aerfolgreich §7geladen!");
        instance = this;
        getServer().getScheduler().runTaskTimer(this, new ItemRemover(), 20L, 20L);

        //** Commands **\\
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("setspawn").setExecutor(new Setspawn());
        getCommand("abfall").setExecutor(new Abfall());
        getCommand("commandspy").setExecutor(new Commandspy());
        getCommand("gamemode").setExecutor(new Gamemode());
        getCommand("kopf").setExecutor(new Kopf());
        getCommand("rename").setExecutor(new Rename());
        getCommand("tpa").setExecutor(new Tpa());
        getCommand("tpaccept").setExecutor(new Tpaccept());
        getCommand("teleport").setExecutor(new Teleport());
        getCommand("creative").setExecutor(new Creative_Command());
        getCommand("worlds").setExecutor(new Worlds());
        getCommand("hat").setExecutor(new Hat());

        //** Listener **\\
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        pm.registerEvents(new PlayerCommandPreprocessListener(), this);
        pm.registerEvents(new PlayerAchievementAwardedListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new PlayerMoveListener(), this);
        pm.registerEvents(new PlayerRespawnListener(), this);
        pm.registerEvents(new ThunderChangeListener(), this);
        pm.registerEvents(new WeatherChangeListener(), this);
        pm.registerEvents(new BlockRedstoneListener(), this);
        pm.registerEvents(new ProjectileHitListener(), this);
        pm.registerEvents(new SignChangeListener(), this);

    }

    @Override
    public void onDisable() {

    }
}
