package dev.scarday.crash;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.LiteBukkitMessages;
import dev.scarday.crash.command.CrashCommand;
import dev.scarday.crash.util.ColorUtil;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.serdes.okaeri.SerdesOkaeriBukkit;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Crash extends JavaPlugin {

    Configuration config;
    BukkitAudiences adventure;
    LiteCommands<CommandSender> litecommands;

    @Override
    public void onEnable() {
        this.adventure = BukkitAudiences.create(this);
        loadConfiguration();
        registerCommands();
    }

    @Override
    public void onDisable() {
        getServer().getScheduler()
                .cancelTasks(this);
    }

    private void loadConfiguration() {
        try {
            this.config = ConfigManager.create(Configuration.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesOkaeriBukkit());
                it.withBindFile(new File(getDataFolder(), "config.yml"));
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception e) {
            getLogger().warning("Failed to load configuration");
            e.printStackTrace();
        }
    }

    private void registerCommands() {
        this.litecommands = LiteBukkitFactory.builder()
                .commands(new CrashCommand(config, adventure, this))
                .message(LiteBukkitMessages.PLAYER_ONLY,
                        ColorUtil.colorizeToString(config.getMessages().getPlayerOnly()))
                .message(LiteBukkitMessages.MISSING_PERMISSIONS,
                        ColorUtil.colorizeToString(config.getMessages().getNoPermission()))
                .message(LiteBukkitMessages.PLAYER_NOT_FOUND,
                        ColorUtil.colorizeToString(config.getMessages().getPlayerNotFound()))
                .build();
    }
}
