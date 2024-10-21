package org.dw363.enxitshulker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends JavaPlugin {

    private static Main instance;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        config = getConfig();

        Bukkit.getPluginManager().registerEvents(new PlayerExitListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryChangeListener(), this);
        Bukkit.getPluginManager().registerEvents(new EnderChestListener(), this);
        this.getCommand("enxit").setExecutor(new EnxitCommand(this));
        this.getCommand("enxit").setTabCompleter(new EnxitTabCompleter());

        getLogger().info("Плагин EnxitShulker включен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин EnxitShulker отключен!");
    }

    public static Main getInstance() {
        return instance;
    }

    public int getMaxShulkerBoxes() {
        return config.getInt("Player.Exit_box", 2);
    }

    public String getLimitShulkerMessage(int countInInventory) {
        String message = config.getString("Text.Limit_shulker",
                "&4У вас шалкеров {count_in_inventory}/{Exit_box} в инвентаре, лишние будут выброшены при выходе.");
        message = message.replace("{count_in_inventory}", String.valueOf(countInInventory))
                .replace("{Exit_box}", String.valueOf(getMaxShulkerBoxes()));
        return translateHexColors(message);
    }

    public String translateHexColors(String message) {
        message = message.replaceAll("#([A-Fa-f0-9]{6})", "&#$1");
        Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String hex = matcher.group(1);
            StringBuilder hexFormatted = new StringBuilder("§x");
            for (char ch : hex.toCharArray()) {
                hexFormatted.append("§").append(ch);
            }
            matcher.appendReplacement(buffer, hexFormatted.toString());
        }
        matcher.appendTail(buffer);
        return ChatColor.translateAlternateColorCodes('&', buffer.toString());
    }


    public void sendActionBar(Player player, String message) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            }
        }.runTask(this);
    }
    public List<ItemStack> getShulkerBoxesInInventory(PlayerInventory inventory) {
        List<ItemStack> shulkerBoxes = new ArrayList<>();
        for (ItemStack item : inventory.getContents()) {
            if (item != null && isShulkerBox(item.getType())) {
                shulkerBoxes.add(item);
            }
        }
        return shulkerBoxes;
    }

    public boolean isShulkerBox(Material material) {
        switch (material) {
            case BLACK_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case SHULKER_BOX:
            case WHITE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
                return true;
            default:
                return false;
        }
    }

    public void dropExcessShulkers(Player player, List<ItemStack> shulkerBoxes, int excess) {
        Random random = new Random();

        for (int i = 0; i < excess; i++) {
            int index = random.nextInt(shulkerBoxes.size());
            ItemStack shulkerBox = shulkerBoxes.get(index);

            player.getWorld().dropItemNaturally(player.getLocation(), shulkerBox);
            player.getInventory().removeItem(shulkerBox);
            shulkerBoxes.remove(index);
        }
    }

    public void reloadPluginConfig() {
        reloadConfig();
        config = getConfig();
        getLogger().info("Enxit: Конфигурация успешно перезагружена.");
    }
}