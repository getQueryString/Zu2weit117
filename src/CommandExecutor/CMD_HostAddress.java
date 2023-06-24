// Copyright© by Fin

package CommandExecutor;

import Listeners.Color;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CMD_HostAddress implements CommandExecutor {

    String targetColor = null;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ip")) {
            if (sender instanceof Player p) {
                if (PermissionsEx.getUser(p).inGroup("Owner")) {
                    if (args.length == 0)
                        p.sendMessage(Main.pre + " §aDeine IP-Adresse: §e" + p.getAddress().getAddress().getHostAddress());
                    else if (args.length == 1) {
                        try {
                            Player t = Bukkit.getServer().getPlayer(args[0]);
                            targetColor = Color.getPlayerColor(PermissionsEx.getUser(t), t);

                            if (t.getPlayer() == p.getPlayer()) {
                                p.sendMessage(Main.pre + " §aDeine IP-Adresse: §e"
                                        + p.getAddress().getAddress().getHostAddress());
                            } else
                                p.sendMessage(Main.pre + " " + targetColor + t.getName() + "§as IP-Adresse: §e" + t.getAddress().getAddress().getHostAddress());
                        } catch (NullPointerException e) {
                            p.sendMessage(Main.pre + " §cSpieler nicht gefunden!");
                        }
                    } else
                        p.sendMessage("§bBenutze: §f/ip §a<Player>");
                } else
                    p.sendMessage(Main.noperm);
            } else {
                if (args.length == 0)
                    Bukkit.getConsoleSender().sendMessage("§bBenutze: §f/ip §a<Player>");
                else if (args.length == 1) {
                    try {
                        Player t = Bukkit.getServer().getPlayer(args[0]);
                        targetColor = Color.getPlayerColor(PermissionsEx.getUser(t), t);

                        Bukkit.getConsoleSender().sendMessage(Main.pre + " " + targetColor + t.getName() + "§as IP-Adresse: §e" + t.getAddress().getAddress().getHostAddress());
                    } catch (NullPointerException e) {
                        Bukkit.getConsoleSender().sendMessage(Main.pre + " §cSpieler nicht gefunden!");
                    }
                } else
                    Bukkit.getConsoleSender().sendMessage("§bBenutze: §f/ip §a<Player>");
            }
        }
        return false;
    }
}