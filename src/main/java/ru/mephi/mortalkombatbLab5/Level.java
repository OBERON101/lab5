package ru.mephi.mortalkombatbLab5;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Level {
    private Integer currentExperience = 0;
    private Integer currentLevel = 0;
    private final Map<Integer, Integer> levelMap = new HashMap<>();

    public Level() {
        int exp = 0;
        for (int i = 0; i < 21; i++) {
            levelMap.put(i, exp);
            exp += 40 + 20 * i;
        }
    }

    public Integer getCurrentExperience() {
        return currentExperience;
    }

    public void setCurrentExperience(Integer currentExperience) {
        this.currentExperience = currentExperience;
    }

    public void increaseCurrentExperience(Integer exp) {
        this.currentExperience += exp;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public Boolean tryIncreaseCurrentLevel() {
        if (currentExperience >= Optional.ofNullable(levelMap.get(currentLevel + 1)).orElse(Integer.MAX_VALUE)) {
            currentLevel++;
            return true;
        }
        return false;
    }

    public int getNextExperience() {
        return Optional.ofNullable(levelMap.get(currentLevel + 1)).orElse(Integer.MAX_VALUE);
    }
}
