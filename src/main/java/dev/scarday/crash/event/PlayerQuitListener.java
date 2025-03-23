package dev.scarday.crash.event;

import dev.by1337.virtualentity.api.tracker.PlayerTracker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class PlayerQuitListener implements Listener {
    PlayerTracker playerTracker;

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // TODO: Реализовать удаление ВСЕХ с трекеера энтити при выходе с сервера
    }
}
