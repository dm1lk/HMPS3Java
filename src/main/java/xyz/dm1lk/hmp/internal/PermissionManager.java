package xyz.dm1lk.hmp.internal;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.dm1lk.hmp.Main;

public class PermissionManager {
    private static final LuckPerms luckperms = Main.getLuckperms();

    public static void enableGhostPermissions(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), () -> luckperms.getUserManager().modifyUser(player.getUniqueId(), user ->
                user.data().add(Node.builder("group." + Main.getPlugin().getConfig().getString("settings.ghost-permission-group")).build())));
    }

    public static void disableGhostPermissions(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), () -> luckperms.getUserManager().modifyUser(player.getUniqueId(), user ->
                user.data().remove(Node.builder("group." + Main.getPlugin().getConfig().getString("settings.ghost-permission-group")).build())));
    }
}
