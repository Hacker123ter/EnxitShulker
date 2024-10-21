package org.dw363.enxitshulker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class EnxitCommand implements CommandExecutor {

    private final Main plugin;

    public EnxitCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender instanceof Player && !sender.hasPermission("enxit.reload")) {
                sender.sendMessage(ChatColor.RED + "У вас нет прав на выполнение этой команды.");
                return true;
            }

            plugin.reloadPluginConfig();
            sender.sendMessage(ChatColor.DARK_PURPLE + "EnxitShulker: " + ChatColor.GREEN + "Конфигурация плагина успешно перезагружена.");
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Неправильное использование команды. Используйте" + ChatColor.GREEN + " /enxit reload" + ChatColor.RED + ".");
        return false;
    }
}