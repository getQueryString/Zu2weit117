// Copyright© by Fin & from another source

package Listeners;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getConsoleSender;

public class CheckPing /*implements Listener*/ {

    /*private Main plugin;
    Plugin context;

    public CheckPing(Plugin pl) {
        this.context = pl;
        this.plugin = Main.getPlugin(Main.class);
        startRunnableHighPlayerPing();
    }

    private void startRunnableHighPlayerPing() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    // Wenn der Spieler in der letzen Abfrage über 1000ms hat
                    if (Main.pingPlayers.containsKey(p)) {
                        // Holt die letzten Pings, die der Spieler hatte
                        ArrayList<Integer> lastPings = Main.pingPlayers.get(p);

                        // Holt den letzen Ping, den der Spieler hatte
                        int lastPing = lastPings.get(lastPings.size());
                        // Holt den derzeitigen Ping, den der Spieler hat
                        int currentPing = getPing(p);

                        // Wenn 15 Pings eingetragen sind
                        if (lastPings.size() >= 5) {
                            // Ob letzer & derzeitiger Ping >= 800ms ist
                            if (lastPing >= 800 && currentPing >= 800) {
                                // Löscht den Spieler aus dem Zwischenspeicher
                                Main.pingPlayers.remove(p);
                                p.kickPlayer("§cDein Ping war im vorgegebenen Zeitraum mit §e" + getPing(p) + "ms §czu hoch");
                                continue;
                            }
                        }
                        // Ob letzer Ping >= 800ms war
                        if (lastPing >= 800) {
                            // Ob derzeitiger Ping >= 800ms ist
                            if (currentPing >= 800) {
                                // Fügt den derzeitigen Ping zur Liste hinzu
                                lastPings.add(currentPing);

                                // Update für die HashMap
                                Main.pingPlayers.remove(p);
                                Main.pingPlayers.put(p, lastPings);
                            } else {
                                // Wenn nicht, wird der Spieler aus dem Zwischenspeicher gelöscht
                                Main.pingPlayers.remove(p);
                            }
                        }
                    } else {
                        // Wenn nicht, wird der derzeitige Ping abgefragt
                        int currentPing = getPing(p);

                        // Ob derzeitiger Ping >= 500ms
                        if (currentPing >= 500) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                boolean allpermexo = PermissionsEx.getUser(all).inGroup("Owner");
                                boolean allpermexv = PermissionsEx.getUser(all).inGroup("Vice");
                                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1F, 1F);
                                p.sendTitle("§4§lACHTUNG!", "§4Hoher Ping!: §e" + getPing(p) + "ms.", 5, 20, 5);
                                if (allpermexo) {
                                    if (p != all) {
                                        all.sendMessage("§4§l" + p.getName() + "'s §4current Ping: §e" + getPing(p) + "ms.");
                                    }
                                    getConsoleSender().sendMessage("§4" + p.getName() + "'s §4current Ping: §e" + getPing(p) + "ms.");
                                } else if (allpermexv) {
                                    if (p != all) {
                                        all.sendMessage("§c" + p.getName() + "'s §4current Ping: §e" + getPing(p) + "ms.");
                                    }
                                    getConsoleSender().sendMessage("§c" + p.getName() + "'s §4current Ping: §e" + getPing(p) + "ms.");
                                }
                            }
                        } else if (currentPing >= 800) {
                            ArrayList<Integer> pings = new ArrayList<>();

                            // Wenn ja, wird der Spieler zum Zwischenspeicher hinzugefügt
                            pings.add(currentPing);
                            Main.pingPlayers.put(p, pings);
                        }
                    }
                }
            }

        }.runTaskTimer(plugin, 0, 60);
    }

    private int getPing(Player p) {
        try {
            int ping = (((CraftPlayer) p).getHandle()).ping;
            return ping;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }*/
}