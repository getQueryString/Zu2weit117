package Listeners;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Autoshutdown implements Listener, CommandExecutor {

    Plugin context;
    BukkitRunnable timer = null;
    boolean enabled = true;

    public Autoshutdown(Plugin plugin) {
        this.context = plugin;
        if (Bukkit.getOnlinePlayers().size() == 0) {
            startTimer();
        }
    }

    public void startTimer() {
        this.timer = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.shutdown();
            }
        };
        timer.runTaskLater(context, 20 * 60 * 5);

    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        if (Bukkit.getOnlinePlayers().size() <= 1) {
            startTimer();
            Bukkit.getConsoleSender().sendMessage("§cThe server will shut down in 5 minutes because there are no more players on the server");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (timer != null) {
            stopTimer();
            Bukkit.getConsoleSender().sendMessage("§cTimer stopped because the server is no longer empty");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            switch (label) {
                case "starttimer" -> {
                    if (timer != null) {
                        sender.sendMessage("§cTimer is already running");
                    } else if (Bukkit.getOnlinePlayers().size() == 0) {
                        enabled = true;
                        startTimer();
                        sender.sendMessage("§cTimer has started");
                    } else if (Bukkit.getOnlinePlayers().size() >= 1) {
                        sender.sendMessage("§cTimer could not be started because there are players on the server");
                    }
                }
                case "stoptimer" -> {
                    if (timer == null) {
                        sender.sendMessage("§cTimer does not run");
                    } else {
                        enabled = false;
                        stopTimer();
                        sender.sendMessage("§cTimer was interrupted");
                    }
                }
            }
        } else if (PermissionsEx.getUser((Player) sender).inGroup("Owner")) {
            sender.sendMessage(Main.Main.ccs);
        } else {
            sender.sendMessage("Unbekannter Befehl");
        }

        return false;
    }
}