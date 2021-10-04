package xyz.dm1lk.hmp.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dm1lk.hmp.Main;
import xyz.dm1lk.hmp.interfaces.SubCommand;
import xyz.dm1lk.hmp.internal.PermissionManager;

import java.util.List;

public class GhostSubCommand extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length > 1) {
            Player player = ((player = Bukkit.getPlayer(args[1])) != null) ? player : null;
            if (player != null && player.isOnline()) {
                if (player.getGameMode() != org.bukkit.GameMode.SURVIVAL) {
                    sender.sendMessage("§7§o" + player.getName() + " is already a ghost!");
                } else {
                    player.setGameMode(GameMode.ADVENTURE);
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("messages.become-ghost")));
                    PermissionManager.enableGhostPermissions(player);
                }
            } else {
                sender.sendMessage("§c§lHMP §8>>§cCannot find a player by this name who is online right now.");
            }
        } else {
            sender.sendMessage("§c§lHMP §8>>§cUSAGE: /hmp ghost [player]");
        }
    }

    @Override
    public String getName() {
        return "ghost";
    }

    @Override
    public String getInfo() {
        return "Force a specified player six-feet under.";
    }

    @Override
    public String getPermission() {
        return "hmp.ghost";
    }

    @Override
    public List<String> getArguments() {
        return null;
    }
}
