package dev.scarday.crash.entity;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.tracker.PlayerTracker;
import dev.scarday.crash.Configuration;
import dev.scarday.crash.util.ColorUtil;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.by1337.blib.geom.Vec3d;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class EntitySpawn {
    Plugin plugin;
    Configuration configuration;
    BukkitAudiences adventure;

    int range;

    public void spawnRandomMobs(Player player, int count) {
        val playerLocation = player.getLocation();

        for (int i = 0; i < count; i++) {
            val spawnLocation = getSpawnLocation(playerLocation);
            val randomEntityName = getRandomEntityName();
            spawn(randomEntityName, spawnLocation);
        }

        val message = ColorUtil.colorize(configuration.getMessages().getSuccessfully()
                .replace("{player}", player.getName())
                .replace("{count}", String.valueOf(count))
                .replace("{range}", String.valueOf(range)));

        adventure.sender(player)
                .sendMessage(message);
    }

    private Location getSpawnLocation(Location playerLocation) {
        val startX = playerLocation.getBlockX();
        val startY = playerLocation.getBlockY();
        val startZ = playerLocation.getBlockZ();

        val randomX = startX + ThreadLocalRandom.current().nextDouble(-range, range + 1);
        val randomY = startY + ThreadLocalRandom.current().nextDouble(-range, range + 1);
        val randomZ = startZ + ThreadLocalRandom.current().nextDouble(-range, range + 1);

        return new Location(playerLocation.getWorld(), randomX, randomY, randomZ);
    }

    private String getRandomEntityName() {
        int randomIndex = ThreadLocalRandom.current().nextInt(configuration.getListEntity().size());
        return configuration.getListEntity().get(randomIndex);
    }

    public void spawn(String entityName, Location location) {
        val tracker = new PlayerTracker(location.getWorld(), new Vec3d(location));
        val entity = VirtualEntityApi.getFactory()
                .create(VirtualEntityType.valueOf(entityName.toUpperCase()));

        entity.setPos(new Vec3d(location));
        tracker.addEntity(entity);

        Bukkit.getScheduler().runTaskTimer(plugin, tracker::tick, 1L, 1L);

        val timeDeleteTicks = configuration.getTimeDelete() * 20L;
        Bukkit.getScheduler().runTaskLater(plugin, tracker::removeAll, timeDeleteTicks);
    }

}
