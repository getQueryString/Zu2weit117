// Copyright© by Fin

package Listeners;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.Collection;
import java.util.Objects;

public class PlayerConnectionListener implements Listener {

    String color = null;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        color = Color.getPlayerColor(PermissionsEx.getUser(p), p);
        p.sendMessage("");
        e.setJoinMessage(Main.join + " " + color + p.getName() + " §6ist dem Spiel beigetreten.");

        PermissionUser permex = PermissionsEx.getUser(p);
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        new EpicScoreboard(p).sendToPlayer(p);
        if (permex.inGroup("Owner")) {
            for (Player pl : onlinePlayers)
                Objects.requireNonNull(Main.scoreboard.get(pl.getUniqueId()).sb.getTeam("002owner")).addEntry(p.getName());
        } else if (p.isOp()) {
            for (Player pl : onlinePlayers)
                Objects.requireNonNull(Main.scoreboard.get(pl.getUniqueId()).sb.getTeam("001op")).addEntry(p.getName());
        } else if (permex.inGroup("Vice")) {
            for (Player pl : onlinePlayers)
                Objects.requireNonNull(Main.scoreboard.get(pl.getUniqueId()).sb.getTeam("003vice")).addEntry(p.getName());
        } else if (permex.inGroup("Fellow")) {
            for (Player pl : onlinePlayers)
                Objects.requireNonNull(Main.scoreboard.get(pl.getUniqueId()).sb.getTeam("004fellow")).addEntry(p.getName());
        } else {
            for (Player pl : onlinePlayers)
                Objects.requireNonNull(Main.scoreboard.get(pl.getUniqueId()).sb.getTeam("005spieler")).addEntry(p.getName());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        color = Color.getPlayerColor(PermissionsEx.getUser(p), p);
        e.setQuitMessage(Main.leave + " " + color + p.getName() + " §6hat das Spiel verlassen.");
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        color = Color.getPlayerColor(PermissionsEx.getUser(p), p);
        Bukkit.getConsoleSender().sendMessage(Main.kick + " " + color + p.getName() + " §cwurde aus dem Spiel geworfen.");
        e.setLeaveMessage(Main.kick + " " + color + p.getName() + " §cwurde aus dem Spiel geworfen.");
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if (e.getPlayer().isBanned())
            e.setKickMessage(Main.title + "§4§lDu wurdest permanent vom Server verbannt.");
        else if (e.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST)
            e.setKickMessage(Main.title + "§4§lDu bist nicht in der Whitelist.");
    }
}