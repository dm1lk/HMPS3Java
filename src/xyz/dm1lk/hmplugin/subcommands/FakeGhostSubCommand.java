package xyz.dm1lk.hmplugin.subcommands;

import org.bukkit.command.CommandSender;
import xyz.dm1lk.hmplugin.interfaces.SubCommand;

import java.util.List;

import static org.bukkit.Bukkit.broadcastMessage;

public class FakeGhostSubCommand extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length > 1) {
            StringBuilder string = new StringBuilder();
            for (String currentIndexValue : args) {
                if (!currentIndexValue.equals("fakeghost")) {
                    string.append(currentIndexValue).append(" ");
                }
            }
            broadcastMessage("§c☠§l " + string);
        } else {
            sender.sendMessage("§c§lHMP §8>>§c Please include arguments for this command.");
        }
    }

    @Override
    public String getName() {
        return "fakeghost";
    }

    @Override
    public String getInfo() {
        return "Prints a fake death message in chat.";
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public List<String> getArguments() {
        return null;
    }
}
