package xyz.dm1lk.hmplugin.tasks;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.dm1lk.hmplugin.Main;

import static xyz.dm1lk.hmplugin.Main.reviveFlag;

public class GhostEffectTasks {

    public static BukkitRunnable applyStandardEffects() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getGameMode().equals(GameMode.ADVENTURE)) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 350, 1, true, false));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 10, 0, true, false));
                    }
                }
            }
        };
    }

    public static BukkitRunnable applyGlowing() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getGameMode().equals(GameMode.ADVENTURE)) {
                        Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), () -> {
                            RegionQuery query = Main.getWorldGuard().getPlatform().getRegionContainer().createQuery();
                            ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(player.getLocation()));
                            LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
                            if (reviveFlag != null && set.testState(localPlayer, reviveFlag)) {
                                Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 30, 0, true, false));
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30, 0, true, false));
                                });
                            }
                        });
                    }
                }
            }
        };
    }
}
