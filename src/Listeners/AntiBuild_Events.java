// Copyright© by Fin

package Listeners;

import Main.Main;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class AntiBuild_Events implements Listener {

    /*@EventHandler
    public void onEvent(Event e) {
        Player p = getPlayerFromEvent(e);
        if (p != null && !Main.allowedPlayer.contains(p)) {
            if (e instanceof Cancellable cancellableEvent) {
                cancellableEvent.setCancelled(true);
            }
        }
    }

    private Player getPlayerFromEvent(Event event) {
        if (event instanceof PlayerEvent) {
            return ((PlayerEvent) event).getPlayer();
        } else if (event instanceof PlayerShearEntityEvent) {
            return ((PlayerShearEntityEvent) event).getPlayer();
        } else if (event instanceof BlockIgniteEvent) {
            return ((BlockIgniteEvent) event).getPlayer();
        } else if (event instanceof PlayerBucketEmptyEvent) {
            return ((PlayerBucketEmptyEvent) event).getPlayer();
        } else if (event instanceof PlayerBucketFillEvent) {
            return ((PlayerBucketFillEvent) event).getPlayer();
        } else if (event instanceof PlayerEditBookEvent) {
            return ((PlayerEditBookEvent) event).getPlayer();
        } else if (event instanceof BlockBreakEvent) {
            return ((BlockBreakEvent) event).getPlayer();
        } else if (event instanceof BlockPlaceEvent) {
            return ((BlockPlaceEvent) event).getPlayer();
        } else if (event instanceof PlayerDropItemEvent) {
            return ((PlayerDropItemEvent) event).getPlayer();
        } else if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) event;
            if (damageEvent.getEntity() instanceof Player) {
                return (Player) damageEvent.getEntity();
            } else if (damageEvent.getDamager() instanceof Player) {
                return (Player) damageEvent.getDamager();
            } else if (damageEvent.getDamager() instanceof Projectile) {
                Projectile projectile = (Projectile) damageEvent.getDamager();
                if (projectile.getShooter() instanceof Player) {
                    return (Player) projectile.getShooter();
                }
            }
        } else if (event instanceof PlayerInteractEntityEvent) {
            return ((PlayerInteractEntityEvent) event).getPlayer();
        } else if (event instanceof PlayerInteractAtEntityEvent) {
            return ((PlayerInteractAtEntityEvent) event).getPlayer();
        } else if (event instanceof PlayerInteractEvent) {
            return ((PlayerInteractEvent) event).getPlayer();
        } else if (event instanceof VehicleDestroyEvent) {
            VehicleDestroyEvent destroyEvent = (VehicleDestroyEvent) event;
            if (destroyEvent.getVehicle() instanceof Boat || destroyEvent.getVehicle() instanceof Minecart) {
                Entity attacker = destroyEvent.getAttacker();
                if (attacker instanceof Player) {
                    return (Player) attacker;
                }
            }
        } else if (event instanceof PlayerShearEntityEvent) {
            return ((PlayerShearEntityEvent) event).getPlayer();
        } else if (event instanceof HangingBreakByEntityEvent) {
            HangingBreakByEntityEvent breakEvent = (HangingBreakByEntityEvent) event;
            Entity remover = breakEvent.getRemover();
            if (remover instanceof Player) {
                return (Player) remover;
            } else if (remover instanceof Arrow) {
                Arrow arrow = (Arrow) remover;
                if (arrow.getShooter() instanceof Player) {
                    return (Player) arrow.getShooter();
                }
            } else if (remover instanceof Trident) {
                Trident trident = (Trident) remover;
                if (trident.getShooter() instanceof Player) {
                    return (Player) trident.getShooter();
                }
            }
        } else if (event instanceof HangingPlaceEvent) {
            return ((HangingPlaceEvent) event).getPlayer();
        }

        return null;
    }*/

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
        if (!Main.allowedPlayer.contains(p))
            e.setCancelled(true);
    }

    // PlayerPickupItem
    @Deprecated
    @EventHandler
    public void onPlayerPickUpItem(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        if (PermissionsEx.getUser(p).inGroup("default"))
            e.setCancelled(true);
    }

    // EntityDamageByEntity
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (!Main.allowedPlayer.contains(e.getEntity()))
                e.setCancelled(true);
        } else if (e.getDamager() instanceof Player) {
            if (!Main.allowedPlayer.contains(e.getDamager()))
                e.setCancelled(true);
        }
        if (e.getDamager() instanceof Projectile) {
            if (e.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) e.getDamager();
                if (arrow.getShooter() instanceof Player) {
                    if (!Main.allowedPlayer.contains(arrow.getShooter()))
                        e.setCancelled(true);
                }
            }
        }
        if (e.getDamager() instanceof Trident) {
            Trident trident = (Trident) e.getDamager();
            if (trident.getShooter() instanceof Player) {
                if (!Main.allowedPlayer.contains(trident.getShooter()))
                    e.setCancelled(true);
            }
        }
    }

    // PlayerInteractEntity
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof Boat || e.getRightClicked() instanceof Minecart || e.getRightClicked() instanceof ItemFrame) {
            if (!Main.allowedPlayer.contains(e.getPlayer()))
                e.setCancelled(true);
        }
    }

    // PlayerInteractAtEntity
    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
        if (!Main.allowedPlayer.contains(e.getPlayer()))
            e.setCancelled(true);
    }

    // PlayerInteract
    @Deprecated
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Material clickedMaterial = e.getClickedBlock().getType();

            if ((isBlockInteractable(clickedMaterial) || isRestrictedItem(p.getItemInHand().getType()) || e.getAction() == Action.PHYSICAL) && !Main.allowedPlayer.contains(p))
                e.setCancelled(true);
        }
    }

    private boolean isBlockInteractable(Material material) {
        return switch (material) {
            case CHEST, DROPPER, DISPENSER, FURNACE, BEACON, TRAPPED_CHEST, ENDER_CHEST, ENCHANTING_TABLE, ANVIL, CHIPPED_ANVIL, DAMAGED_ANVIL, CRAFTING_TABLE, WHITE_BED, ORANGE_BED, MAGENTA_BED, LIGHT_BLUE_BED, YELLOW_BED, LIME_BED, PINK_BED, GRAY_BED, LIGHT_GRAY_BED, CYAN_BED, PURPLE_BED, BLUE_BED, BROWN_BED, GREEN_BED, RED_BED, BLACK_BED, SHULKER_BOX, WHITE_SHULKER_BOX, ORANGE_SHULKER_BOX, MAGENTA_SHULKER_BOX, LIGHT_BLUE_SHULKER_BOX, YELLOW_SHULKER_BOX, LIME_SHULKER_BOX, PINK_SHULKER_BOX, GRAY_SHULKER_BOX, LIGHT_GRAY_SHULKER_BOX, CYAN_SHULKER_BOX, PURPLE_SHULKER_BOX, BLUE_SHULKER_BOX, BROWN_SHULKER_BOX, GREEN_SHULKER_BOX, RED_SHULKER_BOX, BLACK_SHULKER_BOX, LOOM, STONECUTTER, BARREL, SMOKER, BLAST_FURNACE, CARTOGRAPHY_TABLE, FLETCHING_TABLE, GRINDSTONE, SMITHING_TABLE, REPEATER, COMPARATOR, LEVER, HOPPER, DAYLIGHT_DETECTOR, LECTERN, JUKEBOX, NOTE_BLOCK, BREWING_STAND, OAK_DOOR, SPRUCE_DOOR, BIRCH_DOOR, JUNGLE_DOOR, ACACIA_DOOR, DARK_OAK_DOOR, CRIMSON_DOOR, WARPED_DOOR, OAK_FENCE_GATE, SPRUCE_FENCE_GATE, BIRCH_FENCE_GATE, JUNGLE_FENCE_GATE, ACACIA_FENCE_GATE, DARK_OAK_FENCE_GATE, CRIMSON_FENCE_GATE, WARPED_FENCE_GATE, STONE_BUTTON, OAK_BUTTON, SPRUCE_BUTTON, BIRCH_BUTTON, JUNGLE_BUTTON, ACACIA_BUTTON, DARK_OAK_BUTTON, CRIMSON_BUTTON, WARPED_BUTTON, POLISHED_BLACKSTONE_BUTTON, OAK_TRAPDOOR, SPRUCE_TRAPDOOR, BIRCH_TRAPDOOR, JUNGLE_TRAPDOOR, ACACIA_TRAPDOOR, DARK_OAK_TRAPDOOR, CRIMSON_TRAPDOOR, WARPED_TRAPDOOR, COMPOSTER, CAKE, DRAGON_EGG, SPAWNER ->
                    true;
            default -> false;
        };
    }

    private boolean isRestrictedItem(Material material) {
        return material == Material.ACACIA_BOAT || material == Material.BIRCH_BOAT || material == Material.DARK_OAK_BOAT
                || material == Material.JUNGLE_BOAT || material == Material.OAK_BOAT || material == Material.SPRUCE_BOAT
                || material == Material.MINECART || material == Material.CHEST_MINECART || material == Material.FURNACE_MINECART
                || material == Material.HOPPER_MINECART || material == Material.TNT_MINECART || material == Material.ARMOR_STAND
                || material == Material.BONE_MEAL || material == Material.FLINT_AND_STEEL;
    }

    // VehicleBlockCollision
    @EventHandler
    public void onVehicleBlockCollision(VehicleDestroyEvent e) {
        if (e.getVehicle() instanceof Boat || e.getVehicle() instanceof Minecart) {
            if (!Main.allowedPlayer.contains(e.getAttacker()))
                e.setCancelled(true);
        }
    }

    // PlayerShearEntity
    @EventHandler
    public void onPlayerShearEntity(PlayerShearEntityEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p))
            e.setCancelled(true);
    }

    // HangingBreakByEntity
    @EventHandler
    public void onItemFrameBreak(HangingBreakByEntityEvent e) {
        if (e.getRemover() instanceof Player) {
            if (!Main.allowedPlayer.contains(e.getRemover()))
                e.setCancelled(true);
        }
        if (e.getRemover() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getRemover();
            if (arrow.getShooter() instanceof Player) {
                if (!Main.allowedPlayer.contains(arrow.getShooter()))
                    e.setCancelled(true);
            }
        } else if (e.getRemover() instanceof Trident) {
            Trident trident = (Trident) e.getRemover();
            if (trident.getShooter() instanceof Player) {
                if (!Main.allowedPlayer.contains(trident.getShooter()))
                    e.setCancelled(true);
            }
        }
    }

    // HangingPlace
    @EventHandler
    public void onEntityPlace(HangingPlaceEvent e) {
        if (e.getEntity() instanceof ItemFrame) {
            if (!Main.allowedPlayer.contains(e.getPlayer()))
                e.setCancelled(true);
        }
    }

    // PlayerPortal
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p))
            e.setCancelled(true);

    }

    // BlockIgnite
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p))
            e.setCancelled(true);

    }

    // PlayerBucketEmpty
    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p))
            e.setCancelled(true);

    }

    // PlayerBucketFill
    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p))
            e.setCancelled(true);

    }

    // PlayerEditBook
    @EventHandler
    public void onPlayerEditBook(PlayerEditBookEvent e) {
        Player p = e.getPlayer();
        if (!Main.allowedPlayer.contains(p))
            e.setCancelled(true);

    }

    // ProjectileHit
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            if (!Main.allowedPlayer.contains(e.getEntity().getShooter()))
                e.getEntity().setFireTicks(0);
        }
    }
}