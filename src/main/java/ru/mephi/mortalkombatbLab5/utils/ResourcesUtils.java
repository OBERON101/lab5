package ru.mephi.mortalkombatbLab5.utils;

import ru.mephi.mortalkombatbLab5.MortalKombatBVersion;

import java.net.URL;
import java.util.Optional;

public class ResourcesUtils {

    public static String getScoreTablePath() {
        String s = Optional.ofNullable(MortalKombatBVersion.class.getClassLoader()
                        .getResource("score-table/Results.xlsx"))
                .map(URL::getPath)
                .orElseThrow(() -> new RuntimeException("Файл с результатами не найден"));
        return fixSymbols(s);
    }

    public static String getCharacterPicturesFolderPath() {
        String s = Optional.ofNullable(MortalKombatBVersion.class.getClassLoader()
                        .getResource("character-pictures"))
                .map(URL::getPath)
                .orElseThrow(() -> new RuntimeException("Папка с изображениями персонажей не найдена"));
        return fixSymbols(s);
    }

    private static String fixSymbols(String path) {
        return path.replaceAll("%20", " ");
    }

    public static String getLogoPath() {
        String s = Optional.ofNullable(MortalKombatBVersion.class.getClassLoader()
                        .getResource("logo"))
                .map(URL::getPath)
                .orElseThrow(() -> new RuntimeException("Папка с изображением лого"));
        return fixSymbols(s);
    }
}
