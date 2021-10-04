package xyz.dm1lk.hmp.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dm1lk.hmp.Main;
import xyz.dm1lk.hmp.api.events.ReviveEvent;
import xyz.dm1lk.hmp.interfaces.SubCommand;
import xyz.dm1lk.hmp.internal.AnimationManager;
import xyz.dm1lk.hmp.internal.DataManager;

import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class ExecuteSubCommand extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!DataManager.isReviveMachineActive()) {
            Location podiumLocation = new Location(Bukkit.getWorld("world"), -208.5, 71, 208.5);
            Player player = (Player) podiumLocation.getWorld().getNearbyEntities(podiumLocation, 25, 25, 25).stream()
                    .filter(entity -> entity instanceof Player)
                    .filter(entity -> ((Player) entity).getGameMode().equals(GameMode.ADVENTURE))
                    .findFirst()
                    .orElse(null);
            if (player != null) {
                ReviveEvent reviveEvent = new ReviveEvent(player.getUniqueId());
                Bukkit.getPluginManager().callEvent(reviveEvent);
                if (!reviveEvent.isCancelled()) {
                    DataManager.setReviveMachineActive(true);
                    AnimationManager.playReviveMachineAnimation(Bukkit.getScheduler(), Main.getPlugin(), podiumLocation, player);
                }
            }
        } else {
            getLogger().warning("Revival already in progress!");
        }
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
