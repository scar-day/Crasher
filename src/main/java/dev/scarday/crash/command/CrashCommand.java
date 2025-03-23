package dev.scarday.crash.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import dev.scarday.crash.Configuration;
import dev.scarday.crash.Crash;
import dev.scarday.crash.entity.EntitySpawn;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.entity.Player;

@Command(name = "crash")
@Permission(value = "crash.admin")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class CrashCommand {
    Configuration configuration;
    BukkitAudiences adventure;

    Crash plugin;

    @Execute
    void command(@Arg Player player, @Arg int count) {
        val spawn = new EntitySpawn(plugin, configuration, adventure, 5);
        spawn.spawnRandomMobs(player, count);
    }
}
