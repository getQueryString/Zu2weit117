// Copyright© by Fin

package Listeners;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class AntiBuild_Events implements Listener {

    Plugin context;
    BukkitRunnable timer = null;

    // BlockBreak
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        boolean permexo = PermissionsEx.getUser(p).inGroup("Owner");
        boolean permexv = PermissionsEx.getUser(p).inGroup("Vice");
        if (!Main.allowedPlayer.contains(p)) {
            e.setCancelled(true);
            if (permexo || permexv) {
                p.sendMessage(Main.pre + " §cProtected!");
            }
        }
    }

    // BlockPlace
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        boolean permexo = PermissionsEx.getUser(p).inGroup("Owner");
        boolean permexv = PermissionsEx.getUser(p).inGroup("Vice");
        if (!Main.allowedPlayer.contains(p)) {
            e.setCancelled(true);
            if (permexo || permexv) {
                p.sendMessage(Main.pre + " §cProtected!");
            }
        }
    }

    // PlayerDropItem
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p)) {
            e.setCancelled(true);
        }
    }

    // EntityDamageByEntity
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (!Main.allowedPlayer.contains(e.getEntity())) {
                e.setCancelled(true);
            }
        } else if (e.getDamager() instanceof Player) {
            if (!Main.allowedPlayer.contains(e.getDamager())) {
                e.setCancelled(true);
            }
        }
        if (e.getDamager() instanceof Projectile) {
            if (e.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) e.getDamager();
                if (arrow.getShooter() instanceof Player) {
                    if (!Main.allowedPlayer.contains(arrow.getShooter())) {
                        e.setCancelled(true);
                    }
                }
            }
        }
        if (e.getDamager() instanceof Trident) {
            Trident trident = (Trident) e.getDamager();
            if (trident.getShooter() instanceof Player) {
                if (!Main.allowedPlayer.contains(trident.getShooter())) {
                    e.setCancelled(true);
                }
            }
        }
    }

    // PlayerInteractEntity
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof Boat || e.getRightClicked() instanceof Minecart || e.getRightClicked() instanceof ItemFrame) {
            if (!Main.allowedPlayer.contains(e.getPlayer())) {
                e.setCancelled(true);
            }
        }
    }

    // PlayerInteractAtEntity
    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
        if (!Main.allowedPlayer.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    // PlayerInteract
    @Deprecated
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.PHYSICAL) {
            if (!Main.allowedPlayer.contains(p)) {
                e.setCancelled(true);
            }
        }
        if (!Main.allowedPlayer.contains(p) && e.getAction() == Action.RIGHT_CLICK_BLOCK && (p.getItemInHand().getType() == Material.ACACIA_BOAT
                || p.getItemInHand().getType() == Material.BIRCH_BOAT || p.getItemInHand().getType() == Material.DARK_OAK_BOAT
                || p.getItemInHand().getType() == Material.JUNGLE_BOAT || p.getItemInHand().getType() == Material.OAK_BOAT
                || p.getItemInHand().getType() == Material.SPRUCE_BOAT || p.getItemInHand().getType() == Material.MINECART
                || p.getItemInHand().getType() == Material.CHEST_MINECART || p.getItemInHand().getType() == Material.FURNACE_MINECART
                || p.getItemInHand().getType() == Material.HOPPER_MINECART || p.getItemInHand().getType() == Material.TNT_MINECART
                || p.getItemInHand().getType() == Material.ARMOR_STAND || p.getItemInHand().getType() == Material.BONE_MEAL
                || p.getItemInHand().getType() == Material.FLINT_AND_STEEL)) {
            e.setCancelled(true);
        }
        if (!Main.allowedPlayer.contains(p)
                && e.getAction() == Action.RIGHT_CLICK_BLOCK
                && (e.getClickedBlock().getType() == Material.CHEST || e.getClickedBlock().getType() == Material.DROPPER
                || e.getClickedBlock().getType() == Material.DISPENSER
                || e.getClickedBlock().getType() == Material.FURNACE
                || e.getClickedBlock().getType() == Material.BEACON
                || e.getClickedBlock().getType() == Material.TRAPPED_CHEST
                || e.getClickedBlock().getType() == Material.ENDER_CHEST
                || e.getClickedBlock().getType() == Material.ENCHANTING_TABLE
                || e.getClickedBlock().getType() == Material.ANVIL
                || e.getClickedBlock().getType() == Material.CHIPPED_ANVIL
                || e.getClickedBlock().getType() == Material.DAMAGED_ANVIL
                || e.getClickedBlock().getType() == Material.CRAFTING_TABLE
                || e.getClickedBlock().getType() == Material.WHITE_BED
                || e.getClickedBlock().getType() == Material.ORANGE_BED
                || e.getClickedBlock().getType() == Material.MAGENTA_BED
                || e.getClickedBlock().getType() == Material.LIGHT_BLUE_BED
                || e.getClickedBlock().getType() == Material.YELLOW_BED
                || e.getClickedBlock().getType() == Material.LIME_BED
                || e.getClickedBlock().getType() == Material.PINK_BED
                || e.getClickedBlock().getType() == Material.GRAY_BED
                || e.getClickedBlock().getType() == Material.LIGHT_GRAY_BED
                || e.getClickedBlock().getType() == Material.CYAN_BED
                || e.getClickedBlock().getType() == Material.PURPLE_BED
                || e.getClickedBlock().getType() == Material.BLUE_BED
                || e.getClickedBlock().getType() == Material.BROWN_BED
                || e.getClickedBlock().getType() == Material.GREEN_BED
                || e.getClickedBlock().getType() == Material.RED_BED
                || e.getClickedBlock().getType() == Material.BLACK_BED
                || e.getClickedBlock().getType() == Material.SHULKER_BOX
                || e.getClickedBlock().getType() == Material.WHITE_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.ORANGE_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.MAGENTA_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.LIGHT_BLUE_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.YELLOW_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.LIME_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.PINK_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.GRAY_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.LIGHT_GRAY_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.CYAN_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.PURPLE_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.BLUE_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.BROWN_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.GREEN_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.RED_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.BLACK_SHULKER_BOX
                || e.getClickedBlock().getType() == Material.LOOM
                || e.getClickedBlock().getType() == Material.STONECUTTER
                || e.getClickedBlock().getType() == Material.BARREL
                || e.getClickedBlock().getType() == Material.SMOKER
                || e.getClickedBlock().getType() == Material.BLAST_FURNACE
                || e.getClickedBlock().getType() == Material.CARTOGRAPHY_TABLE
                || e.getClickedBlock().getType() == Material.FLETCHING_TABLE
                || e.getClickedBlock().getType() == Material.GRINDSTONE
                || e.getClickedBlock().getType() == Material.SMITHING_TABLE
                || e.getClickedBlock().getType() == Material.REPEATER
                || e.getClickedBlock().getType() == Material.COMPARATOR
                || e.getClickedBlock().getType() == Material.LEVER
                || e.getClickedBlock().getType() == Material.HOPPER
                || e.getClickedBlock().getType() == Material.DAYLIGHT_DETECTOR
                || e.getClickedBlock().getType() == Material.LECTERN
                || e.getClickedBlock().getType() == Material.JUKEBOX
                || e.getClickedBlock().getType() == Material.NOTE_BLOCK
                || e.getClickedBlock().getType() == Material.BREWING_STAND
                || e.getClickedBlock().getType() == Material.OAK_DOOR
                || e.getClickedBlock().getType() == Material.SPRUCE_DOOR
                || e.getClickedBlock().getType() == Material.BIRCH_DOOR
                || e.getClickedBlock().getType() == Material.JUNGLE_DOOR
                || e.getClickedBlock().getType() == Material.ACACIA_DOOR
                || e.getClickedBlock().getType() == Material.DARK_OAK_DOOR
                || e.getClickedBlock().getType() == Material.CRIMSON_DOOR
                || e.getClickedBlock().getType() == Material.WARPED_DOOR
                || e.getClickedBlock().getType() == Material.OAK_FENCE_GATE
                || e.getClickedBlock().getType() == Material.SPRUCE_FENCE_GATE
                || e.getClickedBlock().getType() == Material.BIRCH_FENCE_GATE
                || e.getClickedBlock().getType() == Material.JUNGLE_FENCE_GATE
                || e.getClickedBlock().getType() == Material.ACACIA_FENCE_GATE
                || e.getClickedBlock().getType() == Material.DARK_OAK_FENCE_GATE
                || e.getClickedBlock().getType() == Material.CRIMSON_FENCE_GATE
                || e.getClickedBlock().getType() == Material.WARPED_FENCE_GATE
                || e.getClickedBlock().getType() == Material.STONE_BUTTON
                || e.getClickedBlock().getType() == Material.OAK_BUTTON
                || e.getClickedBlock().getType() == Material.SPRUCE_BUTTON
                || e.getClickedBlock().getType() == Material.BIRCH_BUTTON
                || e.getClickedBlock().getType() == Material.JUNGLE_BUTTON
                || e.getClickedBlock().getType() == Material.ACACIA_BUTTON
                || e.getClickedBlock().getType() == Material.DARK_OAK_BUTTON
                || e.getClickedBlock().getType() == Material.CRIMSON_BUTTON
                || e.getClickedBlock().getType() == Material.WARPED_BUTTON
                || e.getClickedBlock().getType() == Material.POLISHED_BLACKSTONE_BUTTON
                || e.getClickedBlock().getType() == Material.OAK_TRAPDOOR
                || e.getClickedBlock().getType() == Material.SPRUCE_TRAPDOOR
                || e.getClickedBlock().getType() == Material.BIRCH_TRAPDOOR
                || e.getClickedBlock().getType() == Material.JUNGLE_TRAPDOOR
                || e.getClickedBlock().getType() == Material.ACACIA_TRAPDOOR
                || e.getClickedBlock().getType() == Material.DARK_OAK_TRAPDOOR
                || e.getClickedBlock().getType() == Material.CRIMSON_TRAPDOOR
                || e.getClickedBlock().getType() == Material.WARPED_TRAPDOOR
                || e.getClickedBlock().getType() == Material.COMPOSTER
                || e.getClickedBlock().getType() == Material.CAKE
                || e.getClickedBlock().getType() == Material.DRAGON_EGG
                || e.getClickedBlock().getType() == Material.SPAWNER)) {
            e.setCancelled(true);
        }
    }

    // VehicleBlockCollision
    @EventHandler
    public void onVehicleBlockCollision(VehicleDestroyEvent e) {
        if (e.getVehicle() instanceof Boat || e.getVehicle() instanceof Minecart) {
            if (!Main.allowedPlayer.contains(e.getAttacker())) {
                e.setCancelled(true);
            }
        }
    }

    // PlayerShearEntity
    @EventHandler
    public void onPlayerShearEntity(PlayerShearEntityEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p)) {
            e.setCancelled(true);
        }
    }

    // HangingBreakByEntity
    @EventHandler
    public void onItemFrameBreak(HangingBreakByEntityEvent e) {
        if (e.getRemover() instanceof Player) {
            if (!Main.allowedPlayer.contains(e.getRemover())) {
                e.setCancelled(true);
            }
        }
        if (e.getRemover() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getRemover();
            if (arrow.getShooter() instanceof Player) {
                if (!Main.allowedPlayer.contains(arrow.getShooter())) {
                    e.setCancelled(true);
                }
            }
        } else if (e.getRemover() instanceof Trident) {
            Trident trident = (Trident) e.getRemover();
            if (trident.getShooter() instanceof Player) {
                if (!Main.allowedPlayer.contains(trident.getShooter())) {
                    e.setCancelled(true);
                }
            }
        }
    }

    // HangingPlace
    @EventHandler
    public void onEntityPlace(HangingPlaceEvent e) {
        if (e.getEntity() instanceof ItemFrame) {
            if (!Main.allowedPlayer.contains(e.getPlayer())) {
                e.setCancelled(true);
            }
        }
    }

    // PlayerBedEntry
    /*@EventHandler
    public void onPlayerBedEntry(PlayerBedEnterEvent e) {
        Player p = e.getPlayer();
        boolean permexo = PermissionsEx.getUser(p).inGroup("Owner");
        boolean permexv = PermissionsEx.getUser(p).inGroup("Vice");
        boolean permexf = PermissionsEx.getUser(p).inGroup("Fellow");
        long time = Bukkit.getServer().getWorld("world").getTime();
        boolean storm = Bukkit.getServer().getWorld("world").hasStorm();
        for (Entity entity : p.getNearbyEntities(8, 5, 8)) {
            if (!(entity instanceof Creature)) {
                if (time >= 12542 && time <= 23459 || storm) {
                    if (permexo) {
                        Bukkit.broadcastMessage("§4§l" + p.getName() + " §fist schlafen gegangen.");
                        break;
                    } else if (permexv) {
                        Bukkit.broadcastMessage("§c" + p.getName() + " §fist schlafen gegangen.");
                        break;
                    } else if (permexf) {
                        Bukkit.broadcastMessage("§5" + p.getName() + " §fist schlafen gegangen.");
                        break;
                    }
                }
            } else {
                Bukkit.broadcastMessage("§c" + entity + " §6ist in der Nähe");
                e.setCancelled(true);
            }
        }
    }

    // PlayerBedLeave
    @EventHandler
    public void onPlayerBedLeave(PlayerBedLeaveEvent e) {
        Player p = e.getPlayer();
        boolean permexo = PermissionsEx.getUser(p).inGroup("Owner");
        boolean permexv = PermissionsEx.getUser(p).inGroup("Vice");
        boolean permexf = PermissionsEx.getUser(p).inGroup("Fellow");
        long time = Bukkit.getServer().getWorld("world").getTime();
        boolean storm = Bukkit.getServer().getWorld("world").hasStorm();
        if (time >= 12542 && time <= 23459 || storm) {
            if (permexo) {
                Bukkit.broadcastMessage("§4§l" + p.getName() + " §fist wieder aufgestanden.");
            } else if (permexv) {
                Bukkit.broadcastMessage("§c" + p.getName() + " §fist wieder aufgestanden.");
            } else if (permexf) {
                Bukkit.broadcastMessage("§5" + p.getName() + " §fist wieder aufgestanden.");
            }
        }
    }*/

    // PlayerPortal
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p)) {
            e.setCancelled(true);
        }
    }

    // BlockIgnite
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p)) {
            e.setCancelled(true);
        }
    }

    // PlayerBucketEmpty
    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p)) {
            e.setCancelled(true);
        }
    }

    // PlayerBucketFill
    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p)) {
            e.setCancelled(true);
        }
    }

    // PlayerEditBook
    @EventHandler
    public void onPlayerEditBook(PlayerEditBookEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p)) {
            e.setCancelled(true);
        }
    }

    // ProjectileHit
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            if (!Main.allowedPlayer.contains(e.getEntity().getShooter())) {
                e.getEntity().setFireTicks(0);
            }
        }
    }

    /*@EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Material m = e.getPlayer().getLocation().getBlock().getType();
        //if (!Main.allowedPlayer.contains(p)) {
            this.timer = new BukkitRunnable() {
                @Override
                public void run() {

                    if (m == Material.LEGACY_STATIONARY_WATER || m == Material.WATER && p.isInWater()) {
                        p.sendMessage("§3Swimming in water");
                    } else if (m == Material.LEGACY_STATIONARY_LAVA || m == Material.LAVA) {
                        p.sendMessage("§4Swimming in lava");
                    }
                }
            };
        timer.runTaskLater(context, 20);
        //}
}*/
}