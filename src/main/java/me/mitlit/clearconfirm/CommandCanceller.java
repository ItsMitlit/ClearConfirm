package me.mitlit.clearconfirm;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandCanceller implements Listener {

    // Clear Command Variations
    List<String> clearCommandVariations = Arrays.asList(
            "/clear", "/minecraft:clear"
    );

    // Store the player's name and the command they attempted to run
    public static Map<String, String> pendingCommands = new HashMap<>();

    @EventHandler
    public void onClearAttempt(PlayerCommandPreprocessEvent event) {
        if (event.getPlayer().hasPermission("minecraft.command.clear")) {

            // Importing from config
            String confirmMessage = ClearConfirm.plugin.getConfig().getString("confirmMessage");
            // Translating color codes
            confirmMessage = ChatColor.translateAlternateColorCodes('&', confirmMessage);

            // Setting array
            String message = event.getMessage(); // Getting the message
            String[] array = message.split(" ");

            // Checking if the command is in the list
            if (clearCommandVariations.contains(array[0].toLowerCase())) {
                // Sending a message to the player asking to confirm their actions
                Bukkit.getPlayer(event.getPlayer().getName()).sendMessage(confirmMessage);
                // Storing the command
                pendingCommands.put(event.getPlayer().getName(), message);
                // Cancelling the command
                event.setCancelled(true);
            }
        }
    }
}

