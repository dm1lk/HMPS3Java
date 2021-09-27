package xyz.dm1lk.hmplugin.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.dm1lk.hmplugin.Main;
import xyz.dm1lk.hmplugin.internal.AnimationManager;

import java.util.Arrays;

import static org.bukkit.GameMode.SURVIVAL;
import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;
import static xyz.dm1lk.hmplugin.Main.reviveFlag;

public class onPlayerInteract implements Listener {

    // TODO: Ensure ReviveBookItem activates revive machine animation, this can be done by using AnimationManager.ReviveMachine();
    // TODO: Should also probably make it so only one revival can happen at once. ;-;

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Configuration config = Main.getPlugin().getConfig();
        if (config.getBoolean("hmpublic.enableInteractableReviveBook")) {
            if (event.getItem() != null && (event.getAction().equals(RIGHT_CLICK_AIR) || event.getAction().equals(RIGHT_CLICK_BLOCK)) && (event.getItem().getType().equals(Material.BOOK))) {
                ItemMeta itemMeta = event.getItem().getItemMeta();
                if (itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) && itemMeta.getLore().equals(Arrays.asList("§c§lRight-click to revive yourself.", "§7You must be on the revive alter for use.", "§fCONSUMABLE")) && event.getPlayer().getInventory().getItemInMainHand().equals(event.getItem())) {
                    if (event.getPlayer().getGameMode() != SURVIVAL) {
                        RegionQuery query = Main.getWorldGuard().getPlatform().getRegionContainer().createQuery();
                        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(event.getPlayer().getLocation()));
                        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(event.getPlayer());
                        if (reviveFlag != null && set.testState(localPlayer, reviveFlag)) {
                            Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), () -> {
                                if (event.getItem().getAmount() == 1) {
                                    player.getInventory().setItemInMainHand(null);
                                } else {
                                    event.getItem().setAmount(event.getItem().getAmount() - 1);
                                }
                                AnimationManager.adminRevive(player);
                            });
                        }
                    }
                }
            }
        }
    }
}
