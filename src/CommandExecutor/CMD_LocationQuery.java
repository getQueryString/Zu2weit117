// Copyright© by Fin

package CommandExecutor;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CMD_LocationQuery implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("loc")) {
                if (PermissionsEx.getUser(p).inGroup("Owner") || PermissionsEx.getUser(p).inGroup("Vice")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("world")) {
                            p.sendMessage("§bBenutze: §f/loc §aworld §c<meta, list>");
                        } else if (args[0].equalsIgnoreCase("nether")) {
                            p.sendMessage("§bBenutze: §f/loc §anether §c<meta, list>");
                        } else if (args[0].equalsIgnoreCase("end")) {
                            p.sendMessage("§bBenutze: §f/loc §aend §c<meta, list>");
                        } else {
                            p.sendMessage("§cWelt nicht gefunden");
                        }
                    } else if (args.length == 0 || args.length >= 3) {
                        p.sendMessage("§bBenutze: §f/loc §a<world, nether, end>, <meta, list>");
                    }

                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("world") && args[1].equalsIgnoreCase("ohome")) {
                            p.sendMessage("§7[§bAltes Zuhause§7]\n§aworld§7» §fX:§7-1015 §fY:§764 §fZ:§7-387");
                        } else if (args[0].equalsIgnoreCase("world") && args[1].equalsIgnoreCase("nhome")) {
                            p.sendMessage("§7[§bNeues Zuhause§7]\n§aworld§7» §fX:§7-3244 §fY:§768 §fZ:§7-3394");
                        } else if (args[0].equalsIgnoreCase("world") && args[1].equalsIgnoreCase("oend")) {
                            p.sendMessage("§7[§5Endportal§7]\n§aworld§7» §fX:§7-1036 §fY:§763 §fZ:§7-1069");
                        } else if (args[0].equalsIgnoreCase("world") && args[1].equalsIgnoreCase("nend")) {
                            p.sendMessage("§7[§5Endportal§7]\n§aworld§7» §fX:§7-3221 §fY:§793 §fZ:§7-3404");
                        } else if (args[0].equalsIgnoreCase("world") && args[1].equalsIgnoreCase("list")) {
                            p.sendMessage("§7[§bListe von §aworld§7]");
                            p.sendMessage("§f» §aohome, nhome, oend, nend");
                        } else if (args[0].equalsIgnoreCase("nether") && args[1].equalsIgnoreCase("portal")) {
                            p.sendMessage("§7[§5Netherportal§7]\n§5world_nether§7» §fX:§7-409 §fY:§775 §fZ:§7-416");
                        } else if (args[0].equalsIgnoreCase("nether") && args[1].equalsIgnoreCase("fortress")) {
                            p.sendMessage("§7[§5Netherfestung§7]\n§5world_nether§7» §fX:§7~-220 §fY:§7Y §fZ:§7~-700");
                        } else if (args[0].equalsIgnoreCase("nether") && args[1].equalsIgnoreCase("list")) {
                            p.sendMessage("§7[§bListe von §5nether§7]");
                            p.sendMessage("§f» §aportal, fortress");
                        } else if (args[0].equalsIgnoreCase("end") && args[1].equalsIgnoreCase("gateway")) {
                            p.sendMessage("§7[§5Endtransitportal§7]\n§5world_the_end§7» §fX:§71100 §fY:§761 §fZ:§71");
                        } /*else if (args[0].equalsIgnoreCase("end") && args[1].equalsIgnoreCase("empty")) {
                            p.sendMessage("§7[§5Empty§7]\n§5world_the_end§7» §fX:§7X §fY:§7Y §fZ:§7Z");
                        }*/ else if (args[0].equalsIgnoreCase("end") && args[1].equalsIgnoreCase("list")) {
                            p.sendMessage("§7[§bListe von §5end§7]");
                            p.sendMessage("§f» §agateway");
                        } else {
                            p.sendMessage("§cWelt oder Meta nicht gefunden");
                        }
                    }
                } else {
                    sender.sendMessage(Main.noperm);
                }
            }
        } else if (cmd.getName().equalsIgnoreCase("loc")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("world")) {
                    Bukkit.getConsoleSender().sendMessage("§bBenutze: §f/loc §aworld §c<meta, list>");
                } else if (args[0].equalsIgnoreCase("nether")) {
                    Bukkit.getConsoleSender().sendMessage("§bBenutze: §f/loc §anether §c<meta, list>");
                } else if (args[0].equalsIgnoreCase("end")) {
                    Bukkit.getConsoleSender().sendMessage("§bBenutze: §f/loc §aend §c<meta, list>");
                } else {
                    Bukkit.getConsoleSender().sendMessage("§cWelt nicht gefunden");
                }
            } else if (args.length == 0) {
                Bukkit.getConsoleSender().sendMessage("§bBenutze: §f/loc §a<world, nether, end>, <meta, list>");
            } else if (args.length >= 3) {
                Bukkit.getConsoleSender().sendMessage("§bBenutze: §f/loc §a<world, nether, end>, <meta, list>");
            }
            if (args.length == 2)
                if (args[0].equalsIgnoreCase("world") && args[1].equalsIgnoreCase("ohome")) {
                    Bukkit.getConsoleSender().sendMessage("§7[§bAltes Zuhause§7]\n§aworld§7» §fX:§7-1015 §fY:§764 §fZ:§7-387");
                } else if (args[0].equalsIgnoreCase("world") && args[1].equalsIgnoreCase("nhome")) {
                    Bukkit.getConsoleSender().sendMessage("§7[§bNeues Zuhause§7]\n§aworld§7» §fX:§7-3244 §fY:§768 §fZ:§7-3394");
                } else if (args[0].equalsIgnoreCase("world") && args[1].equalsIgnoreCase("oend")) {
                    Bukkit.getConsoleSender().sendMessage("§7[§5Endportal§7]\n§aworld§7» §fX:§7-1036 §fY:§763 §fZ:§7-1069");
                } else if (args[0].equalsIgnoreCase("world") && args[1].equalsIgnoreCase("nend")) {
                    Bukkit.getConsoleSender().sendMessage("§7[§5Endportal§7]\n§aworld§7» §fX:§7-3221 §fY:§793 §fZ:§7-3404");
                } else if (args[0].equalsIgnoreCase("world") && args[1].equalsIgnoreCase("list")) {
                    Bukkit.getConsoleSender().sendMessage("§7[§bListe von §aworld§7]");
                    Bukkit.getConsoleSender().sendMessage("§f» §aohome, nhome, oend, nend");
                } else if (args[0].equalsIgnoreCase("nether") && args[1].equalsIgnoreCase("portal")) {
                    Bukkit.getConsoleSender().sendMessage("§7[§5Netherportal§7]\n§5world_nether§7» §fX:§7-409 §fY:§775 §fZ:§7-416");
                } else if (args[0].equalsIgnoreCase("nether") && args[1].equalsIgnoreCase("fortress")) {
                    Bukkit.getConsoleSender().sendMessage("§7[§5Netherfestung§7]\n§5world_nether§7» §fX:§7~-220 §fY:§7Y §fZ:§7~-700");
                } else if (args[0].equalsIgnoreCase("nether") && args[1].equalsIgnoreCase("list")) {
                    Bukkit.getConsoleSender().sendMessage("§7[§bListe von §5nether§7]");
                    Bukkit.getConsoleSender().sendMessage("§f» §aportal, fortress");
                } else if (args[0].equalsIgnoreCase("end") && args[1].equalsIgnoreCase("gateway")) {
                    Bukkit.getConsoleSender().sendMessage("§7[§5Endtransitportal§7]\n§5world_the_end§7» §fX:§71100 §fY:§761 §fZ:§71");
                } /*else if (args[0].equalsIgnoreCase("end") && args[1].equalsIgnoreCase("empty")) {
                    Bukkit.getConsoleSender().sendMessage("§7[§5Empty§7]\n§5world_the_end§7» §fX:§7X §fY:§7Y §fZ:§7Z");
                }*/ else if (args[0].equalsIgnoreCase("end") && args[1].equalsIgnoreCase("list")) {
                    Bukkit.getConsoleSender().sendMessage("§7[§bListe von §5end§7]");
                    Bukkit.getConsoleSender().sendMessage("§f» §agateway");
                } else {
                    Bukkit.getConsoleSender().sendMessage("§cWelt oder Meta nicht gefunden");
                }
        }
        return false;
    }
}