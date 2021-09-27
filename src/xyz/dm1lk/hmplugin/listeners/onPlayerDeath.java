package xyz.dm1lk.hmplugin.listeners;

import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.dm1lk.hmplugin.Main;

import static org.bukkit.GameMode.ADVENTURE;
import static org.bukkit.GameMode.SURVIVAL;

public class onPlayerDeath implements Listener {

    public onPlayerDeath() {
    }

    @EventHandler
    public void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        if (player.getGameMode().equals(SURVIVAL)) {
            player.setGameMode(ADVENTURE);
            player.getWorld().strikeLightningEffect(player.getLocation());
            Main.getLuckperms().getUserManager().modifyUser(player.getUniqueId(), user ->
                    user.data().remove(Node.builder("group.dead").build()));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("messages.death").replace("%cause%", e.getDeathMessage())));
        } else if (player.getGameMode().equals(ADVENTURE)) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("messages.already-dead").replace("%cause%", e.getDeathMessage())));
        }
        e.setDeathMessage(null);
    }
}
