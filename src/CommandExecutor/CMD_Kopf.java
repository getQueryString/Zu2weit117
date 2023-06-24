// Copyright© by Fin

package CommandExecutor;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CMD_Kopf implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player p) {
            if (PermissionsEx.getUser(p).inGroup("Owner") || PermissionsEx.getUser(p).inGroup("Vice")) {
                if (args.length >= 1)
                    p.sendMessage("§bBenutze: §f/kopf§7, §f/head§7, §f/skull");

                ItemStack is = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta im = (SkullMeta) is.getItemMeta();
                im.setOwningPlayer(p.getPlayer());
                im.setDisplayName("§c§n" + p.getName());
                is.setItemMeta(im);
                p.getInventory().addItem(is);
                p.updateInventory();

            } else
                p.sendMessage(Main.noperm);
        } else
            Bukkit.getConsoleSender().sendMessage(Main.iplayer);
        return false;
    }
}