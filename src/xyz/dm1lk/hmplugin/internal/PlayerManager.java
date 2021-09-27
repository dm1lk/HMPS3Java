package xyz.dm1lk.hmplugin.internal;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import xyz.dm1lk.hmplugin.Main;

import java.io.File;
import java.io.IOException;

public class PlayerManager {
    public static void revivePlayer(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
        player.removePotionEffect(PotionEffectType.GLOWING);
        PermissionManager.disableGhostPermissions(player);
    }

    public static File getPlayerData(Player player) {
        if (!Main.getPlugin().getDataFolder().exists()) {
            Main.getPlugin().getDataFolder().mkdir();
        }
        File pDataFolder = new File(Main.getPlugin().getDataFolder() + "/data/");
        if (!pDataFolder.exists()) {
            pDataFolder.mkdirs();
        }
        File pDataFile = new File(Main.getPlugin().getDataFolder() + "/data/", player.getUniqueId() + ".yml");
        if (!pDataFile.exists()) {
            try {
                pDataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pDataFile;
    }
}
