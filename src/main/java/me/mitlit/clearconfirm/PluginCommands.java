package me.mitlit.clearconfirm;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PluginCommands implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("clearconfirm") && sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getName();
            if (CommandCanceller.pendingCommands.containsKey(playerName)) {
                String pendingCommand = CommandCanceller.pendingCommands.get(playerName);
                player.performCommand(pendingCommand.substring(1));
                CommandCanceller.pendingCommands.remove(playerName);
            }
            return true;
        }
        return false;
    }
}
