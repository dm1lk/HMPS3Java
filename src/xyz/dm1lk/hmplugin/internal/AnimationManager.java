package xyz.dm1lk.hmplugin.internal;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.dm1lk.hmplugin.Main;
import xyz.dm1lk.hmplugin.api.events.ReviveEvent;

import static xyz.dm1lk.hmplugin.internal.PlayerManager.revivePlayer;

public class AnimationManager {
    private static String[] ringCoordActivatedA;
    private static String[] ringCoordActivatedB;
    private static String[] ringCoordActivatedC;
    private static String[] ringCoordIdleA;
    private static String[] ringCoordIdleB;
    private static String[] ringCoordIdleC;
    private static String[] fireworks;

    public static void saveCoordinates(String[] RingCoordActivatedA, String[] RingCoordActivatedB, String[] RingCoordActivatedC, String[] RingCoordIdleA, String[] RingCoordIdleB, String[] RingCoordIdleC, String[] Fireworks) {
        ringCoordActivatedA = RingCoordActivatedA;
        ringCoordActivatedB = RingCoordActivatedB;
        ringCoordActivatedC = RingCoordActivatedC;
        ringCoordIdleA = RingCoordIdleA;
        ringCoordIdleB = RingCoordIdleB;
        ringCoordIdleC = RingCoordIdleC;
        fireworks = Fireworks;
    }

    public static void playAdminReviveAnimation(Player player) {
        ReviveEvent reviveEvent = new ReviveEvent(player.getUniqueId());
        Bukkit.getPluginManager().callEvent(reviveEvent);
        if (!reviveEvent.isCancelled()) {
            revivePlayer(player);
            FileConfiguration config = Main.getPlugin().getConfig();
            World world = player.getWorld();
            Location location = player.getLocation();
            Firework fw = (Firework) world.spawnEntity(location.add(0, 1, 0), EntityType.FIREWORK);
            FireworkMeta fwm = fw.getFireworkMeta();
            fwm.setPower(0);
            fwm.addEffects(FireworkEffect.builder().withColor(Color.LIME).flicker(true).withFade(Color.AQUA, Color.GRAY, Color.WHITE).build(),
                    FireworkEffect.builder().withColor(Color.AQUA).flicker(true).withFade(Color.GRAY, Color.WHITE, Color.LIME).build(),
                    FireworkEffect.builder().withColor(Color.GRAY).flicker(true).withFade(Color.WHITE, Color.LIME, Color.AQUA).build(),
                    FireworkEffect.builder().withColor(Color.WHITE).flicker(true).withFade(Color.LIME, Color.AQUA, Color.WHITE).build());
            fw.setFireworkMeta(fwm);
            fw.detonate();
            world.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 1000000, 1);
            world.strikeLightningEffect(player.getLocation());
            player.setFireTicks(0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 255));
            Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), () -> {
                player.sendTitle("§a§lADMIN REVIVE", "§7You are no longer a ghost.", 10, 70, 20);
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.revive-message")).replace("%player%", player.getName()));
            });
        }
    }

    public static void playReviveMachineAnimation(BukkitScheduler scheduler, Plugin plugin, Location podiumLocation, Player player) {
        int ticks = 0;
        for (int i = 0; i < 6; i++) {
            ticks = ticks + 24;
            int count = i;
            int finalTicks = ticks;
            scheduler.runTaskLater(plugin, () -> { // Delays each ring by 24 ticks or 1.25s.
                if (finalTicks != 144) {
                    dispatchCommand("clone " + ringCoordActivatedA[count] + ringCoordActivatedB[count] + ringCoordActivatedC[count]);
                    if (finalTicks == 120) { // Renders fireworks.
                        int fireworkTicks = 14;
                        for (int j = 0; j < 7; j++) {
                            fireworkTicks = fireworkTicks + 1;
                            int fireworkCount = j;
                            // Delays each firework by 0.05s.
                            scheduler.runTaskLater(plugin, () -> dispatchCommand("summon firework_rocket " + fireworks[fireworkCount]), fireworkTicks);
                        }
                    }
                } else {
                    dispatchCommand("setblock -209 83 208 minecraft:sea_lantern");
                    scheduler.runTaskLater(plugin, () -> {
                        revivePlayer(player);
                        player.teleport(podiumLocation);
                        DataManager.setReviveMachineActive(false);
                        podiumLocation.getWorld().strikeLightningEffect(podiumLocation);
                        dispatchCommand("playsound minecraft:block.end_portal.spawn master @a -208 72 208 100000 1 1");
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("messages.revive-message").replace("%player%", player.getName())));
                        dispatchCommand("summon firework_rocket -209 72 208 {Silent:yes,LifeTime:1,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:2,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:0,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]}]}}}}");
                    }, 8);
                }
                dispatchCommand("playsound minecraft:block.beacon.activate master @a -208 80 208 100000 1 1");
            }, ticks);
        }
        int ticksTwo = 168;
        for (int j = -1; j < 5; j++) {
            ticksTwo = ticksTwo + 16;
            if (ticksTwo == 200) ticksTwo = ticksTwo + 8;
            else if (ticksTwo == 240) ticksTwo = ticksTwo + 6;
            int count = j;
            int finalTicksTwo = ticksTwo;
            scheduler.runTaskLater(plugin, () -> {
                if (finalTicksTwo == 184) {
                    dispatchCommand("setblock -209 83 208 diamond_block replace");
                } else {
                    dispatchCommand("clone " + ringCoordIdleA[count] + ringCoordIdleB[count] + ringCoordIdleC[count]);
                }
            }, ticksTwo);
        }
    }

    private static void dispatchCommand(String command) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }
}

