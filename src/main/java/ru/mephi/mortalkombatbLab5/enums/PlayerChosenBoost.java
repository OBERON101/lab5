package ru.mephi.mortalkombatbLab5.enums;

public enum PlayerChosenBoost {
    HEALTH(0, 1), DAMAGE(1, 0);

    private final Integer damageBoost;
    private final Integer healthBoost;

    PlayerChosenBoost(Integer damageBoost, Integer healthBoost) {
        this.damageBoost = damageBoost;
        this.healthBoost = healthBoost;
    }

    public Integer getDamageBoost() {
        return damageBoost;
    }

    public Integer getHealthBoost() {
        return healthBoost;
    }
}
