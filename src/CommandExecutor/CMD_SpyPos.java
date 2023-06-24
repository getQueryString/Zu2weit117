// Copyright© by Fin

package CommandExecutor;

import Listeners.Color;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CMD_SpyPos implements CommandExecutor {

    String targetColor = null;
    String targetWorld = null;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spypos")) {
            if (sender instanceof Player p) {
                if (PermissionsEx.getUser(p).inGroup("Owner") || PermissionsEx.getUser(p).inGroup("Vice")) {

                    if (args.length == 0) {
                        p.sendMessage("§bBenutze: §f/§cspypos §a<Player>");
                    } else if (args.length == 1) {
                        try {
                            Player t = Bukkit.getServer().getPlayer(args[0]);
                            if (p == t) {
                                p.sendMessage("§bBenutze: §f/§cspypos §a<Player>");
                                return false;
                            }
                            targetColor = Color.getPlayerColor(PermissionsEx.getUser(t), t);

                            if (t.getWorld().getName().equalsIgnoreCase("world")) targetWorld = "§a";
                            else if (t.getWorld().getName().equalsIgnoreCase("world_nether") || t.getWorld().getName().equalsIgnoreCase("world_the_end"))
                                targetWorld = "§5";

                            double coordX = t.getLocation().getX();
                            double coordY = t.getLocation().getY();
                            double coordZ = t.getLocation().getZ();
                            double X = Math.round(coordX * 100.0D) / 100.0D;
                            double Y = Math.round(coordY * 100.0D) / 100.0D;
                            double Z = Math.round(coordZ * 100.0D) / 100.0D;
                            p.sendMessage("§7[" + targetColor + t.getName() + "§7]\n" + targetWorld + t.getWorld().getName()
                                    + "§7» §fX:§7" + X + " §fY:§7" + Y + " §fZ:§7" + Z);
                        } catch (NullPointerException e) {
                            p.sendMessage(Main.pre + " §cSpieler nicht gefunden!");
                        }
                    }
                } else {
                    p.sendMessage(Main.noperm);
                }
            } else if (sender instanceof ConsoleCommandSender) {
                if (args.length == 0) {
                    Bukkit.getConsoleSender().sendMessage("§bBenutze: §f/§cspypos §a<Player>");
                } else if (args.length == 1) {
                    try {
                        Player t = Bukkit.getServer().getPlayer(args[0]);
                        targetColor = Color.getPlayerColor(PermissionsEx.getUser(t), t);

                        if (t.getWorld().getName().equalsIgnoreCase("world")) targetWorld = "§a";
                        else if (t.getWorld().getName().equalsIgnoreCase("world_nether") || t.getWorld().getName().equalsIgnoreCase("world_the_end"))
                            targetWorld = "§5";

                        double coordX = t.getLocation().getX();
                        double coordY = t.getLocation().getY();
                        double coordZ = t.getLocation().getZ();
                        double X = Math.round(coordX * 100.0D) / 100.0D;
                        double Y = Math.round(coordY * 100.0D) / 100.0D;
                        double Z = Math.round(coordZ * 100.0D) / 100.0D;
                        Bukkit.getConsoleSender().sendMessage("§7[" + targetColor + t.getName() + "§7]\n" + targetWorld + t.getWorld().getName()
                                + "§7» §fX:§7" + X + " §fY:§7" + Y + " §fZ:§7" + Z);
                    } catch (NullPointerException e) {
                        Bukkit.getConsoleSender().sendMessage(Main.pre + " §cSpieler nicht gefunden!");
                    }
                }
            }
        }
        return false;
    }
}