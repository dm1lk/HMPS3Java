package xyz.dm1lk.hmp.subcommands;

import org.bukkit.command.CommandSender;
import xyz.dm1lk.hmp.Main;
import xyz.dm1lk.hmp.interfaces.SubCommand;

import java.util.List;

public class ReloadSubCommand extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Main.reloadConfiguration(sender);
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getInfo() {
        return "Reloads plugin configuration.";
    }

    @Override
    public String getPermission() {
        return "hmp.reload";
    }

    @Override
    public List<String> getArguments() {
        return null;
    }
}
