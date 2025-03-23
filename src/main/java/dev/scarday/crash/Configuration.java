package dev.scarday.crash;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Configuration extends OkaeriConfig {
    int blocks = 5;
    List<String> listEntity = List.of("Zombie", "Spider", "Creeper");
    int timeDelete = 15;

    Messages messages = new Messages();

    @Getter
    @Setter
    @FieldDefaults(level = lombok.AccessLevel.PRIVATE)
    public static class Messages extends OkaeriConfig {
        String successfully = "<orange>Появилось {count} энтити в радиусе {radius} блкоков, у игрока {player}";
        String playerOnly = "<red>Данную команду могут использовать только игроки!";
        String playerNotFound = "<red>Игрок не найден!";
        String noPermission = "<red>У вас нет прав на использование этой команды!";

        String helpMessage = "<green>Правильное использование: /crash <ник> <кол-во энтити>";

        String incorrectCount = "<red>Неверное количество энтити!";
    }
}
