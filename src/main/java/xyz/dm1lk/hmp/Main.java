package xyz.dm1lk.hmp;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.dm1lk.hmp.internal.CommandManager;
import xyz.dm1lk.hmp.internal.DataManager;
import xyz.dm1lk.hmp.listeners.onPlayerDeath;
import xyz.dm1lk.hmp.listeners.onPlayerJoin;
import xyz.dm1lk.hmp.tasks.GhostEffectTasks;

import java.io.File;

public class Main extends JavaPlugin {
    public static StateFlag reviveFlag;
    private static Plugin plugin;
    private static File configFile;
    private static LuckPerms luckPerms;
    private static BukkitScheduler scheduler;
    private static WorldGuard worldGuard;

    public static WorldGuard getWorldGuard() {
        return worldGuard;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void reloadConfiguration(CommandSender sender) {
        scheduler.runTaskAsynchronously(plugin, () -> {
            sender.sendMessage("§c§lHMP §8§l>>§7 Reloading plugin...");
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdir();
            }
            if (!configFile.exists()) {
                plugin.saveDefaultConfig();
            }
            plugin.reloadConfig();
            DataManager.setupAnimationValues();
            sender.sendMessage("§c§lHMP §8§l>>§a Reloaded plugin successfully!");
        });
    }

    public static LuckPerms getLuckperms() {
        return luckPerms;
    }

    @Override
    public void onLoad() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            this.saveDefaultConfig();
        }
    }

    @Override
    public void onEnable() {
        CommandManager commandManager = new CommandManager();
        luckPerms = LuckPermsProvider.get();
        plugin = this;
        PluginManager pluginManager = getServer().getPluginManager();
        scheduler = Bukkit.getScheduler();
        reviveFlag = new StateFlag("revive", false);
        worldGuard = WorldGuard.getInstance();
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            registry.register(reviveFlag);
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get("revive");
            if (existing instanceof StateFlag) {
                reviveFlag = (StateFlag) existing;
            }
        }
        if (!Bukkit.getWorlds().isEmpty()) {
            getLogger().warning("======================");
            getLogger().warning("    RELOAD DETECTED   ");
            getLogger().warning("    Why do you hate   ");
            getLogger().warning("       yourself?      ");
            getLogger().warning("======================");
        }
        pluginManager.registerEvents(new onPlayerDeath(), this);
        pluginManager.registerEvents(new onPlayerJoin(), this);
        scheduler.runTaskTimer(this, (Runnable) GhostEffectTasks.applyStandardEffects(), 0L, 300L);
        scheduler.runTaskTimer(this, (Runnable) GhostEffectTasks.applyReviveIslandEffects(), 0L, 20L);
        DataManager.setupAnimationValues();
        commandManager.setupCommands(this);
    }

    @Override
    public void onDisable() {
        scheduler.cancelTasks(this);
    }
}
