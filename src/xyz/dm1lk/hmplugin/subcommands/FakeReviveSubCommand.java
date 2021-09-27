package xyz.dm1lk.hmplugin.subcommands;

import org.bukkit.command.CommandSender;
import xyz.dm1lk.hmplugin.interfaces.SubCommand;

import java.util.List;

import static org.bukkit.Bukkit.broadcastMessage;

public class FakeReviveSubCommand extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length > 1) {
            StringBuilder string = new StringBuilder();
            for (String currentIndexValue : args) {
                if (!currentIndexValue.equals("fakerevive")) {
                    string.append(currentIndexValue).append(" ");
                }
            }
            broadcastMessage("§a❤§l " + string);
        } else {
            sender.sendMessage("§c§lHMP §8>>§c Please include arguments for this command.");
        }
    }

    @Override
    public String getName() {
        return "fakerevive";
    }

    @Override
    public String getInfo() {
        return "Prints a fake revive message in chat.";
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
