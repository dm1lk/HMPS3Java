package xyz.dm1lk.hmplugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.dm1lk.hmplugin.Main;

public class onPlayerJoin implements Listener {
    FileConfiguration config = Main.getPlugin().getConfig();

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (config.getBoolean("settings.first-join-msg")) {
            if (!player.hasPlayedBefore()) {
                e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("messages.first-join").replace("%player%", player.getName())));
                player.getWorld().strikeLightningEffect(player.getLocation());
                player.setFireTicks(0);
            }
        }
    }
}
