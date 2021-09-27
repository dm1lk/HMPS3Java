package xyz.dm1lk.hmplugin.internal;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class PlayerManager {
    public static void revivePlayer(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
        player.removePotionEffect(PotionEffectType.GLOWING);
        PermissionManager.disableGhostPermissions(player);
    }
}
