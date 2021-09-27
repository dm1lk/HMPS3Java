package xyz.dm1lk.hmplugin.subcommands;

import org.bukkit.command.CommandSender;
import xyz.dm1lk.hmplugin.commands.SubCommand;
import xyz.dm1lk.hmplugin.internal.AnimationManager;

import java.util.List;

public class ExecuteSubCommand extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        AnimationManager.reviveMachine();
    }

    @Override
    public String getName() {
        return "execute";
    }

    @Override
    public String getInfo() {
        return "Directly calls upon the revive machine method.";
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
