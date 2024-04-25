package me.mitlit.clearconfirm;

import com.jeff_media.updatechecker.UpdateCheckSource;
import com.jeff_media.updatechecker.UpdateChecker;
import com.jeff_media.updatechecker.UserAgentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClearConfirm extends JavaPlugin implements Listener {

    public static ClearConfirm plugin; // Plugin instance

    @Override
    public void onEnable() {

        // Important Stuff
        plugin = this;

        // Register Events
        getLogger().info("Registering Events...");
        this.getServer().getPluginManager().registerEvents(new CommandCanceller(), this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        // Set Command Executor
        PluginCommands pluginCommands = new PluginCommands();
        this.getCommand("clearconfirm").setExecutor(pluginCommands);

        // Configuration Setup
        saveDefaultConfig();
        FileConfiguration config = getConfig();

        // Update Checker
        new UpdateChecker(this, UpdateCheckSource.CUSTOM_URL, "https://raw.githubusercontent.com/ItsMitlit/ClearConfirm/master/latestversion.txt") // A link to a URL that contains the latest version as String
                .setDownloadLink("https://modrinth.com/project/clearconfirm")
                .setDonationLink("https://www.patreon.com/mitlit")
                .setChangelogLink("https://modrinth.com/project/clearconfirm/versions")
                .setSupportLink("https://discord.com/invite/mApaF6zMfF")
                .setNotifyOpsOnJoin(true)
                .setUserAgent(new UserAgentBuilder().addPluginNameAndVersion())
                .checkEveryXHours(3)
                .checkNow();
    }
}

