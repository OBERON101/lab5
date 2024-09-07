package ru.mephi.mortalkombatbLab5.utils;

import ru.mephi.mortalkombatbLab5.MortalKombatBVersion;

import java.net.URL;
import java.util.Optional;

public class ResourcesUtils {

    public static URL getCharacterPicturesFolderPath(String additionalPath) {
        return Optional.ofNullable(MortalKombatBVersion.class.getClassLoader()
                        .getResource("character-pictures" + additionalPath))
                .orElseThrow(() -> new RuntimeException("Изображение персонажа не найдена"));
    }

    public static URL getLogoPath() {
        return Optional.ofNullable(MortalKombatBVersion.class.getClassLoader()
                        .getResource("logo/MK.jpg"))
                .orElseThrow(() -> new RuntimeException("Изображение лого не найдено"));
    }
}
