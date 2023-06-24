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
import org.bukkit.event.Listener;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class AntiBuild implements Listener, CommandExecutor {

    String playerColor = null;
    String targetColor = null;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("build")) {
            if (sender instanceof Player p) {
                boolean permexo = PermissionsEx.getUser(p).inGroup("Owner");
                int onlinePlayers = Bukkit.getOnlinePlayers().size();

                playerColor = Color.getPlayerColor(PermissionsEx.getUser(p), p);

                if (!permexo && !PermissionsEx.getUser(p).inGroup("Vice") && !PermissionsEx.getUser(p).inGroup("Fellow")) {
                    sender.sendMessage(Main.noperm);
                } else if (args.length == 0) {
                    if (!p.getWorld().getName().equals("world") && !p.getWorld().getName().equals("world_nether") && !p.getWorld().getName().equals("world_the_end")) {
                        p.sendMessage(Main.pre + " §cDieser Command ist auf dieser Welt nicht gestattet!");
                        return true;
                    }

                    if (Main.allowedPlayer.contains(p)) {
                        Main.allowedPlayer.remove(p);
                        p.sendMessage(Main.pre + " §eBaumodus §cdeaktiviert§e!");
                        Bukkit.getConsoleSender().sendMessage(playerColor + p.getPlayer().getName() + " §ehat den Baumodus §cdeaktiviert§e!");
                    } else if (onlinePlayers >= 1) {
                        Main.allowedPlayer.add(p);
                        p.sendMessage(Main.pre + " §eBaumodus §aaktiviert§e!");
                        Bukkit.getConsoleSender().sendMessage(playerColor + p.getPlayer().getName() + " §ehat den Baumodus §aaktiviert§e!");
                    } else {
                        p.sendMessage(Main.pre + " §4Nicht genug Spieler!");
                    }
                } else if (args.length == 1) {
                    if (permexo)
                        p.sendMessage("§7| §4Only for the §bConsoleCommandSender");
                    else
                        p.sendMessage("§bBenutze: §f/§cb§fuild");
                } else
                    p.sendMessage("§bBenutze: §f/§cb§fuild");

            } else if (sender instanceof ConsoleCommandSender) {
                if (args.length == 0) {
                    sender.sendMessage("§bBenutze: §f/§cb§fuild §a<Player>");
                } else if (args.length == 1) {

                    try {
                        Player t = Bukkit.getServer().getPlayer(args[0]);
                        targetColor = Color.getPlayerColor(PermissionsEx.getUser(t), t);

                        if (PermissionsEx.getUser(t).inGroup("Owner") || PermissionsEx.getUser(t).inGroup("Vice") || PermissionsEx.getUser(t).inGroup("Fellow")) {
                            if (Main.allowedPlayer.contains(t)) {
                                Main.allowedPlayer.remove(t);
                                t.sendMessage(Main.pre + " §eDein Baumodus wurde §cdeaktiviert§e!");
                                sender.sendMessage("§eDu hast den Baumodus von " + targetColor + t.getName() + " §cdeaktiviert§e!");
                            } else {
                                Main.allowedPlayer.add(t);
                                t.sendMessage(Main.pre + " §eDein Baumodus wurde §aaktiviert§e!");
                                sender.sendMessage("§eDu hast den Baumodus von " + targetColor + t.getName() + " §aaktiviert§e!");
                            }
                        } else
                            sender.sendMessage("§4Dieser Spieler darf nicht in den Baumodus gesetzt werden!");
                    } catch (NullPointerException e) {
                        sender.sendMessage(Main.pre + " §cSpieler nicht gefunden!");
                    }
                } else
                    sender.sendMessage("§bBenutze: §f/§cb§fuild §a<Player>");
            }
        }
        return false;
    }
}