package org.dw363.enxitshulker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public class PlayerExitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        int maxShulkerBoxes = Main.getInstance().getMaxShulkerBoxes();

        List<ItemStack> shulkerBoxes = Main.getInstance().getShulkerBoxesInInventory(inventory);

        if (shulkerBoxes.size() > maxShulkerBoxes) {
            int excess = shulkerBoxes.size() - maxShulkerBoxes;
            Main.getInstance().dropExcessShulkers(player, shulkerBoxes, excess);
        }
    }
}