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

public class CMD_Ping implements CommandExecutor {

    String targetColor = null;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ping")) {
            if (sender instanceof Player p) {
                if (PermissionsEx.getUser(p).inGroup("Owner") || PermissionsEx.getUser(p).inGroup("Vice") || PermissionsEx.getUser(p).inGroup("Fellow")) {
                    if (args.length == 0) {
                        //int ping = (((CraftPlayer) p).getHandle()).ping;
                        p.sendMessage(Main.pre + " §aDein Ping: §4ERROR§ems.");
                    } else if (args.length >= 2) {
                        p.sendMessage("§bBenutze: §f/§cp§fing §a<Player>");
                    }
                    if (args.length == 1) {
                        try {
                            Player t = Bukkit.getServer().getPlayer(args[0]);
                            //int ping = (((CraftPlayer) t).getHandle()).ping;
                            if (t.getPlayer() == p.getPlayer()) {
                                p.sendMessage(Main.pre + " §aDein Ping: §4ERROR§ems.");
                            } else {
                                targetColor = Color.getPlayerColor(PermissionsEx.getUser(t), t);

                                //p.sendMessage(Main.pre + " " + playerColor + t.getName() + "§a's Ping: §e" + ping + "ms.");
                                p.sendMessage(Main.pre + " " + targetColor + t.getName() + "§a's Ping: §4ERROR§ems.");
                            }
                        } catch (NullPointerException e) {
                            p.sendMessage(Main.pre + " §cSpieler nicht gefunden!");
                        }
                    }
                } else {
                    p.sendMessage(Main.noperm);
                }
            } else {
                if (args.length == 0) {
                    Bukkit.getConsoleSender().sendMessage("§bBenutze: §f/§cp§fing §a<Player>");
                } else if (args.length == 1) {
                    try {
                        Player t = Bukkit.getServer().getPlayer(args[0]);
                        //int ping = (((CraftPlayer) t).getHandle()).ping;
                        targetColor = Color.getPlayerColor(PermissionsEx.getUser(t), t);

                        //Bukkit.getConsoleSender().sendMessage(Main.pre + " " + playerColor + t.getName() + "§a's Ping: §e" + ping + "ms.");
                        Bukkit.getConsoleSender().sendMessage(Main.pre + " " + targetColor + t.getName() + "§a's Ping: §4ERROR§ems.");
                    } catch (NullPointerException e) {
                        Bukkit.getConsoleSender().sendMessage(Main.pre + " §cSpieler nicht gefunden!");
                    }
                } else
                    Bukkit.getConsoleSender().sendMessage("§bBenutze: §f/§cp§fing §a<Player>");
            }
        }
        return false;
    }
}
