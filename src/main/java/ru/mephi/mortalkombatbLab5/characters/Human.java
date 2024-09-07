/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.mortalkombatbLab5.characters;

import ru.mephi.mortalkombatbLab5.Level;

/**
 * @author Мария
 */
public class Human extends Player {


    private int points;
    private int win;
    private int currentWinPerLocation;
    private final Level level;
    private Integer bossCount = 0;

    private final int cheatBoost = 3000;

    public Human(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        this.points = 0;
        this.win = 0;
        this.currentWinPerLocation = 0;
        this.level = new Level();
    }

    public Integer getBossCount() {
        return bossCount;
    }

    public void setBossCount() {
        this.bossCount++;
    }

    public int getCurrentWinPerLocation() {
        return currentWinPerLocation;
    }

    public void setCurrentWinPerLocation(int currentWinPerLocation) {
        this.currentWinPerLocation = currentWinPerLocation;
    }

    public void increaseCurrentWinPerLocation() {
        this.currentWinPerLocation++;
    }

    public int getPoints() {
        return this.points;
    }

    public int getExperience() {
        return this.level.getCurrentExperience();
    }

    public int getNextExperience() {
        return this.level.getNextExperience();
    }

    public int getWin() {
        return this.win;
    }

    public void increasePoints(int p) {
        this.points += p;
    }

    public void increaseExperience(int e) {
        this.level.increaseCurrentExperience(e);
    }

    public void increaseWin() {
        this.win++;
    }

    @Override
    public String getName() {
        return "You";
    }

    @Override
    public int getHealth() {
        return super.getHealth() * cheatBoost / 100;
    }

    @Override
    public int getDamage() {
        return super.getDamage() * cheatBoost / 100;
    }

    @Override
    public int getMaxHealth() {
        return super.getMaxHealth() * cheatBoost / 100;
    }

    public boolean tryIncreaseLevel() {
        return level.tryIncreaseCurrentLevel();
    }
}
