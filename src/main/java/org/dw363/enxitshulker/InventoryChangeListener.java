package org.dw363.enxitshulker;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryChangeListener implements Listener {

    private final Map<Player, BukkitRunnable> playerTasks = new HashMap<>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            checkShulkerLimit(player);
        }
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            checkShulkerLimit(player);
        }
    }

    @EventHandler
    public void onItemHeldChange(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        checkShulkerLimit(player);
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            checkShulkerLimit(player);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        checkShulkerLimit(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        checkShulkerLimit(player);
    }

    public void checkShulkerLimit(Player player) {
        int maxShulkerBoxes = Main.getInstance().getMaxShulkerBoxes();
        List<ItemStack> shulkerBoxes = Main.getInstance().getShulkerBoxesInInventory(player.getInventory());

        if (playerTasks.containsKey(player)) {
            playerTasks.get(player).cancel();
            playerTasks.remove(player);
        }

        if (shulkerBoxes.size() > maxShulkerBoxes) {
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    String message = Main.getInstance().getLimitShulkerMessage(shulkerBoxes.size());
                    Main.getInstance().sendActionBar(player, message);
                }
            };
            task.runTaskTimer(Main.getInstance(), 0, 20);
            playerTasks.put(player, task);
        }
    }
}