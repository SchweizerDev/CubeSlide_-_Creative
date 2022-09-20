package ch.luca.creative;

import ch.luca.creative.commands.*;
import ch.luca.creative.itemremover.ItemRemover;
import ch.luca.creative.listener.*;
import ch.luca.creative.manager.UserManager;
import ch.luca.creative.spuren.Effekte;
import ch.luca.creative.spuren.ShopSpuren;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public class Creative extends JavaPlugin {

    public File file = new File("plugins/Creative/config.yml");
    PluginManager pm = Bukkit.getPluginManager();
    public static HashMap<String, ShopSpuren> effekte = new HashMap<String, ShopSpuren>();

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
    @Getter
    private UserManager userManager;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "Creative wurde §aaktiviert§7!");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "Author§8: §eLuca §7| §7Version§8: §a1.5");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "MySQL wird verbunden...");
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "Alle Daten wurden §aerfolgreich §7geladen!");
        instance = this;
        userManager = new UserManager();
        effekte.clear();
        acitvateSpuren();
        for(Player all : Bukkit.getOnlinePlayers()) {
            userManager.getUser(all);
        }
        getServer().getScheduler().runTaskTimer(this, new ItemRemover(), 20L, 20L);

        //** Commands **\\
        getCommand("spuren").setExecutor(new Spuren());
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
        pm.registerEvents(new Effekte(), this);
        pm.registerEvents(new BlockRedstoneListener(), this);
        pm.registerEvents(new ProjectileHitListener(), this);
        pm.registerEvents(new SignChangeListener(), this);

    }

    @Override
    public void onDisable() {

    }

    public void acitvateSpuren() {
        effekte.put("Liebe", new ShopSpuren("Liebe", Material.RED_ROSE, "§eLiebe", 6000, EnumParticle.HEART, null, 1));
        effekte.put("Feuer", new ShopSpuren("Feuer", Material.FIREBALL, "§eFeuer", 6000, EnumParticle.FLAME, null, 3));
        effekte.put("Regen", new ShopSpuren("Regen", Material.WATER_BUCKET, "§eRegen", 6000, EnumParticle.DRIP_WATER, null, 5));
        effekte.put("Rauch", new ShopSpuren("Rauch", Material.COAL, "§eRauch", 6000, EnumParticle.SMOKE_NORMAL, null, 5));
        effekte.put("Musik", new ShopSpuren("Musik", Material.NOTE_BLOCK, "§eMusik", 6000, EnumParticle.NOTE, null, 5));
        effekte.put("EnderSignal", new ShopSpuren("EnderSignal", Material.REDSTONE, "§eEnderSignal", 6000, null, Effect.ENDER_SIGNAL, 1));
        effekte.put("Lava", new ShopSpuren("Lava", Material.LAVA_BUCKET, "§eLava", 6000, EnumParticle.LAVA, null, 1));
        effekte.put("Geist", new ShopSpuren("Geist", Material.SULPHUR, "§eGeist", 6000, EnumParticle.FOOTSTEP, null, 5));
        effekte.put("Wolken", new ShopSpuren("Wolken", Material.IRON_SWORD, "§eWolken", 10000, EnumParticle.CLOUD, null, 5));
        effekte.put("Explosion", new ShopSpuren("Explosion", Material.ARROW, "§eExplosion", 10000, EnumParticle.EXPLOSION_LARGE, null, 1));
        effekte.put("Ender", new ShopSpuren("Ender", Material.ANVIL, "§eEnder", 10000, EnumParticle.PORTAL, null, 5));
        effekte.put("Weiße Funken", new ShopSpuren("Weiße Funken", Material.NETHER_STAR, "§eWeiße Funken", 10000, EnumParticle.FIREWORKS_SPARK, null, 3));
        effekte.put("Redstonestaub", new ShopSpuren("Redstonestaub", Material.REDSTONE_BLOCK, "§eRedstonestaub", 5500, EnumParticle.REDSTONE, null, 5));
        effekte.put("Bunter Staub", new ShopSpuren("Bunter Staub", Material.GLOWSTONE_DUST, "§eBunter Staub", 25000, null, Effect.COLOURED_DUST, 5));
        effekte.put("Schleim", new ShopSpuren("Schleim", Material.SLIME_BALL, "§eSchleim", 5000, EnumParticle.SLIME, null, 5));
        effekte.put("Wellen", new ShopSpuren("Wellen", Material.RAW_FISH, "§eWellen", 4000, EnumParticle.WATER_WAKE, null, 5));
        effekte.put("Schnee", new ShopSpuren("Schnee", Material.SNOW_BALL, "§eSchnee", 4000, EnumParticle.SNOW_SHOVEL, null, 5));
        effekte.put("Lila Funken", new ShopSpuren("Lila Funken", Material.EYE_OF_ENDER, "§eLila Funken", 4000, EnumParticle.SPELL_WITCH, null, 5));
        effekte.put("Dreck", new ShopSpuren("Dreck", Material.BEDROCK, "§eDreck", 3000, EnumParticle.TOWN_AURA, null, 5));
        effekte.put("Criticals", new ShopSpuren("Criticals", Material.IRON_SWORD, "§eCriticals", 3000, EnumParticle.CRIT_MAGIC, null, 5));
        effekte.put("Grüne Funken", new ShopSpuren("Grüne Funken", Material.EMERALD, "§eGrüne Funken", 3000, EnumParticle.VILLAGER_HAPPY, null, 5));
        effekte.put("Zauberei", new ShopSpuren("Zauberei", Material.ENCHANTMENT_TABLE, "§eZauberei", 6000, EnumParticle.ENCHANTMENT_TABLE, null, 5));
        effekte.put("Verärgerter Villager", new ShopSpuren("Verärgerter Villager", Material.SUGAR_CANE, "§eVerärgerter Villager", 6000, EnumParticle.VILLAGER_ANGRY, null, 1));
    }
}
