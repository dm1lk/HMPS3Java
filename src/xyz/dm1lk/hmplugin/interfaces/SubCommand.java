package xyz.dm1lk.hmplugin.interfaces;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {
    public abstract void onCommand(CommandSender sender, String[] args);

    public abstract String getName();

    public abstract String getInfo();

    public abstract String getPermission();

    public abstract List<String> getArguments();
}
