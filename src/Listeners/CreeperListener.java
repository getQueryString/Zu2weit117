// Copyright© by Fin

package Listeners;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getConsoleSender;

public class CreeperListener implements Listener {

    private final Main plugin;
    Plugin context;

    public CreeperListener(Plugin pl) {
        this.context = pl;
        this.plugin = Main.getPlugin(Main.class);
        teleportCreeper();
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        float eP = 1.5F;    // Standard: 3.0F
        e.setCancelled(true);
        e.getLocation().getWorld().createExplosion(e.getLocation(), eP);
    }

    public void teleportCreeper() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() >= 1)
                    Bukkit.getServer().dispatchCommand(getConsoleSender(), "tp @e[type=creeper] ~ -2 ~");
            }
        }.runTaskTimer(plugin, 1200, 1200);
    }
}