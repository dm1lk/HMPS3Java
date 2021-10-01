package xyz.dm1lk.hmplugin.subcommands;

import org.bukkit.command.CommandSender;
import xyz.dm1lk.hmplugin.interfaces.SubCommand;
import xyz.dm1lk.hmplugin.internal.CommandManager;

import java.util.ArrayList;
import java.util.List;

public class HelpSubCommand extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        List<String> visibleCommands = new ArrayList<>();
        List<SubCommand> availableCommands = CommandManager.getSubCommands();
        for (SubCommand subcommand : availableCommands) {
            if (subcommand.getPermission() != null) {
                if (sender.hasPermission(subcommand.getPermission())) {
                    visibleCommands.add("§c/hmp " + subcommand.getName() + " - " + subcommand.getInfo());
                }
            } else {
                visibleCommands.add("§c/hmp " + subcommand.getName() + " - " + subcommand.getInfo());
            }
        }
        sender.sendMessage("§4§m==================§c§l HMP §4§m===================\n" + String.join("\n", visibleCommands) + "\n§4§m==========================================");
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getInfo() {
        return "Provides a list of plugin commands.";
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
