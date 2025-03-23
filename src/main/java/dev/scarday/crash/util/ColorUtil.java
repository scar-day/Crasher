package dev.scarday.crash.util;

import lombok.experimental.UtilityClass;
import lombok.val;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class ColorUtil {
    private final MiniMessage MM = MiniMessage.miniMessage();

    public Component colorize(@NotNull String message) {
        return MM.deserialize(message);
    }

    public String colorizeToString(String message) {
        val component = colorize(message);

        return LegacyComponentSerializer.legacyAmpersand().serialize(component);
    }
}
