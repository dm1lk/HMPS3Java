package xyz.dm1lk.hmp.listeners;

import org.bukkit.event.Listener;

public class onPlayerInteract implements Listener {
/*
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
    } */
}
