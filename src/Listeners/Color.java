// Copyright© by Fin

package Listeners;

import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;

public class Color {

    public String getColorFormat(String output) {
        String f1 = output.replace("&0", "§0");
        String f2 = f1.replace("&1", "§1");
        String f3 = f2.replace("&2", "§2");
        String f4 = f3.replace("&3", "§3");
        String f5 = f4.replace("&4", "§4");
        String f6 = f5.replace("&5", "§5");
        String f7 = f6.replace("&6", "§6");
        String f8 = f7.replace("&7", "§7");
        String f9 = f8.replace("&8", "§8");
        String f10 = f9.replace("&9", "§9");
        String f11 = f10.replace("&a", "§a");
        String f12 = f11.replace("&b", "§b");
        String f13 = f12.replace("&c", "§c");
        String f14 = f13.replace("&d", "§d");
        String f15 = f14.replace("&e", "§e");
        String f16 = f15.replace("&f", "§f");
        String f17 = f16.replace("&k", "§k");
        String f18 = f17.replace("&l", "§l");
        String f19 = f18.replace("&m", "§m");
        String f20 = f19.replace("&n", "§n");
        String f21 = f20.replace("&o", "§o");
        return f21.replace("&r", "§r");
    }

    public static String getPlayerColor(PermissionUser user, Player p) {
        if (p != null && p.isOp()) return "§f";
        else if (user.inGroup("Owner")) return "§4";
        else if (user.inGroup("Vice")) return "§c";
        else if (user.inGroup("Fellow")) return "§5";
        else if (user.inGroup("default")) return "§8";
        return null;
    }
}