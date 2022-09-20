package ch.luca.creative.api;

import java.io.File;
import java.io.IOException;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationManager {

    public static File Data = new File("plugins/Creative", "Locations.yml");
    public static FileConfiguration File = YamlConfiguration.loadConfiguration(Data);

    public static void setLocation(String Name, Location loc) {
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();
        World world = loc.getWorld();
        File.set(Name + ".X", Double.valueOf(x));
        File.set(Name + ".Y", Double.valueOf(y));
        File.set(Name + ".Z", Double.valueOf(z));
        File.set(Name + ".YAW", Double.valueOf(yaw));
        File.set(Name + ".PITCH", Double.valueOf(pitch));
        File.set(Name + ".WELT", world.getName());
        try {
            File.save(Data);
        } catch (IOException e1) {
        }
    }

    public static Location getLocation(String Name) {
        double x = File.getDouble(Name + ".X");
        double y = File.getDouble(Name + ".Y");
        double z = File.getDouble(Name + ".Z");
        float yaw = (float) File.getDouble(Name + ".YAW");
        float pitch = (float) File.getDouble(Name + ".PITCH");
        World world = Bukkit.getWorld(File.getString(Name + ".WELT"));
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static void setSpawnLoc(Location loc) {
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();
        World world = loc.getWorld();
        File.set("Spawn.X", Double.valueOf(x));
        File.set("Spawn.Y", Double.valueOf(y));
        File.set("Spawn.Z", Double.valueOf(z));
        File.set("Spawn.YAW", Double.valueOf(yaw));
        File.set("Spawn.PITCH", Double.valueOf(pitch));
        File.set("Spawn.WELT", world.getName());
        try {
            File.save(Data);
        } catch (IOException e1) {
        }
    }

    public static Location getSpawnLoc() {
        double x = File.getDouble("Spawn.X");
        double y = File.getDouble("Spawn.Y");
        double z = File.getDouble("Spawn.Z");
        float yaw = (float) File.getDouble("Spawn.YAW");
        float pitch = (float) File.getDouble("Spawn.PITCH");
        World world = Bukkit.getWorld(File.getString("Spawn.WELT"));
        return new Location(world, x, y, z, yaw, pitch);
    }

}