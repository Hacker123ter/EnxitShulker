package org.dw363.enxitshulker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class EnderChestListener implements Listener {

    @EventHandler
    public void onEnderChestClick(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.ENDER_CHEST && event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Inventory enderChest = event.getInventory();
            int maxShulkerBoxes = Main.getInstance().getConfig().getInt("Player.Ender_box", 2);

            List<ItemStack> shulkerBoxesInEnderChest = getShulkerBoxesInInventory(enderChest);

            if (shulkerBoxesInEnderChest.size() > maxShulkerBoxes) {
                int excess = shulkerBoxesInEnderChest.size() - maxShulkerBoxes;

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < excess; i++) {
                            ItemStack shulkerBox = shulkerBoxesInEnderChest.get(shulkerBoxesInEnderChest.size() - 1);
                            shulkerBoxesInEnderChest.remove(shulkerBox);

                            if (player.getInventory().addItem(shulkerBox).isEmpty()) {
                                enderChest.removeItem(shulkerBox);
                            } else {
                                player.getWorld().dropItemNaturally(player.getLocation(), shulkerBox);
                                enderChest.removeItem(shulkerBox);
                            }
                        }

                        player.updateInventory();

                        String limitEnderMessage = Main.getInstance().getConfig().getString("Text.Limit_ender",
                                "&#fc0362У вас превышен лимит шалкеров в Сундуке Эндера.");
                        limitEnderMessage = Main.getInstance().translateHexColors(limitEnderMessage);
                        Main.getInstance().sendActionBar(player, limitEnderMessage);
                    }
                }.runTaskLater(Main.getInstance(), 2L);
            }
        }
    }

    @EventHandler
    public void onEnderChestClose(InventoryCloseEvent event) {
        if (event.getInventory().getType() == InventoryType.ENDER_CHEST && event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            Inventory enderChest = event.getInventory();
            int maxShulkerBoxes = Main.getInstance().getConfig().getInt("Player.Ender_box", 2);

            List<ItemStack> shulkerBoxesInEnderChest = getShulkerBoxesInInventory(enderChest);

            if (shulkerBoxesInEnderChest.size() > maxShulkerBoxes) {
                int excess = shulkerBoxesInEnderChest.size() - maxShulkerBoxes;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < excess; i++) {
                            ItemStack shulkerBox = shulkerBoxesInEnderChest.get(shulkerBoxesInEnderChest.size() - 1);
                            shulkerBoxesInEnderChest.remove(shulkerBox);

                            if (player.getInventory().addItem(shulkerBox).isEmpty()) {
                                enderChest.removeItem(shulkerBox);
                            } else {
                                player.getWorld().dropItemNaturally(player.getLocation(), shulkerBox);
                                enderChest.removeItem(shulkerBox);
                            }
                        }

                        player.updateInventory();

                        String limitEnderMessage = Main.getInstance().getConfig().getString("Text.Limit_ender",
                                "&#fc0362У вас превышен лимит шалкеров в Сундуке Эндера.");
                        limitEnderMessage = Main.getInstance().translateHexColors(limitEnderMessage);
                        Main.getInstance().sendActionBar(player, limitEnderMessage);
                    }
                }.runTaskLater(Main.getInstance(), 2L);
            }
        }
    }

    private List<ItemStack> getShulkerBoxesInInventory(Inventory inventory) {
        List<ItemStack> shulkerBoxes = new ArrayList<>();
        for (ItemStack item : inventory.getContents()) {
            if (item != null && Main.getInstance().isShulkerBox(item.getType())) {
                shulkerBoxes.add(item);
            }
        }
        return shulkerBoxes;
    }
}
