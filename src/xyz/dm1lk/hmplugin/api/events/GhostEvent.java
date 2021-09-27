package xyz.dm1lk.hmplugin.api.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class GhostEvent extends Event implements Cancellable {
    private final static HandlerList handlers = new HandlerList();
    private final Player player;
    private boolean cancelled;

    public GhostEvent (UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = true;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
