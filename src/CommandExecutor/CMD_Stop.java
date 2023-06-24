// Copyright© by Fin

package CommandExecutor;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CMD_Stop implements CommandExecutor {

    private final Main plugin;

    public CMD_Stop() {
        this.plugin = Main.getPlugin(Main.class);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (cmd.getName().equalsIgnoreCase("stop")) {
                new BukkitRunnable() {
                    int count = 3;

                    public void run() {
                        if (count >= 1) {
                            if (Bukkit.getOnlinePlayers().size() >= 1) {
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    Bukkit.broadcastMessage(
                                            "§8| §4Server§f-§4shutdown in§8: §e" + count + " Sekunde(n)");
                                    all.playSound(all.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 50.0F);
                                    break;
                                }
                            } else if (Bukkit.getOnlinePlayers().size() == 0)
                                Bukkit.getConsoleSender().sendMessage("§8| §4Server§f-§4shutdown in§8: §e" + count + " Sekunde(n)");
                            count--;
                        } else if (count == 0)
                            Bukkit.shutdown();
                    }
                }.runTaskTimer(plugin, 0, 20);
            }
        } else if (PermissionsEx.getUser((Player) sender).inGroup("Owner")) {
            Bukkit.getConsoleSender().sendMessage("§4! §e" + sender.getName() + " tried to stop the server");
            sender.sendMessage("§7| §4Only for the §bConsoleCommandSender");
        } else
            sender.sendMessage(Main.noperm);
        return false;
    }
}