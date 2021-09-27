package xyz.dm1lk.hmplugin.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import xyz.dm1lk.hmplugin.Main;
import xyz.dm1lk.hmplugin.commands.SubCommand;
import xyz.dm1lk.hmplugin.internal.AnimationManager;

import java.util.List;

public class ReviveSubCommand extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Bukkit.getLogger().info(args.toString());
        Configuration configuration = Main.getPlugin().getConfig();
        if (args.length > 1) {
            Player player = ((player = Bukkit.getPlayer(args[1])) != null) ? player : null;
            if (player != null && player.isOnline()) {
                if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', configuration.getString("messages.admin.already-alive")).replace("%player%", player.getName()));
                } else {
                    AnimationManager.adminRevive(player);
                }
            } else {
                sender.sendMessage("§c§lHMP §8>>§cCannot find a player by this name who is online right now.");
            }
        } else {
            sender.sendMessage("§c§lHMP §8>>§cUSAGE: /hmp revive [player]");
        }
    }

    @Override
    public String getName() {
        return "revive";
    }

    @Override
    public String getInfo() {
        return "Bring a specified player back to life.";
    }

    @Override
    public String getPermission() {
        return "hmp.revive";
    }

    @Override
    public List<String> getArguments() {
        return null;
    }
}
