package xyz.dm1lk.hmplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import xyz.dm1lk.hmplugin.Main;
import xyz.dm1lk.hmplugin.subcommands.*;

import java.util.ArrayList;
import java.util.List;


public class CommandManager implements org.bukkit.command.TabExecutor {

    private static final ArrayList<SubCommand> commands = new ArrayList<>();
    public String baseCommand = "hmp";

    public CommandManager() {
    }

    public static List<SubCommand> getSubCommands() {
        return new ArrayList<>(commands);
    }

    public void setup(Main plugin) {
        plugin.getCommand(baseCommand).setExecutor(this);
        plugin.getCommand(baseCommand).setTabCompleter(this);
        commands.add(new HelpSubCommand());
        commands.add(new FakeGhostSubCommand());
        commands.add(new FakeReviveSubCommand());
        commands.add(new ExecuteSubCommand());
        commands.add(new GhostSubCommand());
        commands.add(new ReviveSubCommand());
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (command.getName().equalsIgnoreCase(baseCommand)) {
            if (args.length == 0) {
                sender.sendMessage("§c§lHMP §8>>§c Type /" + baseCommand + " help for a full list of subcommands!");
                return true;
            }

            SubCommand target = this.getSubCommand(args[0]);

            if (target == null) {
                sender.sendMessage("§c§lHMP §8>>§c This is not a valid sub-command!");
                return true;
            }

            try {
                target.onCommand(sender, args);
            } catch (Exception error) {
                sender.sendMessage("§c§lHMP §8>>§c An error has occurred D:\n§cPlease contact a developer.");
                error.printStackTrace();
            }
        }

        return true;
    }

    private SubCommand getSubCommand(String name) {

        for (SubCommand subCommand : commands) {
            if (subCommand.getName().equalsIgnoreCase(name)) {
                return subCommand;
            }
        }
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            List<String> visibleCommands = new ArrayList<>();
            List<SubCommand> availableCommands = CommandManager.getSubCommands();
            for (SubCommand subcommand : availableCommands) {
                if (subcommand.getPermission() != null) {
                    if (sender.hasPermission(subcommand.getPermission())) {
                        visibleCommands.add(subcommand.getName());
                    }
                } else {
                    visibleCommands.add(subcommand.getName());
                }
            }
            return visibleCommands;
        } else {
            SubCommand subCommand = this.getSubCommand(args[0]);
            if (subCommand != null) {
                subCommand.getArguments();
            }
        }
        return null;
    }
}
