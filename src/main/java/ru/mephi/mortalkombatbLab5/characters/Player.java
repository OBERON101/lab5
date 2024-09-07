/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.mortalkombatbLab5.characters;

/**
 * @author Мария
 */
public class Player {

    private int level;
    private int health;
    private int maxHealth;
    private int damage;
    private int attack;

    public Player(int level, int health, int damage, int attack) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.attack = attack;
        this.maxHealth = health;
    }

    public void increaseLevel() {
        this.level++;
    }

    public void increaseHealth(int h) {
        this.health += h;
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public void increaseDamage(int d) {
        this.damage += d;
    }

    public void setAttack(int a) {
        this.attack = a;
    }

    public void increaseMaxHealth(int h) {
        this.maxHealth += h;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public String getName() {
        return "";
    }

}
