// Copyright© by Fin

package CommandExecutor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CMD_Reload implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender || sender.isOp()) {
            if (cmd.getName().equalsIgnoreCase("rl")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("zz.see.reload")) {
                        Bukkit.broadcastMessage(Main.Main.pre + " §cReloading...");
                        break;
                    }
                }

                Bukkit.reload();
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("zz.see.reload")) {
                        Bukkit.broadcastMessage(Main.Main.pre + " §aReload complete.");
                        break;
                    }
                }
            }
        } else {
            Player p = (Player) sender;
            if (!PermissionsEx.getUser(p).inGroup("Owner")) {
                sender.sendMessage(Main.Main.noperm);
            } else {
                p.sendMessage(Main.Main.ccs);
            }
        }
        return false;
    }
}