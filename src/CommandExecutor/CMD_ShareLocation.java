// Copyright© by Fin

package CommandExecutor;

import Listeners.Color;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CMD_ShareLocation implements CommandExecutor {

    String playerColor = null;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player p) {
            if (cmd.getName().equalsIgnoreCase("sharelocation"))
                if (PermissionsEx.getUser(p).inGroup("Owner") || PermissionsEx.getUser(p).inGroup("Vice")
                        || PermissionsEx.getUser(p).inGroup("Fellow") || p.isOp()) {
                    double coordX = p.getLocation().getX();
                    double coordY = p.getLocation().getY();
                    double coordZ = p.getLocation().getZ();
                    double X = Math.round(coordX * 100.0D) / 100.0D;
                    double Y = Math.round(coordY * 100.0D) / 100.0D;
                    double Z = Math.round(coordZ * 100.0D) / 100.0D;

                    playerColor = Color.getPlayerColor(PermissionsEx.getUser(p), p);

                    if (p.getWorld().getName().equalsIgnoreCase("world")) {
                        Bukkit.broadcastMessage("§7[" + playerColor + p.getName() + "§7]\n§a " + p.getWorld().getName()
                                + "§7» §fX:§7" + X + " §fY:§7" + Y + " §fZ:§7" + Z);
                    } else if (p.getWorld().getName().equalsIgnoreCase("world_nether")) {
                        Bukkit.broadcastMessage("§7[" + playerColor + p.getName() + "§7]\n§5" + p.getWorld().getName()
                                + "§7» §fX:§7" + X + " §fY:§7" + Y + " §fZ:§7" + Z);
                    } else if (p.getWorld().getName().equalsIgnoreCase("world_the_end")) {
                        Bukkit.broadcastMessage("§7[" + playerColor + p.getName() + "§7]\n§5" + p.getWorld().getName()
                                + "§7» §fX:§7" + X + " §fY:§7" + Y + " §fZ:§7" + Z);
                    }
                } else {
                    p.sendMessage(Main.noperm);
                }
        } else
            Bukkit.getConsoleSender().sendMessage(Main.iplayer);
        return false;
    }
}