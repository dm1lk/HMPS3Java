package xyz.dm1lk.hmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import xyz.dm1lk.hmp.Main;
import xyz.dm1lk.hmp.internal.PermissionManager;

import static org.bukkit.GameMode.ADVENTURE;
import static org.bukkit.GameMode.SURVIVAL;

public class onPlayerDeath implements Listener {

    public onPlayerDeath() {
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        if (player.getGameMode().equals(SURVIVAL)) {
            player.setGameMode(ADVENTURE);
            PermissionManager.enableGhostPermissions(player);
            player.getWorld().strikeLightningEffect(player.getLocation());
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("messages.death").replace("%cause%", e.getDeathMessage())));
        } else if (player.getGameMode().equals(ADVENTURE)) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("messages.already-dead").replace("%cause%", e.getDeathMessage())));
        }
        e.setDeathMessage(null);
    }
}
