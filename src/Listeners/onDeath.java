// Copyright© by Fin

package Listeners;

import Main.Main;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.projectiles.BlockProjectileSource;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class onDeath implements Listener {

    static String playerColor = null;
    static String killerColor = null;

    @EventHandler
    public static void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player k = e.getEntity().getKiller();
        playerColor = Color.getPlayerColor(PermissionsEx.getUser(p), p);

        if (playerColor != null) {
            switch (p.getLastDamageCause().getCause()) {
                case FALL -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §ffiel aus zu großer Höhe.");
                case LAVA -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fversuchte in Lava zu schwimmen.");
                case DROWNING -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fertrank.");
                case DRYOUT -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fist ausgetrocknet.");
                case CRAMMING -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fCRAMMING.");
                case CONTACT -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §CONTACT.");
                case CUSTOM -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fCUSTOM.");
                case DRAGON_BREATH -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fDRAGON_BREATH.");
                case ENTITY_ATTACK -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fENTITY_ATTACK.");
                case ENTITY_SWEEP_ATTACK -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fENTITY_SWEEP_ATTACK.");
                case FIRE -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fging in Flammen auf.");
                case FIRE_TICK -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fverbrannte.");
                case FLY_INTO_WALL -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fFLY_INTO_WALL.");
                case HOT_FLOOR -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fHOT_FLOOR.");
                case LIGHTNING -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fLIGHTNING.");
                case MAGIC -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fMAGIC.");
                case MELTING -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fMELTING.");
                case POISON -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fPOITSON.");
                case STARVATION -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fverhungerte.");
                case SUFFOCATION -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fwurde lebendig begraben.");
                case SUICIDE -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fsuizidierte sich.");
                case THORNS -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fTHORNS.");
                case VOID -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §ffiel aus der Welt.");
                case WITHER -> e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fWITHER.");
            }

            // Player deaths from mobs etc
            if (e.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent lastDamageCause = (EntityDamageByEntityEvent) e.getEntity().getLastDamageCause();
                Entity killer = lastDamageCause.getDamager();
                String getItem = killer.getName();
                if (!(killer instanceof Creeper) && !(killer instanceof Arrow) && !(killer instanceof Trident) && !(killer instanceof Bee) && !(killer instanceof TNTPrimed) && !(killer instanceof FallingBlock) && !(p == k) && k == null) {
                    e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fwurde von §e" + killer.getName() + " §ferschlagen.");
                } else if (killer instanceof Creeper) {
                    e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fwurde von §e" + killer.getName() + " §fin die Luft gesprengt.");
                } else if (killer instanceof Arrow) {
                    Arrow arrow = (Arrow) lastDamageCause.getDamager();
                    if (arrow.getShooter() instanceof BlockProjectileSource) {
                        e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fwurde von §e" + getItem + " §ferschossen.");
                    } else if (!(arrow.getShooter() instanceof BlockProjectileSource) && !(p == k) && k == null) {
                        String shooter = ((Entity) arrow.getShooter()).getName();
                        e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fwurde von §e" + shooter + " §ferschossen.");
                    } else if (p == k) {
                        e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fhat sich mit einem §e" + killer.getName() + " §ferschossen.");
                    } else if (k != null) {
                        if (playerColor != null && killerColor != null) {
                            e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fwurde von " + killerColor + k.getName() + " §fmit einem §e" + getItem + " §ferschossen.");
                        }
                    }
                } else if (killer instanceof Trident) {
                    Trident trident = (Trident) lastDamageCause.getDamager();
                    String shooter = ((Entity) trident.getShooter()).getName();
                    if (k instanceof Player) {
                        PermissionUser permexKiller = PermissionsEx.getUser(k);
                        if (permexKiller.inGroup("Owner")) killerColor = "§4§l";
                        else if (permexKiller.inGroup("Vice")) killerColor = "§c";
                        else if (permexKiller.inGroup("Fellow")) killerColor = "§5";
                        else if (permexKiller.inGroup("default")) killerColor = "§8";
                        if (playerColor != null) {
                            e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fwurde von " + killerColor + shooter + " §fmit einem §e" + killer.getName() + " §ferschossen.");
                        }
                    } else if (p == k) {
                        e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fhat sich mit einem §e" + killer.getName() + " §ferschossen.");
                    } else {
                        e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fwurde von §e" + shooter + " §fmit einem §e" + killer.getName() + " §ferschossen.");
                    }
                } else if (killer instanceof Bee) {
                    e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fwurde von §e" + killer.getName() + " §fzu Tode erstochen.");
                } else if (killer instanceof FallingBlock) {
                    e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fwurde durch §e" + killer.getName() + " §fzerquetscht.");
                } else if (p == k) {
                    e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fhat sich mit einem §e" + killer.getName() + " §ferschossen.");
                } else if (k != null) {
                    killerColor = Color.getPlayerColor(PermissionsEx.getUser(k), k);
                    e.setDeathMessage(Main.pre + " " + playerColor + p.getName() + " §fwurde von " + killerColor + k.getName() + " §fgetötet.");
                }
            }
        }
    }
}